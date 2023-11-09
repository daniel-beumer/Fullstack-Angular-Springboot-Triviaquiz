package com.triviaquiz.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TriviaQuestionList {
    private List<TriviaQuestion> results;
}