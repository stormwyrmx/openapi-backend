package com.weng.openapiserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weng.openapiserver.common.Result;
import com.weng.openapiserver.model.dto.interfaceinfo.InterfaceInfoAddRequest;
import com.weng.openapiserver.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.weng.openapiserver.model.dto.interfaceinfo.InterfaceInfoUpdateRequest;
import com.weng.openapiserver.model.dto.page.PageRequest;
import com.weng.openapiserver.model.entity.InterfaceInfo;
import com.weng.openapiserver.model.entity.User;
import com.weng.openapiserver.service.InterfaceInfoService;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
@Validated
@RequiredArgsConstructor
public class InterfaceInfoController
{
    private final InterfaceInfoService interfaceInfoService;

    // region 增删改查

    /**
     * 增加
     *
     * @param interfaceInfoAddRequest
     * @return
     */
    @PostMapping
    public Result<Long> addInterfaceInfo(@RequestBody @Validated InterfaceInfoAddRequest interfaceInfoAddRequest,
                                         @AuthenticationPrincipal User user){
        //@RequestBody默认不接受空的数据，也就是什么都不传=null。
        //如果开启了required = false，那么可以接受null。这个时候如果不传请求体，那么interfaceInfoAddParam为null
        //@Validated就不起作用。只有当不为null时，才会起作用
        //如果传了空json，@RequestBody就会默认会拆成interfaceInfoAddParam(name=null,age=null...)这个时候@Validated就起作用了
        /**
         * controller层倾向于对请求参数本身的校验(所有都要)，不涉及业务逻辑本身(越少越好)
         * service层是对业务逻辑的校验（有可能被controller 之外的类调用)
         */
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        //新增
        interfaceInfo.setUserId(user.getId());
        interfaceInfoService.save(interfaceInfo);//MyBatis-Plus 会直接抛出异常，返回的false没什么用。
        return Result.success(interfaceInfo.getId());
    }

    /**
     * 删除
     *
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    //因为是路径参数，所以id一定会有值，不可能为null，所以这里校验是否大于0即可
    public Result<Boolean> deleteInterfaceInfo(@Min(value = 1,message = "id必须大于0") @PathVariable Long id) {
        //判断是否能够删除
        interfaceInfoService.isQualified(id);
        boolean result = interfaceInfoService.removeById(id);
        return Result.success(result);
    }

    /**
     * 修改
     *
     * @param interfaceInfoUpdateRequest
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")/*or #interfaceInfo.userId == authentication.principal.id*/
    public Result<Boolean> updateInterfaceInfo(@Validated @RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest) {
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
        //参数校验(这里已经被@Validated注解校验过了)
//        interfaceInfoService.validInterfaceInfo(interfaceInfo);
        //判断是否能够修改
        interfaceInfoService.isQualified(interfaceInfo.getId());
        interfaceInfo.setUpdateTime(LocalDateTime.now());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return Result.success(result);
    }

    /**
     * 根据 id 查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<InterfaceInfo> getInterfaceInfoById(@Min(value = 1,message = "id必须大于0")
                                                          @PathVariable Long id) {
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        return Result.success(interfaceInfo);
    }

    /**
     * 获取列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result<List<InterfaceInfo>> listInterfaceInfo(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        LambdaQueryWrapper<InterfaceInfo>lambdaQueryWrapper=new LambdaQueryWrapper<>();

        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(lambdaQueryWrapper);
        return Result.success(interfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     *
     * @param pageRequest
     * @return
     */
    @GetMapping("/page")//pageRequest不可能为null，只可能为pageRequest(size=null,current=null)
    public Result<Page<InterfaceInfo>> listInterfaceInfoByPage(@Validated PageRequest pageRequest) {
        //请求参数进来先拼成一个对象，如果不传那么默认会拼成pageRequest(size=null,current=null)
        //这个时候@Validated注解就会校验size和current。所以这里@NotNull注解就不起作用了，因为pageRequest不为null
        LambdaQueryWrapper<InterfaceInfo>interfaceInfoLambdaQueryWrapper=new LambdaQueryWrapper<>();
        interfaceInfoLambdaQueryWrapper.orderByAsc(InterfaceInfo::getCreateTime);
        Page<InterfaceInfo>interfaceInfoPage=new Page<>(pageRequest.getCurrent(),pageRequest.getSize());
        interfaceInfoService.page(interfaceInfoPage,interfaceInfoLambdaQueryWrapper);
        return Result.success(interfaceInfoPage);
    }

    // endregion

}
