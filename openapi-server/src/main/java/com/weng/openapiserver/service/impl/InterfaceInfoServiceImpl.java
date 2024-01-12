package com.weng.openapiserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.weng.openapiclientspringbootstarter.client.OpenApiClient;
import com.weng.openapiserver.common.ResultCodeEnum;
import com.weng.openapiserver.common.RoleEnum;
import com.weng.openapiserver.exception.BusinessException;
import com.weng.openapiserver.mapper.InterfaceInfoMapper;
import com.weng.openapiserver.model.entity.InterfaceInfo;
import com.weng.openapiserver.model.entity.User;
import com.weng.openapiserver.service.InterfaceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* @author weng
* @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
* @createDate 2024-01-01 16:53:33
*/
@Service
@RequiredArgsConstructor
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    private final InterfaceInfoMapper interfaceInfoMapper;
    private final OpenApiClient openApiClient;
    private final Gson gson;
    /**
     * 判断是否存在
     * @param id 接口id
     * @return 根据id查询到的接口信息
     */
    @Override
    public InterfaceInfo isExist(Long id) {
        // 判断是否存在
//        如果没有找到匹配的记录，该方法将返回 null，而不是抛出异常
        InterfaceInfo interfaceInfo = interfaceInfoMapper.selectById(id);
        if (interfaceInfo == null) {
            throw new BusinessException(ResultCodeEnum.NOT_FOUND_ERROR);
        }
//        // 仅本人或管理员可删除
//        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        //不是本人，也不是管理员时，抛出异常
//        if (!interfaceInfo.getUserId().equals(user.getId()) &&
//                !Objects.equals(user.getRole(), RoleEnum.ADMIN.toString())) {
//            throw new BusinessException(ResultCodeEnum.NO_AUTH_ERROR);
//        }
        return interfaceInfo;
    }


    @Override
    public String invokeInterfaceInfo(String method, String url, String requestParam) throws IOException {
        String result = "";
        Map<String,String> map=new HashMap<>();
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        openApiClient.setApiKey(user.getApiKey());
        switch (method)
        {
            case "GET":
                String[] pairs = requestParam.split(",");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    map.put(keyValue[0], keyValue[1]);
                }
                //如果调用的url不存在，返回的是空字符串
                result = openApiClient.doGet(url, map);
                break;
            case "POST":
                map = gson.fromJson(requestParam, Map.class);
                //如果调用的url不存在，返回的是空字符串
                result = openApiClient.doPost4Json(url, map);
                break;
            case "PUT", "DELETE":
            default:
                break;
        }
        return result;
    }
}




