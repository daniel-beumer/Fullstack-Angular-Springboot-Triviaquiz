package com.triviaquiz.controller;

import com.triviaquiz.dto.PostAnswersRequest;
import com.triviaquiz.dto.PostAnswersResponse;
import com.triviaquiz.service.AnswersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/answers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AnswersController {
    private final AnswersService answersService;

    @PostMapping
    public PostAnswersResponse triviaQuestions(@RequestBody @Valid PostAnswersRequest postAnswersRequest) {
        return answersService.validateAnswers(postAnswersRequest);
    }
}