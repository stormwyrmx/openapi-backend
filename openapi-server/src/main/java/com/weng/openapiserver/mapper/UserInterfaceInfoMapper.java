package com.weng.openapiserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.openapiserver.model.entity.UserInterfaceInfo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author weng
* @description 针对表【user_interface_info(用户调用接口关系表)】的数据库操作Mapper
* @createDate 2024-01-17 13:16:18
* @Entity generator.domain.UserInterfaceInfo
*/
@Mapper
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

}




