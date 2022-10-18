package com.example.templateService.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.templateService.dto.ChuyenBayDto;

@Service
public class RestTemplateAPi {
    
    private final static String URL_API = "http://localhost:8080/chuyenbay"; 
    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public void postChuyenBay(ChuyenBayDto chuyenBayDto) {
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();  
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
            messageConverters.add(converter);  
            restTemplate.setMessageConverters(messageConverters);
            
            
            HttpEntity<Object> requestBody = new HttpEntity<>(chuyenBayDto, setHeaders());

            ResponseEntity<?> res = restTemplate.exchange(URL_API, HttpMethod.POST, requestBody, Object.class);
        }catch(Exception error) {
            error.printStackTrace();
        }
    }
}
