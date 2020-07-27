package com.springcloud.controller.impl;

import com.alibaba.fastjson.JSON;
import com.springcloud.controller.sysUserController;

import com.springcloud.service.sysUserService;
import com.springcloud.util.enums.returnCode;
import com.springcloud.util.message.returnMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sui, Chengbin on 2017/10/15.
 */
@RestController
@RequestMapping("sysUser")
public class sysUserControllerImpl implements sysUserController {

    private Logger logger = LoggerFactory.getLogger(sysUserControllerImpl.class);

    @Autowired
    sysUserService sysuserService;

    @Override
    @RequestMapping(value="getUser/{id}", method = RequestMethod.GET)
    @RequiresPermissions(value={"sysUser:selectByid"})
    public String  selectByid(@PathVariable String id) {
        this.logger.info("selectByid");
        return JSON.toJSONString (new returnMsg(returnCode.SUCCESS,sysuserService.selectByid(id)));
    }
}
