package com.triviaquiz;

import com.triviaquiz.dto.GetQuestionsResponse;
import com.triviaquiz.dto.PostAnswersRequest;
import com.triviaquiz.dto.PostAnswersResponse;
import com.triviaquiz.entity.TriviaQuestion;
import com.triviaquiz.entity.TriviaQuestionList;
import com.triviaquiz.mapper.AnswersMapper;
import com.triviaquiz.service.AnswersService;
import com.triviaquiz.service.TriviaService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class AnswersServiceTest {
    AnswersService answersService;
    @Mock
    TriviaService triviaServiceMock;
    @Mock
    AnswersMapper answerMapperMock;
    TestObjects testObjects = new TestObjects();
    TriviaQuestion triviaQuestionTestObject = testObjects.setTriviaQuestion();
    List<TriviaQuestion> testListOfTriviaQuestions = new ArrayList<>();
    TriviaQuestionList triviaQuestionListTestObject = testObjects.setTriviaQuestionList(testListOfTriviaQuestions);
    GetQuestionsResponse getQuestionsResponse = testObjects.setQuestionsResponse();
    List<GetQuestionsResponse> getQuestionsResponseList = new ArrayList<>();
    PostAnswersRequest postAnswersRequest = testObjects.setPostAnswerRequest();
    PostAnswersResponse postAnswersResponse = testObjects.setPostAnswerResponse();
    List<String> emptyList = new ArrayList<>();
    int percentageCorrect = 100;

    @BeforeEach
    public void setUp() {
        answersService = new AnswersService(triviaServiceMock, answerMapperMock);

        testListOfTriviaQuestions.add(triviaQuestionTestObject);
        getQuestionsResponseList.add(getQuestionsResponse);

        when(triviaServiceMock.fetchTriviaDataFromApi()).thenReturn(triviaQuestionListTestObject);
    }

    @Test
    @DisplayName("validateAnswers returns PostAnswersResponse")
    void validateAnswers_ReturnsPostAnswersResponse() {
        when(answerMapperMock.triviaDataToPostAnswersResponse(anyList(), anyList(), anyInt())).thenReturn(postAnswersResponse);

        PostAnswersResponse postAnswersResponse = answersService.validateAnswers(postAnswersRequest);

        assertEquals(PostAnswersResponse.class, postAnswersResponse.getClass());
        assertNotNull(postAnswersResponse);
    }

    @Test
    @DisplayName("validateAnswers returns PostAnswersResponse with only incorrect questions")
    void validateAnswers_ReturnsIncorrectQuestions() {

        when(answerMapperMock.triviaDataToPostAnswersResponse(emptyList, emptyList, percentageCorrect)).thenReturn(postAnswersResponse);

        PostAnswersResponse postAnswersResponse = answersService.validateAnswers(postAnswersRequest);

        assertEquals(PostAnswersResponse.class, postAnswersResponse.getClass());
        assertNotNull(postAnswersResponse);
    }
}