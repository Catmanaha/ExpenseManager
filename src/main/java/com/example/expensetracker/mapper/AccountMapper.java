package com.example.expensetracker.mapper;

import com.example.expensetracker.mapper.models.AccountMapperModel;
import com.example.expensetracker.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "sharedAccount", ignore = true)
    @Mapping(target = "version", ignore = true)
    Account toAccount(AccountMapperModel mm);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "sharedAccount", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateAccountFromMm(AccountMapperModel mm, @MappingTarget Account account);
}
