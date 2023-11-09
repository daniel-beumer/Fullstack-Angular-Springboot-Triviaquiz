package com.triviaquiz.controller;

import com.triviaquiz.dto.GetQuestionsResponse;
import com.triviaquiz.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionsController {
    private final QuestionsService questionsService;

    @GetMapping
    public List<GetQuestionsResponse> triviaQuestions() {
        return questionsService.fetchQuestions();
    }
}