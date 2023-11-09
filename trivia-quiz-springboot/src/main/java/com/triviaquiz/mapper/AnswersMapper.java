package com.triviaquiz.mapper;

import com.triviaquiz.dto.PostAnswersResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswersMapper {
    PostAnswersResponse triviaDataToPostAnswersResponse(
            List<String> questionsIncorrect, List<String> correctedAnswers, int percentageCorrect);
}