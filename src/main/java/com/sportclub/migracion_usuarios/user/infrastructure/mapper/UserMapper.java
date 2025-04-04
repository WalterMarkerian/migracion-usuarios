package com.sportclub.migracion_usuarios.user.infrastructure.mapper;

import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "sede", ignore = true)
        // Ignoramos la relación bidireccional
    UserDTO toDto(User user);

    @Mapping(target = "sede", ignore = true)
        // Ignoramos la relación bidireccional
    User toEntity(UserDTO userDTO);

    @Mapping(target = "id", ignore = true) // Para updates, no cambiamos el ID
    @Mapping(target = "sede", ignore = true) // La sede se maneja aparte
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UserDTO userDTO, @MappingTarget User user);
}