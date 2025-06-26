package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {

    public Account selectById(Integer id);
    public Integer updateAccount(Account account);
}
