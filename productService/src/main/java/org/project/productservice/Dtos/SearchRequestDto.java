package org.project.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String query ;
    private int pageNumber ;
    private int itemsperpage ;
    private List<SortParam> sortParams ;
}
