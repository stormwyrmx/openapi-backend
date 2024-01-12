package com.weng.openapiinterface.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable
{
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * api签名认证
     */
    private String apiKey;

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

    /**
     * 0-正常，1-被删除
     */
    private Integer deleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}