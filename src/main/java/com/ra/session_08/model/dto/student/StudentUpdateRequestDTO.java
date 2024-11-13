package com.ra.session_08.model.dto.student;

import com.ra.session_08.model.entity.aClass;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StudentUpdateRequestDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String address;
    private String avatar;
    private boolean gender;
    private Long classId;
}
