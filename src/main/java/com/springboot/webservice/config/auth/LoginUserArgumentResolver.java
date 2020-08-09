package com.springboot.webservice.config.auth;

import com.springboot.webservice.config.auth.LoginUser;
import com.springboot.webservice.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

// HandlerMethodArgumentResolver 인터페이스 구현클래스
// 조건에 맞는 메소드가 있을 때 HandlerMethodArgumentResolver의 구현체가 지정한 값으로
// 해당 메소드 파라미터로 넘길 수 있도록 한다.
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    // 컨트롤러 메소드의 특정 파라미터를 지원하는지 판단
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        // 파라미터에 @LoginUser 어노테이션이 붙어있고
        boolean isLoginUserAnnotation = methodParameter.getParameterAnnotation(LoginUser.class) != null;
        // 파라미터 클래스 타입이 SessionUser (인증된 사용자 정보를 담는 클래스)인 경우 true 반환
        boolean isUserClass = SessionUser.class.equals(methodParameter.getParameterType());

        return isLoginUserAnnotation && isUserClass; // 조건이 만족되면 true 반환
    }

    // 파라미터에 전달할 객체를 생성한다. (세션에서 user 객체를 가져온다.)
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
