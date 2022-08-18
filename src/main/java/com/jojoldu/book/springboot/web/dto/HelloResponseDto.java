package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName : com.jojoldu.book.springboot.web.dto
 * className : HelloResponseDto
 * user : jwlee
 * date : 2022/08/18
 */

/**
 * 선언된 모든 필드의 get Method 를 선언한다.
 */
/**
 * 선언된 모든 final 필드가 포함된 생성자를 생성해 준다.
 * final이 없는 필드는 생성자에 포함되지 않음
 */
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
