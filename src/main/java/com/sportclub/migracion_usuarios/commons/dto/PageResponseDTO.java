package com.sportclub.migracion_usuarios.commons.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content;
    private int totalPages;
    private long totalRecords;
    private String prevPage;
    private String nextPage;

    public PageResponseDTO(List<T> content, int totalPages, long totalRecords, String prevPage, String nextPage) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
        this.prevPage = prevPage;
        this.nextPage = nextPage;

    }
}

