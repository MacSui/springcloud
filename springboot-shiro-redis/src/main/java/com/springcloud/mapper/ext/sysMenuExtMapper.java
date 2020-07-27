package com.springcloud.mapper.ext;

import com.springcloud.mapper.sysMenuMapper;
import com.springcloud.model.sysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Sui, Chengbin on 2017-11-07.
 */
public interface sysMenuExtMapper extends sysMenuMapper {
    List<sysMenu> selectByUserId(@Param("id") String id);
}
