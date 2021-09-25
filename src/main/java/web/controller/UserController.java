package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("users", new User());
        return "info";
    }
    @PostMapping("/saveUser")
    public String createUser(@ModelAttribute("users") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("users", userService.getUserById(id));
        return "update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("users") User user, @PathVariable("id") long id) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String delete(@ModelAttribute("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
