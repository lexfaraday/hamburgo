package com.hotelbeds.travel.api.commons;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class AuthUtil {

   
    @Value("${oauth.authorize:https://www.eventbrite.com/oauth/authorize}")
    private String authorizeUrl;

    @Value("${oauth.token:https://www.eventbrite.com/oauth/token}")
    private String tokenUrl;

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {

        //        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        //
        //        List<String> scopes = new ArrayList<String>(2);
        //        scopes.add("write");
        //        scopes.add("read");
        //        resource.setAccessTokenUri(tokenUrl);
        //        resource.setClientId("6LXSEZEOW27DBGD44I");
        //        resource.setClientSecret("DYQXSSSB3OGJHEQBK6EXK4YEV7Y6ZJDD2QP2V6SMFZCZUIJBLS");
        //        resource.setGrantType("password");
        //        resource.setScope(scopes);
        //
        //        resource.setUsername("s");
        //        resource.setPassword("e");
        //
        //        return resource;

        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("reddit");
        details.setClientId("6LXSEZEOW27DBGD44I");
        details.setClientSecret("DYQXSSSB3OGJHEQBK6EXK4YEV7Y6ZJDD2QP2V6SMFZCZUIJBLS");
        details.setAccessTokenUri(tokenUrl);
        details.setUserAuthorizationUri(authorizeUrl);
        details.setTokenName("oauth_token");
        details.setScope(Arrays.asList("identity"));
        details.setPreEstablishedRedirectUri("http://localhost/login");
        details.setUseCurrentUri(false);
        return details;
    }

    @Bean
    public OAuth2RestOperations restTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        //return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
        return new OAuth2RestTemplate(new AuthorizationCodeResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

}