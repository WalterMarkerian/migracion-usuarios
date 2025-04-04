package com.sportclub.migracion_usuarios.sede.infrastructure.mapper;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SedeMapper {

    SedeMapper INSTANCE = Mappers.getMapper(SedeMapper.class);

    @Mapping(target = "usuarios", ignore = true)
    SedeDTO toDto(Sede sede);

    @Mapping(target = "usuarios", ignore = true)
    Sede toEntity(SedeDTO sedeDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(SedeDTO sedeDTO, @MappingTarget Sede sede);
}