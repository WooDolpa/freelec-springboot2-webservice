package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName : com.jojoldu.book.springboot.web
 * className : HelloControllerTest
 * user : jwlee
 * date : 2022/08/17
 */

/**
 * 테스트를 진행할때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행
 * 여기서는 SpringRunner 라는 스프링 실행자를 사용
 * 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할
 *
 */
@ExtendWith(SpringExtension.class)
/**
 * 여러 스프링 테스트 어노테이션 중, Web(spring MVC)에 집중할 수 있는 어노테이션이다.
 * 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
 * 단 @Service, @Component, @Repository 등은 사용할 수 없음
 * 여기서는 컨트롤러만 사용하기 때문에 선언한다.
 *
 */
/**
 * @WebMvcTest 는 CustomOAuth2UserService 를 스캔하지 않음
 *
 */
@WebMvcTest(controllers = HelloController.class,
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {

    @Autowired      // 스프링이 관리하는 빈(Bean)을 주입 받음
    /**
     * 웹 API를 테스트할때 사용
     * 스프링 MVC 테스트의 시작점
     * 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
     *
     */
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {

        String hello = "hello";

        mvc.perform(get("/hello"))      // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함, 체이닝이 지원되어 여러 검증 기능을 이어서 선언
                .andExpect(status().isOk())        // mvc.perform 의 결과를 검증, HTTP Header의 Status를 검증, 200, 404, 500 등의 상태 검증, 여기선 OK 즉, 200인지 검증
                .andExpect(content().string(hello)); // mvc.perform 결과 검증, 응답 본문의 내용을 검증, Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증

    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {

        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));


        // 1. param
        // API 테스트할때 사용될 요청 파라미터를 설정
        // 단, 값은 String 만 허용
        // 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야 한다

        // 2. jsonPath
        // JSON 응답값을 필드별로 검증할 수 있는 메소드
        // $를 기준으로 필드명을 명시
        // 여기서는 name 과 amount 를 검증하니 $.name, $.amount 로 검증
    }

}
