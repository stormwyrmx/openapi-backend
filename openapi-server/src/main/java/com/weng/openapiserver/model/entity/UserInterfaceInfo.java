package com.weng.openapiserver.model.entity;

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

/**
 * 用户调用接口关系表
 * @TableName user_interface_info
 */
@TableName(value ="user_interface_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInterfaceInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 接口调用人
     */
    private Long userId;

    /**
     * 
     */
    private Long interfaceInfoId;

    /**
     * 已经调用的次数
     */
    private Long totalNum;

    /**
     * 剩余调用次数
     */
    private Long leftNum;

    /**
     * 用户调用接口状态（0-正常，1-禁用）
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
     * 
     */
    private Integer deleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}