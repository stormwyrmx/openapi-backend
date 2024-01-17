package com.weng.openapiserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.openapiserver.common.ResultCodeEnum;
import com.weng.openapiserver.exception.BusinessException;
import com.weng.openapiserver.mapper.UserInterfaceInfoMapper;
import com.weng.openapiserver.model.entity.UserInterfaceInfo;
import com.weng.openapiserver.service.UserInterfaceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author weng
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service实现
* @createDate 2024-01-17 13:16:18
*/
@Service
@RequiredArgsConstructor
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService
{
    private final UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Override
    public UserInterfaceInfo isExist(Long id)
    {
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectById(id);
        if (userInterfaceInfo == null) {
            throw new BusinessException(ResultCodeEnum.NOT_FOUND_ERROR);
        }
        return userInterfaceInfo;
    }
}




