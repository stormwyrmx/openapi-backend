package com.weng.openapiserver.controller;

import com.weng.openapiserver.common.Result;
import com.weng.openapiserver.model.dto.auth.LoginRequest;
import com.weng.openapiserver.model.dto.auth.RegisterRequest;
import com.weng.openapiserver.model.entity.User;
import com.weng.openapiserver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final UserService userService;
    @PostMapping("/login")
    public Result<User> login(@RequestBody @Validated LoginRequest loginRequest, HttpServletRequest request)
    {
        User user = userService.login(loginRequest, request);
        return Result.success(user);
//        String token = userService.login(loginRequest);
//        return Result.success(token);
    }

    @PostMapping("/register")
    public Result<Long> register(@RequestBody @Validated RegisterRequest registerRequest)
    {
        Long id=userService.register(registerRequest);
        return Result.success(id);
    }

}
