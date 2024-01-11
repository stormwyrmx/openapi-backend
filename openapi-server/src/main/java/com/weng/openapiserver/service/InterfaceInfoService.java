package com.weng.openapiserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.openapiserver.model.entity.InterfaceInfo;

/**
* @author weng
* @description 针对表【interface_info(接口信息表)】的数据库操作Service
* @createDate 2024-01-01 16:53:33
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void isQualified(Long id);
}
