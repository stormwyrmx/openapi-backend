package com.weng.openapiserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.openapiserver.model.dto.auth.LoginRequest;
import com.weng.openapiserver.model.dto.auth.RegisterRequest;
import com.weng.openapiserver.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author weng
* @description 针对表【user】的数据库操作Service
* @createDate 2024-01-01 16:53:33
*/
public interface UserService extends IService<User> {
    User login(LoginRequest loginRequest, HttpServletRequest request);
    String login(LoginRequest loginRequest);

    Long register(RegisterRequest registerRequest);
}
