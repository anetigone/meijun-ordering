package com.mo.service.mapper;

import com.mo.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MerchantMapper {
    Merchant getMerchantByUsername(String username);
    Merchant getMerchantByUuid(String uuid);

    void addMerchant(Merchant merchant);
}
