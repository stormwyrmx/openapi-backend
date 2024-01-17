package com.weng.openapiserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.openapiserver.model.entity.UserInterfaceInfo;

/**
* @author weng
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Service
* @createDate 2024-01-17 13:16:18
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    UserInterfaceInfo isExist(Long id);
}
