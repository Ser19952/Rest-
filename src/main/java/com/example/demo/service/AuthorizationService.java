package com.example.demo.service;


import com.example.demo.Authorities;
import com.example.demo.exceprion.InvalidCredentials;
import com.example.demo.exceprion.UnauthorizedUser;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorizationService {
   private final UserRepository userRepository;

   public AuthorizationService(UserRepository userRepository){
       this.userRepository = userRepository;
   }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("UNKNOWN USER " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();

    }
}
