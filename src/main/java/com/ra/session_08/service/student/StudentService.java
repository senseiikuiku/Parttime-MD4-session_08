package com.ra.session_08.service.student;

import com.ra.session_08.model.dto.student.StudentRequestDTO;
import com.ra.session_08.model.dto.student.StudentResponseDTO;
import com.ra.session_08.model.dto.student.StudentUpdateRequestDTO;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> findAll();
    StudentResponseDTO create(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO findById(Long id);
    StudentResponseDTO update(StudentUpdateRequestDTO studentUpdateRequestDTO);
    void delete(Long id);
//    Search By Name
    List<StudentResponseDTO> searchByStudentName(String keyword);
}
