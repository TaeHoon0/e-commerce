package kr.hhplus.be.server.order.infrastructure.message;

import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageQueue {

    public boolean random() {

        int chance = ThreadLocalRandom.current().nextInt(100);

        return chance >= 30;
    }

    public void send() {

        if (random()) { throw new IllegalArgumentException("메시지 발송 실패");}

        log.info("메시지 발송 성공");
    }
}
