package com.triviaquiz.service;

import com.triviaquiz.entity.TriviaQuestion;
import com.triviaquiz.entity.TriviaQuestionList;
import com.triviaquiz.exception.TriviaQuestionNotFoundException;
import com.triviaquiz.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TriviaService {
    @Autowired
    private Util util;
    @Autowired
    RestTemplate restTemplate;
    private final String TRIVIA_URL;
    private TriviaQuestionList triviaQuestionList;

    @Autowired
    public TriviaService(@Value("${trivia-quiz.url}") String TRIVIA_URL) {
        this.TRIVIA_URL = TRIVIA_URL;
    }

    public TriviaQuestionList fetchTriviaDataFromApi() {
        if (triviaQuestionList == null) {
            triviaQuestionList = restTemplate.getForObject(TRIVIA_URL, TriviaQuestionList.class);
            if (triviaQuestionList.getResults().isEmpty()) {
                throw new TriviaQuestionNotFoundException("Trivia questions still empty after fetching questions");
            }
            triviaQuestionList = cleanUpTriviaInformation(triviaQuestionList);
        }
        return triviaQuestionList;
    }

    public TriviaQuestionList cleanUpTriviaInformation(TriviaQuestionList triviaQuestionList) {
        List<TriviaQuestion> triviaQuestions = triviaQuestionList.getResults();
        for (TriviaQuestion question : triviaQuestions) {
            question.setIncorrect_answers(util.cleanSpecialCharactersFromList(question.getIncorrect_answers()));
            question.setIncorrect_answers(util.removeLeadingCommas(question.getIncorrect_answers()));
            question.setQuestion(util.cleanSpecialCharactersFromString(question.getQuestion()));
            question.setCorrect_answer(util.cleanSpecialCharactersFromString(question.getCorrect_answer()));
            question.setCorrect_answer(util.removeLeadingComma(question.getCorrect_answer()));
            question.setCategory(util.cleanSpecialCharactersFromString(question.getCategory()));
            question.setCategory(util.startStringUpperCase(question.getCategory()));
            question.setDifficulty(util.startStringUpperCase(question.getDifficulty()));
            question.setType(util.startStringUpperCase(question.getType()));
        }
        return triviaQuestionList;
    }
}