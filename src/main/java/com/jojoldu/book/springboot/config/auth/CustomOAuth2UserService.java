package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.OAuthAttributes;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * packageName : com.jojoldu.book.springboot.config.auth
 * className : CustomOAuth2UserService
 * user : jwlee
 * date : 2022/08/27
 */
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /**
         * 현재 로그인 진행 중인 서비스를 구분하는 코드
         * 지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시에 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용
         */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        /**
         * OAuth2 로그인 진행 시 키가 되는 필드값 -> Primary Key 같은 의미
         * userNameAttributeName
         * 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 는 미지원 구글의 기본 코드는 sub
         * 이후 네이버 로그인과 구글 로그인을 동시 지원할때 사용
         */
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        /**
         * OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute 를 담는 클래스
         * 이후 네이버 등 다른 소셜 로그인도 해당 클래스 사용
         */
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);
        /**
         * 세션에 사용자 정보를 저장하기 위한 Dto
         * 왜 User 클래스를 사용하면 안될까?
         * --> User 클래스에 직렬화 되지 않았다는 에러가 발생 그럼 User 클래스에 직렬화 코드를 넣으면 되나?
         * --> 자식 엔티티를 가질 수 있기 때문에 User 에 직렬화가 되면 자식들도 포함이 되므로 성능 이슈, 부수 효과가 발생 확률이 높다
         * 직렬화 기능을 가진 세션 Dto를 하나 추가해서 만드는 것이 운영 및 유지보수 때 많은 도움이 됨
         */
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {

        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
