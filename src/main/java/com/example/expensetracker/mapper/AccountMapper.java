package com.example.expensetracker.mapper;

import com.example.expensetracker.mapper.models.AccountMapperModel;
import com.example.expensetracker.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountMapperModel mm);
    void updateAccountFromMm(AccountMapperModel mm, @MappingTarget Account account);
}
