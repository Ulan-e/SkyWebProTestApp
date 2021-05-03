package com.ulanapp.skywebprotestapp.utils

import java.util.regex.Pattern

val PASSWORD_PATTERN: Pattern = Pattern.compile(
    "^" +  "(?=.*[0-9])" +    //минимум 1 цифра,
            "(?=.*[a-z])" +         //минимум 1 строчная буква,
            "(?=.*[A-Z])" +         //минимум 1 заглавная,
            "(?=.*[a-zA-Z])" +      //все буквы,
            "(?=\\S+$)" +           //без пробела,
            ".{6,}" +               //минимум 6 символов
            "$"
)