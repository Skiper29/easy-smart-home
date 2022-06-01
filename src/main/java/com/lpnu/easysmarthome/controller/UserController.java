package com.lpnu.easysmarthome.controller;

import com.lpnu.easysmarthome.model.User;
import com.lpnu.easysmarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        return "/userEdit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userRepository.save(user);
        }
        return "/userEdit";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/userList";
    }
}
