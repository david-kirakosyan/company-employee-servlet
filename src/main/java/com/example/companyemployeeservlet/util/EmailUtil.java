package com.example.companyemployeeservlet.util;

import java.util.regex.Pattern;

public class EmailUtil {

    private static final String EMAIL_REGEX = "^(.+)@(\\S+)$";

    private EmailUtil() {

    }

    public static boolean patternMatches(String emailAddress) {
        return Pattern.compile(EMAIL_REGEX)
                .matcher(emailAddress)
                .matches();
    }
}
