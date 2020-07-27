package com.springcloud.service.impl;


import com.springcloud.mapper.ext.sysUserExtMapper;
import com.springcloud.model.sysUser;
import com.springcloud.service.sysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Sui, Chengbin on 2017/10/14.
 */
@Component
public class sysUserServiceImpl implements sysUserService {

    private Logger logger = LoggerFactory.getLogger(sysUserServiceImpl.class);

    @Autowired
    private sysUserExtMapper sysuserextmapper;

    @Override
    public sysUser selectByid(String id) {
        this.logger.info("selectByid");
        return sysuserextmapper.selectByPrimaryKey(id);
    }


}
