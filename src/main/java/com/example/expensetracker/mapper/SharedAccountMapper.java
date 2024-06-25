package com.example.expensetracker.mapper;

import com.example.expensetracker.model.dto.SharedAccountDto;
import com.example.expensetracker.model.entity.SharedAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SharedAccountMapper {
    SharedAccount toSharedAccount(SharedAccountDto dto);

}
