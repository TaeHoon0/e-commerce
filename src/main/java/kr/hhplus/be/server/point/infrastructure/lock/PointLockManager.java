package kr.hhplus.be.server.point.infrastructure.lock;

import kr.hhplus.be.server.point.domain.exception.ErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
@ConfigurationProperties(prefix = "lock")
public class PointLockManager {

    private final long timeOut;
    private final long retrySleep;
    private final ConcurrentMap<Long, ReentrantLock> pointLockManager = new ConcurrentHashMap<>();

    public void getLock(long userId) throws InterruptedException {

        ReentrantLock lock = pointLockManager.computeIfAbsent(userId, k -> new ReentrantLock(true));

        for (int retry = 0; retry < 3; retry++ ) {

            if (lock.tryLock(timeOut, TimeUnit.MILLISECONDS))
                return;

            Thread.sleep(retrySleep);
        }

        throw new PointException(ErrorCode.LOCK_ACQUISITION_FAILED);
    }

    public void releaseLock(long userId) {

        ReentrantLock lock = pointLockManager.get(userId);

        if (lock != null && lock.isHeldByCurrentThread()) {

            lock.unlock();

            // 아무도 기다리지 않고, 락도 안 잡혀 있으면 제거
            if (!lock.hasQueuedThreads() && !lock.isLocked())
                pointLockManager.remove(userId, lock);
        }
    }
}
