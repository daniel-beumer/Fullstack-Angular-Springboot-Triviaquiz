package com.triviaquiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostAnswersResponse {
    @NotNull(message = "Percentage correct can not be empty")
    private int percentageCorrect;
    @NotBlank(message = "Questions incorrect can not be empty")
    private List<String> questionsIncorrect;
    @NotBlank(message = "Corrected answers can not be empty")
    private List<String> correctedAnswers;
}