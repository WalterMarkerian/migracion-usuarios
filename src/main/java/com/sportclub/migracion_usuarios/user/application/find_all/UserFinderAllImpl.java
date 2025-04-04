package com.sportclub.migracion_usuarios.user.application.find_all;

import com.sportclub.migracion_usuarios.commons.dto.PageResponseDTO;
import com.sportclub.migracion_usuarios.commons.helpers.GeneratePrevNextPage;
import com.sportclub.migracion_usuarios.user.domain.dto.UserDTO;
import com.sportclub.migracion_usuarios.user.domain.entity.User;
import com.sportclub.migracion_usuarios.user.infrastructure.mapper.UserMapper;
import com.sportclub.migracion_usuarios.user.infrastructure.repository.UserRepository;
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
public class UserFinderAllImpl implements UserFinderAll {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HttpServletRequest request;

    @Override
    public PageResponseDTO<UserDTO> findAll(Integer page, Integer pageSize, String sort) {

        String host = request.getHeader("Host");

        List<UserDTO> usersDTO = new ArrayList<>();

        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : page, pageSize, Sort.by(Sort.Direction.ASC, sort));
        Page<User> users = userRepository.findAll(pageable);


        for (User user : users) {
            usersDTO.add(userMapper.toDto(user));
        }
        System.out.println(page);


        GeneratePrevNextPage prevNext = new GeneratePrevNextPage(host, page, pageSize, (int) users.getTotalPages(), GeneratePrevNextPage.Path.USUARIOS, null);


        return new PageResponseDTO<>(
                usersDTO,
                users.getTotalPages(),
                users.getTotalElements(),
                prevNext.nextPageAndPrev()[1],
                prevNext.nextPageAndPrev()[0]
        );


    }


}