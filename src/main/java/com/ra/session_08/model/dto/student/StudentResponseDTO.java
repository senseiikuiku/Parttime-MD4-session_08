package com.ra.session_08.model.dto.student;

import com.ra.session_08.model.entity.aClass;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class StudentResponseDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String address;
    private String avatar;
    private String gender;
    private aClass aClass;
}
