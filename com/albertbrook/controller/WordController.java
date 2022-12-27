package com.albertbrook.controller;

import com.albertbrook.settings.Settings;

import java.util.regex.Pattern;

public class WordController {
    public boolean matchCount(String say) {
        for (String badWord : Settings.BAD_WORD) {
            if (Pattern.matches(badWord, say))
                return true;
        }
        return false;
    }
}
