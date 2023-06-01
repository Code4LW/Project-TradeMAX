package com.example.trademax.controllers;


import com.example.trademax.models.User;
import com.example.trademax.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String logIn(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }
    @GetMapping("/signup")
    public String signUp(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "signup";
    }
    @PostMapping("/signup")
    public String createUser(User user, Model model) {
     if (!userService.createUser(user)) {
         model.addAttribute("Error", "The username " + user.getEmail()
                 + " is already taken");
         return "signup";
     }
    return "redirect:/login";
    }
    @GetMapping("/profile")
    public String getProfile(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "my-profile";
    }
    @GetMapping("/user/{user}")
    public String getUserInfo(@PathVariable("user") User user, Model model, Principal principal){
     model.addAttribute("user", user);
     model.addAttribute("items", user.getItems());
     model.addAttribute("userPrincipal", userService.getUserByPrincipal(principal));
     return "user-info";
    }
}
