package com.example.Demo_Spring_Boot.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PageResponse<T> {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long total;
    private List<T> data;

}
