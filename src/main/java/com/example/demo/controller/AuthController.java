package com.example.demo.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.LoginForm;
import com.example.demo.dto.RegisterForm;
import com.example.demo.entity.AuthToken;
import com.example.demo.exception.PasswordMismatchException;
import com.example.demo.exception.UserDuplicateException;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private HttpSession session;

    @ModelAttribute("loginForm")
    private LoginForm loginForm() {
        return new LoginForm();
    }

    @ModelAttribute("registerForm")
    private RegisterForm registerForm() {
        return new RegisterForm();
    }

    public AuthController(AuthService authService, UserService userService, HttpSession session) {
        this.authService = authService;
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "auth/index";
    }

    // resultとmodelの順番を逆にするとに400 Bad Request が発生する
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.info("");

            for (var error : result.getAllErrors()) {
                log.info(error.getDefaultMessage());
            }

            return "auth/index";
        }

        Optional<AuthToken> auth = authService.login(loginForm.getId(), loginForm.getPassword());

        if (auth.isEmpty()) {
            model.addAttribute("msg", "ログインに失敗しました");
            return "auth/index";
        } else {
            session.setAttribute("auth", auth.get());
            return "redirect:/todos";
        }

    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerForm") RegisterForm registerForm, BindingResult result,
            Model model)
            throws NoSuchAlgorithmException {

        if (result.hasErrors()) {
            for (var error : result.getAllErrors()) {
                log.info(error.getDefaultMessage());
            }

            return "auth/register";
        }

        try {
            userService.register(registerForm);
        } catch (UserDuplicateException e) {
            log.debug(e.getMessage());

            model.addAttribute("msg", "すでに登録されてます");
            return "auth/register";
        } catch (PasswordMismatchException e) {
            log.debug(e.getMessage());
            model.addAttribute("msg", "入力したパスワードが一致しません");
            return "auth/register";
        }

        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String getMethodName() {

        session.removeAttribute("auth");

        return "redirect:/auth/login";
    }

}