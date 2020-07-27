package com.springcloud.mapper.ext;

import com.springcloud.mapper.sysUserMapper;
import com.springcloud.model.sysUser;
import org.apache.ibatis.annotations.Param;


/**
 * Created by Sui, Chengbin on 2017/10/14.
 */
public interface sysUserExtMapper extends sysUserMapper {

    sysUser selectByUsername(@Param("username") String username);

}
