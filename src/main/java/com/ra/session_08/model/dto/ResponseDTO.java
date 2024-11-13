package com.ra.session_08.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ResponseDTO <T>{
    private int httpStatus;
    private String message;
    private T data;
}
