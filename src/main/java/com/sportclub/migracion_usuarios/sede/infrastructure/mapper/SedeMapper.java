package com.sportclub.migracion_usuarios.sede.infrastructure.mapper;

import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import org.springframework.stereotype.Component;

@Component
public class SedeMapper {

    // Método para convertir de Sede a SedeDTO
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

    // Método para convertir de SedeDTO a Sede
    public Sede toEntity(SedeDTO sedeDTO) {
        if (sedeDTO == null) {
            return null;
        }

        Sede sede = new Sede();
        sede.setId(sedeDTO.getId());
        sede.setNombre(sedeDTO.getNombre());
        sede.setDireccion(sedeDTO.getDireccion());
        sede.setCiudad(sedeDTO.getCiudad());

        return sede;
    }

    // Método para actualizar la entidad Sede usando los datos del DTO
    public void updateFromDto(SedeDTO sedeDTO, Sede sede) {
        if (sedeDTO == null || sede == null) {
            return;
        }

        // Actualizar los campos de la entidad Sede usando los valores del DTO
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
