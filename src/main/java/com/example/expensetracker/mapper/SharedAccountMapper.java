package com.example.expensetracker.mapper;

import com.example.expensetracker.model.dto.SharedAccountDto;
import com.example.expensetracker.model.entity.SharedAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SharedAccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    SharedAccount toSharedAccount(SharedAccountDto dto);

}
