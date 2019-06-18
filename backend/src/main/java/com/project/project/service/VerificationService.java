package com.project.project.service;

import com.project.project.dto.UserRegistrationDTO;
import com.project.project.exceptions.UserAlreadyVerified;
import com.project.project.exceptions.UserNotFound;
import com.project.project.model.User;
import com.project.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationService {

    @Autowired
    private UserRepository userRepository;

    @Value("${frontend}")
    private String frontend;

    public String verify(Long id) throws UserNotFound, UserAlreadyVerified {
        Optional<User> u = userRepository.findOneById(id);

        if(!u.isPresent()) {
            throw new UserNotFound(id);
        }

        if(u.get().isVerified()) {
            throw new UserAlreadyVerified();
        }

        u.get().setVerified(true);

        userRepository.save(u.get());

        String response = "";
        response += "<html><body>";
        response += "<p>Account successfully verified.</p>";
        response += "<p>You can now log in here: ";
        response += "<a href='" + frontend + "/login'>Log in</a></p>";
        response += "</body></html>";

        return response;
    }
}
