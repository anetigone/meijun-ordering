package com.mo.service.mapper;

import com.mo.entity.Admin;
import com.mo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admins where username = #{username}")
    Admin getAdminByUsername(String username);
}
