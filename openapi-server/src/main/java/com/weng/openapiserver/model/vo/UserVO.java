package com.weng.openapiserver.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 
 * @TableName user
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO
{
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String username;


    /**
     * 用户角色
     */
    private String role;

    /**
     * 用户状态(0-正常，1-禁用)
     */
    private Integer status;

    /**
     * 
     */
    private LocalDateTime createTime;

    /**
     * 
     */
    private LocalDateTime updateTime;


}