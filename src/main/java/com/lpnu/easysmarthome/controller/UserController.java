package com.lpnu.easysmarthome.controller;

import com.lpnu.easysmarthome.model.User;
import com.lpnu.easysmarthome.model.enums.Role;
import com.lpnu.easysmarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", Role.values());

        return "/userEdit";
    }

    @PostMapping("/{id}")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userRepository.save(user);
        }
        return "/userEdit";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@ModelAttribute("user") User user,HttpServletRequest request) throws ServletException {
        if(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).equals(user))
            request.logout();
        userRepository.delete(user);

        return "redirect:/logout";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/userList";
    }
}
