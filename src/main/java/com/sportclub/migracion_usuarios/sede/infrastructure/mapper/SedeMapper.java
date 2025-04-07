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

    // Modificado para ignorar el ID al crear una nueva entidad
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

    public void updateFromDto(SedeDTO sedeDTO, Sede sede) {
        if (sedeDTO == null || sede == null) {
            return;
        }

        if (sedeDTO.getNombre() != null) {
            sede.setNombre(sedeDTO.getNombre());
        }
        if (sedeDTO.getDireccion() != null) {
            sede.setDireccion(sedeDTO.getDireccion());
        }
        if (sedeDTO.getCiudad() != null) {
            sede.setCiudad(sedeDTO.getCiudad());
        }
    }
}