package com.ra.session_08.service.aclass;

import com.ra.session_08.model.dto.aclass.ClassRequestDTO;
import com.ra.session_08.model.dto.aclass.ClassResponseDTO;
import com.ra.session_08.model.dto.aclass.ClassUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassService {
    List<ClassResponseDTO> findAll();

    ClassResponseDTO create(ClassRequestDTO classRequestDTO);

    ClassResponseDTO findById(Long id);

    ClassResponseDTO update(ClassUpdateRequestDTO classRequestDTO);

    void delete(Long id);

    //    Search Theo ten class
    List<ClassResponseDTO> searchByClassName(String keyword);

    //    Phan trang
    Page<ClassResponseDTO> paginate(Pageable pageable);
}
