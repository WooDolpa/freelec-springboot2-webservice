package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName : com.jojoldu.book.springboot.config.auth
 * className : LoginUser
 * user : jwlee
 * date : 2022/08/27
 */

/**
 * 어노테이션이 생성될 수 있는 위치를 지정
 * PARAMETER 로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있음
 * 이 외에도 클래스 선언문에 쓸 수 있는 Type 등이 있음
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 이 파일을 어노테이션 클래스로 지정
 * LoginUser 라는 이름을 가진 어노테이션이 생성
 */
public @interface LoginUser {
}
