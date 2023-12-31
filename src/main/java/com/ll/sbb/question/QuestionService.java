package com.ll.sbb.question;

import com.ll.sbb.global.exception.DataNotFoundException;
import com.ll.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content, SiteUser siteUser){
        Question question = Question.builder()
                        .subject(subject).content(content).createDate(LocalDateTime.now()).author(siteUser).build();
        questionRepository.save(question);
    }

    public Page<Question> getList(int page){
        Pageable pageable = PageRequest.of(page,10);
        return this.questionRepository.findAll(pageable);
    }

    public Page<Question> getList(int page,String kw){
        Pageable pageable = PageRequest.of(page, 10);
        return this.questionRepository.findAllByKeyword(kw, pageable);
    }

    @Transactional
    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());

    }

    @Transactional
    public void delete(Question question){
        this.questionRepository.delete(question);
    }

    @Transactional
    public void vote(Question question,SiteUser siteUser){
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }



}
