package com.example.spring02.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // spring에서 관리하는 bean
@Aspect // aop를 지원하는 어드바이스 클래스
public class LogAdvice {

//method 호출 전후에 실행되는 코드	
	@Around(
"execution(* com.example.spring02.controller..*Controller.*(..))"
+ " || execution(* com.example.spring02.service..*Impl.*(..))"
+ " || execution(* com.example.spring02.model..dao.*Impl.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis(); //현재 시각
		
		//메인 액션
		Object result = joinPoint.proceed(); //
		
		//클래스 이름
		String type = joinPoint.getSignature().getDeclaringTypeName();
		String name = "";
		if (type.indexOf("Controller") > -1) {
			name = "Controller \t: ";
		} else if (type.indexOf("Service") > -1) {
			name = "ServiceImpl \t: ";
		} else if (type.indexOf("DAO") > -1) {
			name = "DAOImpl \t: ";
		}
		//method 이름
		System.out.println(name + type + "." + 
				joinPoint.getSignature().getName() + "()");
		//파라미터 리스트
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		long end = System.currentTimeMillis(); 
		long time = end - start;
		System.out.println("실행시간:" + time);
		return result;
	}
}
