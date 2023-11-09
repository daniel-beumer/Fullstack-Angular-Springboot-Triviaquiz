package com.triviaquiz;

import com.triviaquiz.dto.GetQuestionsResponse;
import com.triviaquiz.dto.PostAnswersRequest;
import com.triviaquiz.dto.PostAnswersResponse;
import com.triviaquiz.entity.TriviaQuestion;
import com.triviaquiz.entity.TriviaQuestionList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Builder;

@Builder
public class TestObjects {

    public TriviaQuestion setTriviaQuestion() {
        return TriviaQuestion.builder()
                .category("General Knowledge")
                .type("multiple")
                .difficulty("easy")
                .question("What is the capital of France?")
                .correct_answer("Paris")
                .incorrect_answers(Arrays.asList("L&123;ondon", "Ber&123;lin", "Madri&123d;d"))
                .build();
    }

    public List<TriviaQuestion> setTestListOfTriviaQuestions(TriviaQuestion question1) {
        return new ArrayList<>(Arrays.asList(question1));
    }

    public TriviaQuestionList setTriviaQuestionList(List<TriviaQuestion> listOfTriviaQuestions) {
        return TriviaQuestionList.builder()
                .results(listOfTriviaQuestions)
                .build();
    }

    public GetQuestionsResponse setQuestionsResponse() {
        return GetQuestionsResponse.builder()
                .category("General Knowledge")
                .difficulty("easy")
                .answers(Arrays.asList("Answer 1", "Answer 2", "Answer 3"))
                .question("What is the capital of France?")
                .build();
    }

    public PostAnswersRequest setPostAnswerRequest() {
        return PostAnswersRequest.builder()
                .userAnswers(Arrays.asList("Paris"))
                .build();
    }

    public PostAnswersResponse setPostAnswerResponse() {
        return PostAnswersResponse.builder()
                .percentageCorrect(100)
                .questionsIncorrect(Arrays.asList(""))
                .correctedAnswers(Arrays.asList(""))
                .build();
    }
}