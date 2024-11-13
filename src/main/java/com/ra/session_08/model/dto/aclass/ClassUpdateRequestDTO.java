package com.ra.session_08.model.dto.aclass;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ClassUpdateRequestDTO {
    private Long id;
    private String className;
    private boolean status;
}
