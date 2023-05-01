package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String printUsers(ModelMap model) {
        model.addAttribute("usersList", userService.listUsers());
        return "users";
    }

    @GetMapping(value = "/addFormUser")
    public String addFormUser() {
        return "add";
    }

    @PostMapping(value = "/addNewUser")
    public RedirectView addNewUser(@RequestParam("name") String name,
                             @RequestParam("last_name") String last_name,
                             @RequestParam("email") String email) {
        User user = new User(name, last_name, email);
        userService.add(user);

        return new RedirectView("users");
    }

    @GetMapping( "users/edit/{id}")
    public String updateFormUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping(value = "/complete")
    public RedirectView updateUser(@ModelAttribute("User") User user) {
        userService.change(user);
        return new RedirectView("users");
    }

    @RequestMapping(value = "users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        userService.remove(user);
        return "redirect:/users";
    }

}
