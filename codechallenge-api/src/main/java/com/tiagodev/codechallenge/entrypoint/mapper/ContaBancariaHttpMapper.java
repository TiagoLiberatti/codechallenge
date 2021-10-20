package com.tiagodev.codechallenge.entrypoint.mapper;

import com.tiagodev.codechallenge.core.entity.ContaBancariaEntity;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaHttpModel;
import com.tiagodev.codechallenge.entrypoint.entity.ContaBancariaHttpResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContaBancariaHttpMapper {

    ContaBancariaHttpMapper INSTANCE = Mappers.getMapper(ContaBancariaHttpMapper.class);

    ContaBancariaHttpResponse entityToHttpResponse(ContaBancariaEntity contaBancariaEntity);

    @Mapping(target = "id", ignore = true)
    ContaBancariaEntity httpModelToEntity(ContaBancariaHttpModel contaBancariaHttpModel);
}
