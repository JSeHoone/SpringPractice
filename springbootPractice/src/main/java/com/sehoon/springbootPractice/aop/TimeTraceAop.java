package com.sehoon.springbootPractice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // Aop적용하는 어노테이션
@Component // SpringConfig에 빈을 등록해도 되고, Component로 등록해도 된다.
public class TimeTraceAop {

    // 공통관심사항을 타켓팅해줄 수 있다.
    @Around("execution(* com.sehoon.springbootPractice..*(..))") // 이 뜻은 패키지 하위에다가 다 적용하라는 의미
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toLongString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toLongString() + " " + timeMs + "ms");
        }

    }
}
