package com.scheduleproject.services;

import com.scheduleproject.dto.request.SubjectDtoRequest;
import com.scheduleproject.dto.response.SubjectDtoResponse;
import com.scheduleproject.entities.Subject;
import com.scheduleproject.repos.SubjectRepository;
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
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    private final ModelMapper mapper;

    @Transactional
    public SubjectDtoResponse create(SubjectDtoRequest subjectDtoReq) {
        return mapper.map(subjectRepository.save(mapper.map(subjectDtoReq, Subject.class)),
                SubjectDtoResponse.class);
    }

    public List<SubjectDtoResponse> getAll() {
        return subjectRepository.findAll().stream()
                .map(el->mapper.map(el,SubjectDtoResponse.class))
                .collect(Collectors.toList());
    }

    public SubjectDtoResponse get(Integer id) {
        return mapper.map(subjectRepository.getReferenceById(id), SubjectDtoResponse.class);
    }

    public void delete(Integer subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    public SubjectDtoResponse update(SubjectDtoResponse subjectDtoResp) {
        Subject subject = subjectRepository.getReferenceById(subjectDtoResp.getId());
        subject.setName(subjectDtoResp.getName());
        subjectRepository.save(subject);
        return mapper.map(subject, SubjectDtoResponse.class);
    }
}
