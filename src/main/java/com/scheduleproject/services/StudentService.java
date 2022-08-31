package com.scheduleproject.services;

import com.scheduleproject.dto.request.StudentDtoRequest;
import com.scheduleproject.dto.response.StudentDtoResponse;
import com.scheduleproject.entities.Student;
import com.scheduleproject.repos.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    private final ModelMapper mapper;

    @Transactional
    public Student create(StudentDtoRequest studentReq) {
        return studentRepository.save(mapper.map(studentReq, Student.class));
    }

    public List<StudentDtoResponse> getAll() {
        return studentRepository.findAll().stream()
                .map(el->mapper.map(el,StudentDtoResponse.class))
                .collect(Collectors.toList());
    }

    public StudentDtoResponse get(Integer id) {
        return mapper.map(studentRepository.getReferenceById(id), StudentDtoResponse.class);
    }

    public Student get(StudentDtoResponse studentDtoResp) {
        return mapper.map(studentRepository.getReferenceById(studentDtoResp.getId()), Student.class);
    }

    public Student getById(Integer studentId) {
        return studentRepository.getReferenceById(studentId);
    }

    @Transactional
    public StudentDtoResponse update(StudentDtoResponse studentDtoResp) {
        Student student = studentRepository.getReferenceById(studentDtoResp.getId());
        student.setName(studentDtoResp.getName());
        student.setSurname(studentDtoResp.getSurname());
        return mapper.map(student, StudentDtoResponse.class);
    }

    @Transactional
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Transactional
    public void delete(StudentDtoResponse studentDtoResp) {
        studentRepository.deleteById(studentDtoResp.getId());
    }

    @Transactional
    public void delete(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
