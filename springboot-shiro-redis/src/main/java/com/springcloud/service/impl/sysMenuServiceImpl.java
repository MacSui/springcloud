package com.springcloud.service.impl;

import com.springcloud.mapper.ext.sysMenuExtMapper;
import com.springcloud.model.sysMenu;
import com.springcloud.service.sysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Sui, Chengbin on 2017/11/7.
 */
@Component
public class sysMenuServiceImpl implements sysMenuService {


    @Autowired
    private sysMenuExtMapper sysmenumapper;

    @Override
    public sysMenu selectByid(Long id) {
        return  sysmenumapper.selectByPrimaryKey(id);
    }
}
