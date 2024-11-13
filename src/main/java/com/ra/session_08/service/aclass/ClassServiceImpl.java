package com.ra.session_08.service.aclass;

import com.ra.session_08.model.dto.aclass.ClassRequestDTO;
import com.ra.session_08.model.dto.aclass.ClassResponseDTO;
import com.ra.session_08.model.dto.aclass.ClassUpdateRequestDTO;
import com.ra.session_08.model.entity.aClass;
import com.ra.session_08.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    @Autowired
    public ClassServiceImpl(final ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public List<ClassResponseDTO> findAll() {
        // convert Entity và DTO
        List<aClass> classes = classRepository.findAll();
        List<ClassResponseDTO> responseDTOS = new ArrayList<>();
        for(aClass aClass : classes) {
            ClassResponseDTO responseDTO = ClassResponseDTO.builder()
                    .id(aClass.getId())
                    .className(aClass.getClassName())
                    .status(aClass.isStatus())
                    .build();
            responseDTOS.add(responseDTO);
        }
         return responseDTOS;
    }

    @Override
    public ClassResponseDTO create(ClassRequestDTO classRequestDTO) {
        aClass class1 = aClass.builder()
                .className(classRequestDTO.getClassName())
                .status(classRequestDTO.isStatus())
                .build();
        aClass classNew = classRepository.save(class1);

        return ClassResponseDTO.builder()
                .id(classNew.getId())
                .className(classNew.getClassName())
                .status(classNew.isStatus())
                .build();
    }

    @Override
    public ClassResponseDTO findById(Long id) {
        aClass aClass = classRepository.findById(id).orElse(null);

        return ClassResponseDTO.builder()
                .id(aClass.getId())
                .className(aClass.getClassName())
                .status(aClass.isStatus())
                .build();
    }

    @Override
    public ClassResponseDTO update(ClassUpdateRequestDTO classRequestDTO) {
        aClass class1 = aClass.builder()
                .id(classRequestDTO.getId())
                .className(classRequestDTO.getClassName())
                .status(classRequestDTO.isStatus())
                .build();
        aClass classNew = classRepository.save(class1);

        return ClassResponseDTO.builder()
                .id(classNew.getId())
                .className(classNew.getClassName())
                .status(classNew.isStatus())
                .build();
    }

    @Override
    public void delete(Long id) {
        classRepository.deleteById(id);
    }

    @Override
    public List<ClassResponseDTO> searchByClassName(String keyword) {
        List<aClass> classes = classRepository.findAllByClassNameContains(keyword);

        return classes.stream().map(class1 -> {
            ClassResponseDTO classResponseDTO = new ClassResponseDTO();
            classResponseDTO.setId(class1.getId());
            classResponseDTO.setClassName(class1.getClassName());
            classResponseDTO.setStatus(class1.isStatus());
            return classResponseDTO;
        }).toList();
    }

    @Override
    public Page<ClassResponseDTO> paginate(Pageable pageable) {
        Page<aClass> classes = classRepository.findAll(pageable);
        return classes.map(class1->{
            ClassResponseDTO classResponseDTO = new ClassResponseDTO();
            classResponseDTO.setId(class1.getId());
            classResponseDTO.setClassName(class1.getClassName());
            classResponseDTO.setStatus(class1.isStatus());
            return classResponseDTO;
        });
    }
}
