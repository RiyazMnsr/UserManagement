package com.example.user.service.jsonprovider;

import com.example.user.constant.ErrorMessage;
import com.example.user.dto.User;
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

    @Autowired
    private RestTemplate apiGatewayRestTemplate;

    @Override
    public List<User> getUserList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<User> usersResponse;

        String sampleJsonUri = "/posts";
        HttpEntity<String> request = new HttpEntity<String>(headers);


        try {
            usersResponse = apiGatewayRestTemplate.exchange(sampleJsonUri, HttpMethod.GET, request,
                    new ParameterizedTypeReference<List<User>>() {
                    }).getBody();

        } catch (RestClientResponseException ex) {
            throw new RuntimeException(ErrorMessage.ERROR_WHILE_CALLING_JSON_PROVIDER);
        }

        usersResponse = Optional.ofNullable(usersResponse)
                .orElseThrow(() -> new RuntimeException(ErrorMessage.JSON_NULL_RESPONSE));
        return usersResponse;
    }
}
