package com.ra.session_08.service.student;

import com.ra.session_08.model.dto.student.StudentRequestDTO;
import com.ra.session_08.model.dto.student.StudentResponseDTO;
import com.ra.session_08.model.dto.student.StudentUpdateRequestDTO;
import com.ra.session_08.model.entity.Student;
import com.ra.session_08.repository.ClassRepository;
import com.ra.session_08.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ClassRepository classRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }

    @Override
    public List<StudentResponseDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> responseDTOS = new ArrayList<>();

        for(Student student : students) {
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
            studentResponseDTO.setId(student.getId());
            studentResponseDTO.setFullName(student.getFullName());
            studentResponseDTO.setPhone(student.getPhone());
            studentResponseDTO.setAddress(student.getAddress());
            studentResponseDTO.setAvatar(student.getAvatar());
            studentResponseDTO.setGender(student.isGender() ? "Nam" : "Nu");
            studentResponseDTO.setAClass(student.getClasses());
            responseDTOS.add(studentResponseDTO);
        }
        return responseDTOS;
    }

    @Override
    public StudentResponseDTO create(StudentRequestDTO studentRequestDTO) {
        Student student = Student.builder()
                .fullName(studentRequestDTO.getFullName())
                .phone(studentRequestDTO.getPhone())
                .address(studentRequestDTO.getAddress())
                .avatar(studentRequestDTO.getAvatar())
                .gender(studentRequestDTO.isGender())
                .classes(classRepository.findById(studentRequestDTO.getClassId()).orElseThrow(() -> new NoSuchElementException("class not found")))// sử dụng repository của class và dùng findById() -> có class mà set vào đây
                .build();
        Student newStudent = studentRepository.save(student);

        return StudentResponseDTO.builder()
                .id(newStudent.getId())
                .fullName(newStudent.getFullName())
                .phone(newStudent.getPhone())
                .address(newStudent.getAddress())
                .avatar(newStudent.getAvatar())
                .gender(newStudent.isGender()?"Nam":"Nu")
                .aClass(newStudent.getClasses())
                .build();
    }

    @Override
    public StudentResponseDTO findById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);

        return StudentResponseDTO.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .phone(student.getPhone())
                .address(student.getAddress())
                .avatar(student.getAvatar())
                .gender(student.isGender()?"Nam":"Nu")
                .aClass(student.getClasses())
                .build();
    }

    @Override
    public StudentResponseDTO update(StudentUpdateRequestDTO studentUpdateRequestDTO) {
        Student student = Student.builder()
                .id(studentUpdateRequestDTO.getId())
                .fullName(studentUpdateRequestDTO.getFullName())
                .phone(studentUpdateRequestDTO.getPhone())
                .address(studentUpdateRequestDTO.getAddress())
                .avatar(studentUpdateRequestDTO.getAvatar())
                .gender(studentUpdateRequestDTO.isGender())
                .classes(classRepository.findById(studentUpdateRequestDTO.getClassId()).orElseThrow(()->new RuntimeException("class not found")))
                .build();
        Student newStudent = studentRepository.save(student);

        return StudentResponseDTO.builder()
                .id(newStudent.getId())
                .fullName(newStudent.getFullName())
                .phone(newStudent.getPhone())
                .address(newStudent.getAddress())
                .avatar(newStudent.getAvatar())
                .gender(newStudent.isGender()?"Nam":"Nu")
                .aClass(newStudent.getClasses())
                .build();
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponseDTO> searchByStudentName(String keyword) {
        List<Student> students = studentRepository.findAllByFullNameContains(keyword);

        return students.stream().map(student -> {
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
            studentResponseDTO.setId(student.getId());
            studentResponseDTO.setFullName(student.getFullName());
            studentResponseDTO.setPhone(student.getPhone());
            studentResponseDTO.setAddress(student.getAddress());
            studentResponseDTO.setAvatar(student.getAvatar());
            studentResponseDTO.setGender(student.isGender()?"Nam":"Nu");
            studentResponseDTO.setAClass(student.getClasses());
            return studentResponseDTO;
        }).toList();
    }


}
