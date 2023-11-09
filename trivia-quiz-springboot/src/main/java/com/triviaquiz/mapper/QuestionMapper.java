package com.triviaquiz.mapper;

import com.triviaquiz.dto.GetQuestionsResponse;
import com.triviaquiz.entity.TriviaQuestion;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    List<GetQuestionsResponse> triviaQuestionsToGetQuestionsResponse(List<TriviaQuestion> triviaQuestions);

    default GetQuestionsResponse map(TriviaQuestion triviaQuestion) {
        GetQuestionsResponse getQuestionsResponse = new GetQuestionsResponse();

        List<String> answers = new ArrayList<>(triviaQuestion.getIncorrect_answers());
        answers.add(triviaQuestion.getCorrect_answer());
        Collections.shuffle(answers);

        getQuestionsResponse.setAnswers(answers);
        getQuestionsResponse.setCategory(triviaQuestion.getCategory());
        getQuestionsResponse.setDifficulty(triviaQuestion.getDifficulty());
        getQuestionsResponse.setQuestion(triviaQuestion.getQuestion());
        return getQuestionsResponse;
    }
}