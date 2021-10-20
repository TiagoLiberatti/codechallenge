package com.tiagodev.codechallenge.dataprovider.mapper;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.dataprovider.entity.ContaBancariaTable;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContaBancariaMapper {

    ContaBancariaMapper INSTANCE = Mappers.getMapper(ContaBancariaMapper.class);

    ContaBancariaEntity tableToEntity(ContaBancariaTable contaBancariaTable);

    ContaBancariaTable entityListToTableList(ContaBancariaEntity contaBancariaEntity);
}