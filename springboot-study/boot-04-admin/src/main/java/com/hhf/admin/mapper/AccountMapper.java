package com.hhf.admin.mapper;

import com.hhf.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    public Account getAccount(long id);
}
