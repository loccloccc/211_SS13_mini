package com.example.ktra.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* com.example.ktra.service.*.*(..))")
    public void loggingPointcut() {
    }

    @Before("loggingPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getClass().getName();
        log.info("Method {} bắt đầu chạy " , className);
    }


    @AfterReturning(
            pointcut = "loggingPointcut()",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        String methodName = joinPoint.getSignature().getName();

        log.info("Method {} chạy thành công", methodName);

        log.info("Kết quả trả về: {}", result);
    }


    @AfterThrowing(
            pointcut = "loggingPointcut()",
            throwing = "ex"
    )
    public void logError(JoinPoint joinPoint, Exception ex) {

        String methodName = joinPoint.getSignature().getName();

        log.error("Method {} bị lỗi", methodName);

        log.error("Error: {}", ex.getMessage());
    }
}
