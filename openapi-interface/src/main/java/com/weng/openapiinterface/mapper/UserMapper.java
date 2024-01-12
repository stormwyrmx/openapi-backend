package com.weng.openapiinterface.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weng.openapiinterface.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author weng
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-01-01 16:53:33
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User>
{

}




