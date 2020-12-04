package com.example.user.service.jsonprovider;

import com.example.user.constant.ErrorMessage;
import com.example.user.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserGatewayImpl implements UserGateway {

    public static final Logger logger = LoggerFactory.getLogger(UserGatewayImpl.class);
    private static final String SAMPLE_JSON_URI = "/posts";

    @Autowired
    private RestTemplate apiGatewayRestTemplate;

    /**
     *
     * @return user list
     * calling json provider over HTTP protocol using Rest Template
     */
    @Override
    public List<User> getUserList() {
        logger.info("getUserList start");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<User> usersResponse;

        HttpEntity<String> request = new HttpEntity<String>(headers);

        try {
            usersResponse = apiGatewayRestTemplate.exchange(SAMPLE_JSON_URI, HttpMethod.GET, request,
                    new ParameterizedTypeReference<List<User>>() {
                    }).getBody();

        } catch (RestClientResponseException ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(ErrorMessage.ERROR_WHILE_CALLING_JSON_PROVIDER);
        }

        usersResponse = Optional.ofNullable(usersResponse)
                .orElseThrow(() -> new RuntimeException(ErrorMessage.JSON_NULL_RESPONSE));
        logger.debug("Response from gateway : {}", usersResponse);
        return usersResponse;
    }
}
