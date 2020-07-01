package com.aia.core.dao;

import com.aia.base.shiro.realm.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<SysUser> {

}
