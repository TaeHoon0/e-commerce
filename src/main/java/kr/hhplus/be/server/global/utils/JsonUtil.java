package kr.hhplus.be.server.global.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

    // 스레드 세이프한 ObjectMapper 인스턴스
    private static final ObjectMapper mapper = new ObjectMapper()
        .findAndRegisterModules()
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    /**
     * 객체를 JSON 문자열로 변환
     * @param obj 변환할 객체
     * @return JSON 문자열
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("객체 → JSON 변환 실패", e);
        }
    }

    /**
     * JSON 문자열을 지정한 타입의 객체로 변환
     * @param json  JSON 문자열
     * @param clazz 변환 대상 클래스
     * @param <T>   변환 대상 타입
     * @return 변환된 객체
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("JSON → 객체 변환 실패", e);
        }
    }
}
