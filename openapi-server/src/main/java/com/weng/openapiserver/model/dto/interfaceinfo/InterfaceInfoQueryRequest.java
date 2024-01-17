package com.weng.openapiserver.model.dto.interfaceinfo;

import com.weng.openapiserver.model.dto.page.PageRequest;
import lombok.Data;

/**
 * 查询请求
 *
 * @author yupi
 */
@Data
public class InterfaceInfoQueryRequest extends PageRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口url地址
     */
    private String url;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 接口状态（0-默认关闭，1-开启）
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long createUser;

}