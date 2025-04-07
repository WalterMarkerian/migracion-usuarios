package com.sportclub.migracion_usuarios.sede.infrastructure.mapper;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import org.springframework.stereotype.Component;

@Component
public class SedeMapper {

    public SedeDTO toDto(Sede sede) {
        if (sede == null) {
            return null;
        }

        SedeDTO sedeDTO = new SedeDTO();
        sedeDTO.setId(sede.getId());
        sedeDTO.setNombre(sede.getNombre());
        sedeDTO.setDireccion(sede.getDireccion());
        sedeDTO.setCiudad(sede.getCiudad());

        return sedeDTO;
    }

    public Sede toEntity(SedeDTO sedeDTO) {
        if (sedeDTO == null) {
            return null;
        }

        Sede sede = new Sede();
        sede.setNombre(sedeDTO.getNombre());
        sede.setDireccion(sedeDTO.getDireccion());
        sede.setCiudad(sedeDTO.getCiudad());

        return sede;
    }

    public void replaceFromDto(SedeDTO dto, Sede entity) {
        if (dto == null || entity == null) return;

        entity.setNombre(dto.getNombre());
        entity.setDireccion(dto.getDireccion());
        entity.setCiudad(dto.getCiudad());
    }
}