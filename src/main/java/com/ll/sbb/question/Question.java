package com.ll.sbb.question;

import com.ll.sbb.answer.Answer;
import com.ll.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUser> voter;


}
