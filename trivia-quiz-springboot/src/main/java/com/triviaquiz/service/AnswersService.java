package com.triviaquiz.service;

import com.triviaquiz.dto.PostAnswersRequest;
import com.triviaquiz.dto.PostAnswersResponse;
import com.triviaquiz.entity.TriviaQuestionList;
import com.triviaquiz.exception.TriviaQuestionNotFoundException;
import com.triviaquiz.mapper.AnswersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AnswersService {
    private final TriviaService triviaService;
    private final AnswersMapper answersMapper;

    public PostAnswersResponse validateAnswers(PostAnswersRequest postAnswersRequest) {
        TriviaQuestionList triviaQuestionList = triviaService.fetchTriviaDataFromApi();
        List<String> userAnswersList = postAnswersRequest.getUserAnswers();

        if (triviaQuestionList.getResults().isEmpty()) {
            throw new TriviaQuestionNotFoundException("Trivia questions empty during validation of user answer");
        }
        List<String> incorrectQuestions = extractIncorrectQuestions(triviaQuestionList, userAnswersList);
        return answersMapper.triviaDataToPostAnswersResponse(incorrectQuestions,
                extractCorrectAnswersForIncorrectQuestions(triviaQuestionList, userAnswersList),
                calculatePercentageCorrectlyAnswered(userAnswersList, incorrectQuestions)
        );
    }

    private static int calculatePercentageCorrectlyAnswered(List<String> userAnswerList, List<String> incorrectQuestions) {
        double percentageCorrectDoubles = (((double) incorrectQuestions.size() / userAnswerList.size()) * 100.0);
        return 100 - (int) percentageCorrectDoubles;
    }

    private static List<String> extractCorrectAnswersForIncorrectQuestions(TriviaQuestionList triviaQuestionList, List<String> userAnswersList) {
        return triviaQuestionList.getResults().stream()
                .filter(question -> !userAnswersList.contains(question.getCorrect_answer()))
                .flatMap(question -> Stream.of(question.getCorrect_answer()))
                .toList();
    }

    private static List<String> extractIncorrectQuestions(TriviaQuestionList triviaQuestionList, List<String> userAnswersList) {
        return triviaQuestionList.getResults().stream()
                .filter(question -> !userAnswersList.contains(question.getCorrect_answer()))
                .flatMap(question -> Stream.of(question.getQuestion()))
                .toList();
    }
}