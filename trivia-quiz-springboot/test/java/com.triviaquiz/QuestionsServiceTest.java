package com.triviaquiz;

import com.triviaquiz.dto.GetQuestionsResponse;
import com.triviaquiz.entity.TriviaQuestion;
import com.triviaquiz.entity.TriviaQuestionList;
import com.triviaquiz.mapper.QuestionMapper;
import com.triviaquiz.service.QuestionsService;
import com.triviaquiz.service.TriviaService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionsServiceTest {
    QuestionsService questionsService;
    @Mock
    QuestionMapper questionMapperMock;
    @Mock
    TriviaService triviaServiceMock;
    TestObjects testObjects = new TestObjects();
    List<TriviaQuestion> testListOfTriviaQuestions = new ArrayList<>();
    TriviaQuestionList triviaQuestionListTestObject = testObjects.setTriviaQuestionList(testListOfTriviaQuestions);
    GetQuestionsResponse getQuestionsResponse = testObjects.setQuestionsResponse();
    List<GetQuestionsResponse> getQuestionsResponseList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        questionsService = new QuestionsService(questionMapperMock, triviaServiceMock);

        getQuestionsResponseList.add(getQuestionsResponse);
    }

    @Test
    @DisplayName("FetchQuestions returns a list of GetQuestionsResponse")
    void fetchQuestion_ReturnsListOfGetQuestionsResponse() {
        when(triviaServiceMock.fetchTriviaDataFromApi()).thenReturn(triviaQuestionListTestObject);
        when(questionMapperMock.triviaQuestionsToGetQuestionsResponse(triviaQuestionListTestObject.getResults())).thenReturn(getQuestionsResponseList);

        List<GetQuestionsResponse> getQuestionsResponse = questionsService.fetchQuestions();

        assertEquals(getQuestionsResponseList, getQuestionsResponse);
        assertNotNull(getQuestionsResponse);
    }
}