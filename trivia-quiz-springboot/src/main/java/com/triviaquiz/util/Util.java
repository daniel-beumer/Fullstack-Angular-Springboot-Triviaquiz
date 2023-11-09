package com.triviaquiz.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Util {

    public List<String> cleanSpecialCharactersFromList(List<String> toClean) {
        return toClean.stream()
                .map(this::cleanSpecialCharactersFromString)
                .collect(Collectors.toList());
    }

    public String cleanSpecialCharactersFromString(String toClean) {
        return toClean.replaceAll("&[^;]*;", "");
    }

    public String startStringUpperCase(String toUpperCase) {
        if (toUpperCase != null && !toUpperCase.isEmpty()) {
            return Character.toUpperCase(toUpperCase.charAt(0)) + toUpperCase.substring(1);
        }
        return toUpperCase;
    }
    public String removeLeadingComma(String input) {
        if (input != null && input.startsWith(",")) {
            return input.substring(1);
        }
        return input;
    }
    public List<String> removeLeadingCommas(List<String> inputList) {
        return inputList.stream()
                .map(input -> input != null && input.startsWith(",") ? input.substring(1) : input)
                .collect(Collectors.toList());
    }
}