package org.project.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {
    private HttpStatus httpStatus ;
    private String httpMessage ;
}
