package com.triviaquiz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetQuestionsResponse {
    @NotBlank(message = "Category can not be empty")
    private String category;
    @NotBlank(message = "Difficulty can not be empty")
    private String difficulty;
    @NotBlank(message = "Answers can not be empty")
    private List<String> answers;
    @NotBlank(message = "Question can not be empty")
    private String question;
}