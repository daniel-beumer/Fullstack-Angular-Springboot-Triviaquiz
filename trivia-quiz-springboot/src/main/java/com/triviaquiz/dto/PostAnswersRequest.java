package com.triviaquiz.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostAnswersRequest {
    @NotNull(message = "user answer can not be empty")
    private List<String> userAnswers = new ArrayList<>();
}