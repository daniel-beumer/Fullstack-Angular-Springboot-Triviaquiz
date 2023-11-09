package com.triviaquiz;

import com.triviaquiz.dto.GetQuestionsResponse;
import com.triviaquiz.entity.TriviaQuestion;
import com.triviaquiz.entity.TriviaQuestionList;
import com.triviaquiz.service.TriviaService;
import com.triviaquiz.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TriviaServiceTest {
    private final String mockedTriviaUrl = "http://example.com/api";
    @Mock
    private RestTemplate restTemplate;
    @Mock
    Util util;
    @InjectMocks
    private TriviaService triviaService = new TriviaService(mockedTriviaUrl);
    TestObjects testObjects = new TestObjects();
    TriviaQuestion triviaQuestionTestObject = testObjects.setTriviaQuestion();
    List<TriviaQuestion> testListOfTriviaQuestions = new ArrayList<>();
    TriviaQuestionList triviaQuestionListTestObject = testObjects.setTriviaQuestionList(testListOfTriviaQuestions);
    GetQuestionsResponse getQuestionsResponse = testObjects.setQuestionsResponse();
    List<GetQuestionsResponse> getQuestionsResponseList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        testListOfTriviaQuestions.add(triviaQuestionTestObject);
        getQuestionsResponseList.add(getQuestionsResponse);
        ReflectionTestUtils.setField(triviaService, "TRIVIA_URL", mockedTriviaUrl);

        when(restTemplate.getForObject(mockedTriviaUrl, TriviaQuestionList.class)).thenReturn(triviaQuestionListTestObject);
        when(util.removeLeadingCommas(any())).thenCallRealMethod();
        when(util.cleanSpecialCharactersFromString(any())).thenCallRealMethod();
        when(util.removeLeadingComma(any())).thenCallRealMethod();
        when(util.startStringUpperCase(any())).thenCallRealMethod();
    }

    @Test
    @DisplayName("fetchTriviaDataFromApi returns TriviaQuestionList")
    public void fetchTriviaData_ReturnsTriviaQuestionList() {
        when(util.cleanSpecialCharactersFromList(any())).thenCallRealMethod();

        TriviaQuestionList triviaQuestionList = triviaService.fetchTriviaDataFromApi();

        assertEquals(TriviaQuestionList.class, triviaQuestionList.getClass());
        assertNotNull(triviaQuestionList);
    }

    @Test
    @DisplayName("cleanUpTriviaInformation removes special characters from incorrect questions")
    public void testFetchTriviaDataFromApiWhenEmptyResults() {
        when(util.cleanSpecialCharactersFromList(
                triviaQuestionListTestObject.getResults().get(0).getIncorrect_answers())).thenCallRealMethod();

        TriviaQuestionList triviaQuestionList = triviaService.fetchTriviaDataFromApi();

        assertEquals(Arrays.asList("London", "Berlin", "Madrid"), triviaQuestionList.getResults().get(0).getIncorrect_answers());
        assertNotNull(triviaQuestionList);
    }
}