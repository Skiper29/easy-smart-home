package com.lpnu.easysmarthome.controller;

import com.lpnu.easysmarthome.model.Role;
import com.lpnu.easysmarthome.model.User;
import com.lpnu.easysmarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("passwordMessage", null);
        model.addAttribute("message", null);
        model.addAttribute("emailMessage", null);
        return "/registration";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult,
                          @RequestParam String secondPassword) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        User userFromDbByEmail = userRepository.findByEmail(user.getEmail());
        if (bindingResult.hasErrors() || !user.getPassword().equals(secondPassword) || userFromDb != null || userFromDbByEmail != null) {
            if (!user.getPassword().equals(secondPassword)) {
                bindingResult.addError(new FieldError("passwordError",
                        "password", "The passwords doesn't equals"));
            }
            if (userFromDb != null)
                bindingResult.addError(new FieldError("userError",
                        "username", "User exists!"));
            if (userFromDbByEmail != null) {
                bindingResult.addError(new FieldError("emailError",
                        "email", "User with such email exists!"));
            }
            return "/registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
