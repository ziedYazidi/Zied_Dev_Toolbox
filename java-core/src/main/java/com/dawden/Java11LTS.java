package com.dawden;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Java11LTS {

    private static final Logger log = LoggerFactory.getLogger(Java11LTS.class);

    public static void main(String[] args) {
        getUsers().forEach(System.out::println);
        varPOC();
    }

    public static void varPOC() {
        var name = "zied";
        System.out.println(name);
    }
    public static List<User> getUsers() {
        try (HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build()) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String users = httpResponse.body();
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.asList(objectMapper.readValue(users, User[].class));
        } catch (IOException e) {
            log.error("IOException");
        } catch (InterruptedException e) {
            log.error("InterruptedException");
        }
        return Collections.emptyList();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    static class User {
        private int id;
        private String name;
        private String username;
        private String email;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
