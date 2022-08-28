package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * packageName : com.jojoldu.book.springboot.config.auth
 * className : LoginUserArgumentResolver
 * user : jwlee
 * date : 2022/08/27
 */
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    /**
     * 컨트롤러 메소드의 특정 파라미터를 지원하는지 판단
     * @LoginUser 어노테이션이 붙어 있고 파라미터 클래스 타입이 SessionUser.class 인 경우 true 반환
     * @param parameter the method parameter to check
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    /**
     * 파라미터에 전달하는 객체를 생성
     * 세션에서 객체를 가져옴
     *
     * @param parameter the method parameter to resolve. This parameter must
     * have previously been passed to {@link #supportsParameter} which must
     * have returned {@code true}.
     * @param mavContainer the ModelAndViewContainer for the current request
     * @param webRequest the current request
     * @param binderFactory a factory for creating {@link WebDataBinder} instances
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
