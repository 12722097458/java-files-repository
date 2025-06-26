package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.Account;

import java.util.List;

public interface AccountMapper {

    List<Account> selectAllAccounts();

    Account selectAccountById(int uid);

    int updateAccount(Account account);
}
