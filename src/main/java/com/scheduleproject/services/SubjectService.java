package com.scheduleproject.services;

import com.scheduleproject.dto.request.SubjectDtoRequest;
import com.scheduleproject.dto.response.StudentDtoResponse;
import com.scheduleproject.dto.response.SubjectDtoResponse;
import com.scheduleproject.entities.Student;
import com.scheduleproject.entities.Subject;
import com.scheduleproject.repos.SubjectRepository;
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
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    private final ModelMapper mapper;

    @Transactional
    public Subject create(SubjectDtoRequest subjectDtoReq) {
        return subjectRepository.save(mapper.map(subjectDtoReq, Subject.class));
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Subject get(Integer id) {
        return subjectRepository.getReferenceById(id);
    }

    public Subject get(SubjectDtoResponse subjectDtoResp) {
        return subjectRepository.getReferenceById(subjectDtoResp.getId());
    }

    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }

    public void delete(SubjectDtoResponse subjectDtoResp) {
        subjectRepository.deleteById(subjectDtoResp.getId());
    }

    public void delete(Integer subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
