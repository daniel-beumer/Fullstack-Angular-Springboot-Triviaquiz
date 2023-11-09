package com.triviaquiz.service;

import com.triviaquiz.dto.GetQuestionsResponse;
import com.triviaquiz.entity.TriviaQuestionList;
import com.triviaquiz.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionsService {
    private final QuestionMapper questionMapper;
    private final TriviaService triviaService;

    public List<GetQuestionsResponse> fetchQuestions() {
        TriviaQuestionList triviaQuestionList = triviaService.fetchTriviaDataFromApi();
        return questionMapper.triviaQuestionsToGetQuestionsResponse(triviaQuestionList.getResults());
    }
}