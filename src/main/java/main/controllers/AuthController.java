package main.controllers;

import main.view.requests.RestoreRequest;
import main.view.service.AuthService;
import main.view.requests.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// искусственный коммит 9.5.22
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final LoginRequest loginRequest;

    public AuthController(AuthService authService, LoginRequest loginRequest) {
        this.authService = authService;
        this.loginRequest = loginRequest;
    }

    @GetMapping("/check")
    private ResponseEntity<?> getAuthCheck () {
        System.out.println("Method getAuthCheck is activated.");
        return authService.getAuthCheck();
    }

    @PostMapping (value = "/login")
    private ResponseEntity<?> postAuthLogin(@RequestBody LoginRequest loginRequest) {
        System.out.println("Method postAuthLogin is activated.");
        return authService.postAuthLogin(loginRequest);
    }

    @GetMapping("/logout")
    private ResponseEntity<?> getAuthLogout () {
        System.out.println("Method getAuthLogout is activated.");
        return authService.getAuthLogout();
    }

    @GetMapping("/captcha")
    private ResponseEntity<?> getCaptcha () {
        System.out.println("Method getCaptcha is activated.");
        return authService.getCaptcha();
    }

    @PostMapping("/register")
    private ResponseEntity<?> postAuthRegister(@RequestBody LoginRequest loginRequest){
        System.out.println("Method postAuthRegister is activated.");
        return authService.postAuthRegister(loginRequest);
    }

    @PostMapping("/restore")
    private ResponseEntity<?> authRestore (@RequestBody RestoreRequest restoreRequest) {
        System.out.println("Method authRestore is activated.");
        return authService.authRestore(restoreRequest.getEmail());
    }

    @PostMapping("/password")
    private ResponseEntity<?> authPassword (String code, String password, String captcha, String captchaSecret) {
        System.out.println("Method authPassword is activated.");
        return authService.authPassword(code, password, captcha, captchaSecret);
    }
}

