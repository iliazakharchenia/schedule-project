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

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student get(Integer id) {
        return studentRepository.getReferenceById(id);
    }

    public Student get(StudentDtoResponse studentDtoResp) {
        return studentRepository.getReferenceById(studentDtoResp.getId());
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public void delete(StudentDtoResponse studentDtoResp) {
        studentRepository.deleteById(studentDtoResp.getId());
    }

    public void delete(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
