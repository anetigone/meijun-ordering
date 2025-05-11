package com.mo.service.mapper;

import com.mo.entity.Admin;
import com.mo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    Admin getAdminByUsername(String username);
    Admin getAdminByUuid(String uuid);
}
