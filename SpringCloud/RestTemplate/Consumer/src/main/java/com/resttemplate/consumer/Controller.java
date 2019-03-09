package com.resttemplate.consumer;

import com.jc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class Controller {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/gethello")
    public String getHello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        return result.toString();
    }

    @RequestMapping("/sayhello")
    public String sayHello() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://HELLO-SERVICE/sayHello?name={1}",
                String.class, "QQ");
        return responseEntity.getBody();
    }
    @RequestMapping("/sayhello2")
    public String sayHello2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "WW");
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://HELLO-SERVICE/sayHello?name={name}",
                        String.class, map);
        return responseEntity.getBody();
    }

    @RequestMapping("/book1")
    public Book book1() {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/getbook1", Book.class);
        return responseEntity.getBody();
    }
    @RequestMapping("/book2")
    public Book book2() {
        Book book = restTemplate.getForObject("http://HELLO-SERVICE/getbook1", Book.class);
        return book;
    }

    @RequestMapping("/book3")
    public Book book3() {
        Book book = new Book("Software Enginerring",90);
         ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://HELLO-SERVICE/getbook2", book, Book.class);
        return responseEntity.getBody();
    }
}
