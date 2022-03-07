package com.base.jpaproject.auth;

import com.base.jpaproject.main.entity.User;
import com.base.jpaproject.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService defaultUserService = new DefaultOAuth2UserService();
        OAuth2User authUser = defaultUserService.loadUser(userRequest);

        String regId = userRequest.getClientRegistration().getRegistrationId();
        String userAttrName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        log.info("## auth regid : " + regId);

        //google info get
        OAuthAttributes attr = OAuthAttributes.ofGoogle(userAttrName, authUser.getAttributes());

        User user = saveUpdateUser(attr);


        return null;
    }

    private User saveUpdateUser(OAuthAttributes attr) {
        User user = userRepository.findByEmail(attr.getEmail())
                .map(entity -> entity.update(attr.getName(),attr.getPicture()))
                .orElse(attr.createUser())
                ;
        return userRepository.save(user);
    }
}
