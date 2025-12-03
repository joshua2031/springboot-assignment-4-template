package com.wafflestudio.spring2025.common.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisService(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    // 키-값 저장 (만료 시간 설정)
    fun setValues(
        key: String,
        value: String,
        duration: Duration,
    ) {
        stringRedisTemplate.opsForValue().set(key, value, duration)
    }

    // 값 조회
    fun getValues(key: String): String? = stringRedisTemplate.opsForValue().get(key)

    // 키 삭제
    fun deleteValues(key: String) {
        stringRedisTemplate.delete(key)
    }

    // 블랙리스트 확인
    fun isBlacklisted(token: String): Boolean = getValues(token) != null
}
