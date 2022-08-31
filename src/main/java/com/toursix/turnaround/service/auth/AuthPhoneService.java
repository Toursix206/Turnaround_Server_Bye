package com.toursix.turnaround.service.auth;

import com.toursix.turnaround.common.exception.InternalServerException;
import com.toursix.turnaround.common.exception.ValidationException;
import com.toursix.turnaround.common.util.YamlPropertySourceFactory;
import com.toursix.turnaround.service.auth.dto.request.AuthPhoneCheckRequestDto;
import com.toursix.turnaround.service.auth.dto.request.AuthPhoneRequestDto;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.toursix.turnaround.common.exception.ErrorCode.SMS_EXCEPTION;
import static com.toursix.turnaround.common.exception.ErrorCode.VALIDATION_AUTH_NUMBER_EXCEPTION;

@RequiredArgsConstructor
@Service
@PropertySource(value = "classpath:application-coolsms.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class AuthPhoneService {

    private static final long AUTH_NUMBER_EXPIRE_TIME = 3 * 60 * 1000L;   // 3분
    private static final long AUTH_USER_EXPIRE_TIME = 24 * 60 * 60 * 1000L;   // 1일

    private final RedisTemplate redisTemplate;

    @Value("${coolsms.apiKey}")
    private String apiKey;

    @Value("${coolsms.apiSecret}")
    private String apiSecret;

    @Value("${coolsms.fromNumber}")
    private String fromNumber;

    public void authPhone(AuthPhoneRequestDto request, Long userId) {
        String authNumber = createAuthNumber();
        sendMessage(request.getPhoneNumber(), authNumber);
        redisTemplate.opsForValue().set("AN:" + userId, authNumber, AUTH_NUMBER_EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    public void authPhoneCheck(AuthPhoneCheckRequestDto request, Long userId) {
        String authNumber = (String) redisTemplate.opsForValue().get("AN:" + userId);
        validateAuthNumber(request.getAuthNumber(), authNumber);
        redisTemplate.opsForValue().set("AU:" + userId, request.getPhoneNumber(), AUTH_USER_EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    private void validateAuthNumber(String checkNumber, String authNumber) {
        if (!checkNumber.equals(authNumber)) {
            throw new ValidationException(String.format("인증번호 (%s) (%s) 가 일치하지 않습니다.", checkNumber, authNumber), VALIDATION_AUTH_NUMBER_EXCEPTION);
        }
    }

    private void sendMessage(String toNumber, String authNumber) {
        Message coolsms = new Message(apiKey, apiSecret);
        HashMap<String, String> params = new HashMap<>();
        params.put("to", toNumber);
        params.put("from", fromNumber);
        params.put("type", "SMS");
        params.put("text", "[턴어라운드] 인증번호 " + authNumber + " 를 입력하세요.");
        params.put("app_version", "turnaround app 1.0");
        try {
            JSONObject obj = coolsms.send(params);
        } catch (CoolsmsException e) {
            throw new InternalServerException(String.format("전화번호 (%s) 인증번호 전송 실패입니다", toNumber), SMS_EXCEPTION);
        }
    }

    private String createAuthNumber() {
        int targetStringLength = 6;
        Random random = new Random();
        return random.ints(48, 58)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
