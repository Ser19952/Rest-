package com.example.demo.controller;

import com.example.demo.Authorities;
import com.example.demo.exceprion.InvalidCredentials;
import com.example.demo.exceprion.UnauthorizedUser;
import com.example.demo.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorizationController {
   private final AuthorizationService service;

    public  AuthorizationController(AuthorizationService service) {
        this.service = service;
    }


    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("User") String user, @RequestParam("Password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentials e) {
        System.out.println("EXCEPTION: " + e.getMessage());
        return new ResponseEntity<>("EXCEPTION: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> handleUnauthorizedUser(UnauthorizedUser e) {
        System.out.println("EXCEPTION: " + e.getMessage());
        return new ResponseEntity<>("EXCEPTION:" + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
