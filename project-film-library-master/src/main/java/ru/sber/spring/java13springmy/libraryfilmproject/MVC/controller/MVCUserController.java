package ru.sber.spring.java13springmy.libraryfilmproject.MVC.controller;

import org.springframework.ui.Model;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.spring.java13springmy.libraryfilmproject.dto.UserDTO;
import ru.sber.spring.java13springmy.libraryfilmproject.service.UserService;

import static ru.sber.spring.java13springmy.libraryfilmproject.constants.UserRolesConstants.ADMIN;

@Controller
@Hidden
@RequestMapping("/users")
public class MVCUserController {
    private final UserService userService;

    public MVCUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO,
                               BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой email уже существует");
            return "registration";
        }
        userService.create(userDTO);
        return "redirect:login";
    }
}

