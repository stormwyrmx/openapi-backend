package com.weng.openapiserver.controller;

import com.weng.openapiserver.common.Result;
import com.weng.openapiserver.model.dto.userinterfaceinfo.UserInterfaceInfoAddRequest;
import com.weng.openapiserver.model.dto.userinterfaceinfo.UserInterfaceInfoUpdateRequest;
import com.weng.openapiserver.model.entity.User;
import com.weng.openapiserver.model.entity.UserInterfaceInfo;
import com.weng.openapiserver.model.vo.UserVO;
import com.weng.openapiserver.service.UserInterfaceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserInterfaceInfoService userInterfaceInfoService;

    /**
     * 获取当前登录用户
     *
     * @param
     * @return
     */
    @GetMapping("/current")
    public Result<UserVO> getCurrentUser(@AuthenticationPrincipal User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    /**
     * 用户开通特定接口调用权限
     * @return
     */
    @PostMapping("/userInterfaceInfo")
    public Result<Long> addUserInterfaceInfo(@RequestBody @Validated UserInterfaceInfoAddRequest userInterfaceInfoAddRequest)
    {
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoAddRequest, userInterfaceInfo);
        //新增
        userInterfaceInfoService.save(userInterfaceInfo);//MyBatis-Plus 会直接抛出异常，返回的false没什么用。
        return Result.success(userInterfaceInfo.getId());
    }

    /**
     * 修改用户接口调用信息，增加剩余调用次数或者禁用用户调用特定接口
     * @return
     */
    @PutMapping("/userInterfaceInfo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Boolean> updateUserInterfaceInfo(@RequestBody @Validated UserInterfaceInfoUpdateRequest userInterfaceInfoUpdateRequest)
    {
        //判断是否存在
        userInterfaceInfoService.isExist(userInterfaceInfoUpdateRequest.id());

        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(userInterfaceInfoUpdateRequest, userInterfaceInfo);
        userInterfaceInfo.setUpdateTime(LocalDateTime.now());
        //修改,增加剩余调用次数或者禁用用户调用特定接口
        boolean flag = userInterfaceInfoService.updateById(userInterfaceInfo);//MyBatis-Plus 会直接抛出异常，返回的false没什么用。
        return Result.success(flag);
    }

}
