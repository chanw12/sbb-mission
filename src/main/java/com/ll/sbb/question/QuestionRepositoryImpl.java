package com.ll.sbb.question;
import com.ll.sbb.QQuestion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class QuestionRepositoryImpl  implements QuestionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public QuestionRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Question findBycontent(String content) {
        QQuestion question = QQuestion.question;

        return jpaQueryFactory.selectFrom(question)
                .where(question.content.contains(content)).fetchOne();

    }

    @Override
    public Question findBySubject(String subject) {
        QQuestion question = QQuestion.question;
        return jpaQueryFactory.selectFrom(question)
                .where(question.subject.contains(subject)).fetchOne();
    }

    @Override
    public Question findBySubjectAndContent(String subject, String content) {
        QQuestion question = QQuestion.question;
        return jpaQueryFactory.selectFrom(question)
                .where(question.subject.contains(subject)
                        .and(question.content.contains(content))).fetchOne();
    }

    @Override
    public List<Question> findBySubjectLike(String subject) {
        QQuestion question = QQuestion.question;
        return jpaQueryFactory.selectFrom(question)
                .where(question.subject.like(subject))
                .fetch();

    }
}