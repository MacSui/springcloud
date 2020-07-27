package com.springcloud.controller.impl;

import com.alibaba.fastjson.JSON;
import com.springcloud.controller.sysMenuController;
import com.springcloud.service.sysMenuService;
import com.springcloud.util.enums.returnCode;
import com.springcloud.util.message.returnMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sui, Chengbin on 2017/11/7.
 */
@RestController
@RequestMapping("sysMenu")
public class sysMenuControllerImpl implements sysMenuController {

    @Autowired
    sysMenuService sysmenuService;

    @Override
    @RequestMapping(value="getMenu/{id}", method = RequestMethod.GET)
    @RequiresPermissions(value={"sysMenu:selectByid"})
    public String  selectByid(@PathVariable long id) {
        return JSON.toJSONString (new returnMsg(returnCode.SUCCESS,sysmenuService.selectByid(id)));
    }
}
