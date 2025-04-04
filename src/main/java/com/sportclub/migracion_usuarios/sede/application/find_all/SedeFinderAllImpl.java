package com.sportclub.migracion_usuarios.sede.application.find_all;

import com.sportclub.migracion_usuarios.commons.dto.PageResponseDTO;
import com.sportclub.migracion_usuarios.commons.helpers.GeneratePrevNextPage;
import com.sportclub.migracion_usuarios.sede.domain.dto.SedeDTO;
import com.sportclub.migracion_usuarios.sede.domain.entity.Sede;
import com.sportclub.migracion_usuarios.sede.infrastructure.mapper.SedeMapper;
import com.sportclub.migracion_usuarios.sede.infrastructure.repository.SedeRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SedeFinderAllImpl implements SedeFinderAll {

    private final HttpServletRequest request;
    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    @Override
    public PageResponseDTO<SedeDTO> findAll(Integer page, Integer pageSize, String sort) {

        String host = request.getHeader("Host");

        List<SedeDTO> sedeDTOS = new ArrayList<>();

        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : page, pageSize, Sort.by(Sort.Direction.ASC, sort));
        Page<Sede> sedes = sedeRepository.findAll(pageable);

        for (Sede sede : sedes) {
            sedeDTOS.add(sedeMapper.toDto(sede));
        }

        GeneratePrevNextPage prevNext = new GeneratePrevNextPage(
                host,
                page,
                pageSize,
                (int) sedes.getTotalPages(),
                GeneratePrevNextPage.Path.SEDES,
                null
        );


        return new PageResponseDTO<>(
                sedeDTOS,
                sedes.getTotalPages(),
                sedes.getTotalElements(),
                prevNext.nextPageAndPrev()[1],
                prevNext.nextPageAndPrev()[0]
        );
    }
}
