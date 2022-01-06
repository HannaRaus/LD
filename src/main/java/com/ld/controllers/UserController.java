package com.ld.controllers;

import com.ld.model.User;
import com.ld.services.UserService;
import com.ld.validation.UserValidationService;
import com.ld.validation.ValidateResponse;
import com.ld.validation.ValidateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserValidationService validationService;

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    @ResponseBody
    public ValidateResponse registration(@RequestBody ValidateUserRequest request) {
        ValidateResponse response = validationService.validate(request);
        if (response.isSuccess()) {
            register(request);
        }
        return response;
    }

    @ModelAttribute("userForm")
    public User defaultUser() {
        return new User();
    }

    private void register(ValidateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());
        user.setSendNotification(request.isSendNotifications());
        user.setActive(true);
        service.save(user);
    }
}
