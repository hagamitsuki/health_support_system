package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Mood;

public class MoodValidator {
    public static List<String> validate(Mood m) {
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(m.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = _validateContent(m.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    private static String _validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "今の気分を入力してください。";
            }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "対処法などを入力してください。";
            }

        return "";
    }
}