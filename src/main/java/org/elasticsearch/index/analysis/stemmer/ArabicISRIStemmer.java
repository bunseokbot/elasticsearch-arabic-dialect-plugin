package org.elasticsearch.index.analysis.stemmer;

import java.util.ArrayList;
import java.util.List;

public class ArabicISRIStemmer extends AbstractArabicISRIStemmer {

    public String stem(String token) {
        token = token.replaceAll(arabicComma.pattern(), "");
        token = norm(token, 1);

        for (String word : STOP_WORDS) {
            if (word.equals(token))
                return token;
        }

        token = pre32(token);
        token = suf32(token);
        token = waw(token);
        token = norm(token, 2);

        if (token.length() == 4) {
            token = pro_w4(token);
        }
        else if (token.length() == 5) {
            token = pro_w53(token);
            token = end_w5(token);
        }
        else if (token.length() == 6) {
            token = pro_w6(token);
            token = end_w6(token);
        }
        else if (token.length() == 7) {
            token = suf1(token);
            if (token.length() == 7) {
                token = pre1(token);
            }
            if (token.length() == 6) {
                token = pro_w6(token);
                token = end_w6(token);
            }
        }

        return token;
    }

    private String norm(String word, int number) {
        switch (number) {
            case 1:
                word = word.replaceAll(shortVowels.pattern(), "");
                break;

            case 2:
                word = word.replaceAll(initialHamza.pattern(), "\u0627");
                break;

            case 3:
                word = word
                        .replaceAll(shortVowels.pattern(), "")
                        .replaceAll(initialHamza.pattern(), "\u0627");
                break;
        }

        return word;
    }

    private String pre32(String word) {
        if (word.length() >= 6) {
            for(String element : P3) {
                if (word.startsWith(element)) {
                    return word.substring(3);
                }
            }
        }

        if (word.length() >= 5) {
            for (String element: P2) {
                if (word.startsWith(element)) {
                    return word.substring(2);
                }
            }
        }

        return word;
    }

    private String suf32(String word) {
        if (word.length() >= 6) {
            for (String element : S3) {
                if (word.endsWith(element)) {
                    return word.substring(0, word.length() - 3);
                }
            }
        }

        if (word.length() >= 5) {
            for (String element : S2) {
                if (word.endsWith(element)) {
                    return word.substring(0, word.length() - 2);
                }
            }
        }

        return word;
    }

    private String waw(String word) {
        if (word.length() >= 4 && word.substring(2).equals("\u0648\u0648")) {
            word = word.substring(1);
        }

        return word;
    }

    private String pro_w4(String word) {
        if (word.startsWith(PR4[0][0])) {
            word = word.substring(1);
        }
        else if (word.startsWith(PR4[1][0], 1)) {
            word = word.substring(0, 1) + word.substring(2);
        }
        else if (
            word.startsWith(PR4[2][0], 2) ||
            word.startsWith(PR4[2][1], 2) ||
            word.startsWith(PR4[2][2], 2)
        ) {
            word = word.substring(0, 2) + word.charAt(3);
        } else if (word.endsWith(PR4[3][0])) {
            word = word.substring(0, word.length() - 1);
        } else {
            word = suf1(word);
            if (word.length() == 4)
                word = pre1(word);
        }

        return word;
    }

    private boolean checkContain(String c, String[] arr) {
        for (String value : arr) {
            if (value.equals(c))
                return true;
        }

        return false;
    }

    private String pro_w53(String word) {
        if (checkContain(Character.toString(word.charAt(2)), PR53[0]) && word.charAt(0) == '\u0627') {
            word = word.charAt(1) + word.substring(3);
        }
        else if (checkContain(Character.toString(word.charAt(3)), PR53[1]) && word.charAt(0) == '\u0645') {
            word = word.substring(1, 3) + word.charAt(4);
        }
        else if (checkContain(Character.toString(word.charAt(0)), PR53[2]) && word.charAt(4) == '\u0629') {
            word = word.substring(1, 4);
        }
        else if (checkContain(Character.toString(word.charAt(0)), PR53[3]) && word.charAt(2) == '\u062a') {
            word = word.charAt(1) + word.substring(3);
        }
        else if (checkContain(Character.toString(word.charAt(0)), PR53[4]) && word.charAt(2) == '\u0627') {
            word = word.charAt(1) + word.substring(3);
        }
        else if (checkContain(Character.toString(word.charAt(2)), PR53[5]) && word.charAt(4) == '\u0629') {
            word = word.substring(0, 2) + word.charAt(3);
        }
        else if (checkContain(Character.toString(word.charAt(0)), PR53[6]) && word.charAt(1) == '\u0646') {
            word = word.substring(2);
        }
        else if (word.charAt(3) == '\u0627' && word.charAt(0) == '\u0627') {
            word = word.substring(1, 3) + word.charAt(4);
        }
        else if (word.charAt(4) == '\u0646' && word.charAt(3) == '\u0627') {
            word = word.substring(0, 3);
        }
        else if (word.charAt(4) == '\u064a' && word.charAt(3) == '\u062a') {
            word = word.substring(1, 3) + word.charAt(4);
        }
        else if (word.charAt(3) == '\u0648' && word.charAt(1) == '\u0627') {
            word = Character.toString(word.charAt(0)) + word.charAt(2) + word.charAt(4);
        }
        else if (word.charAt(2) == '\u0627' && word.charAt(1) == '\u0648') {
            word = word.charAt(0) + word.substring(3);
        }
        else if (word.charAt(3) == '\u0626' && word.charAt(2) == '\u0627') {
            word = word.substring(0, 2) + word.charAt(4);
        }
        else if (word.charAt(4) == '\u0629' && word.charAt(1) == '\u0627') {
            word = word.charAt(0) + word.substring(2, 4);
        }
        else if (word.charAt(4) == '\u064a' && word.charAt(2) == '\u0627') {
            word = word.substring(0, 2) + word.charAt(3);
        }
        else {
            word = suf1(word);
            if (word.length() == 5) {
                word = pre1(word);
            }
        }

        return word;
    }

    private String pro_w54(String word) {
        if (checkContain(Character.toString(word.charAt(0)), PR53[2])) {
            word = word.substring(1);
        }
        else if (word.charAt(4) == '\u0629') {
            word = word.substring(0, 4);
        }
        else if (word.charAt(2) == '\u0627') {
            word = word.substring(0, 2) + word.substring(3);
        }

        return word;
    }

    private String end_w5(String word) {
        if (word.length() == 4) {
            word = pro_w4(word);
        }
        else if (word.length() == 5) {
            word = pro_w54(word);
        }

        return word;
    }

    private String pro_w6(String word) {
        if (word.startsWith("\u0627\u0633\u062a") || word.startsWith("\u0645\u0633\u062a")) {
            word = word.substring(3);
        }
        else if (word.charAt(0) == '\u0645' && word.charAt(3) == '\u0627' && word.charAt(5) == '\u0629') {
            word = word.substring(1, 3) + word.charAt(4);
        }
        else if (word.charAt(0) == '\u0627' && word.charAt(3) == '\u062a' && word.charAt(5) == '\u0627') {
            word = Character.toString(word.charAt(1)) + word.charAt(3) + word.charAt(5);
        }
        else if (word.charAt(0) == '\u0645' && word.charAt(2) == '\u062a' && word.charAt(4) == '\u0627') {
            word = word.charAt(1) + word.substring(4);
        }
        else if (word.charAt(0) == '\u062a' && word.charAt(2) == '\u0627' && word.charAt(4) == '\u064a') {
            word = Character.toString(word.charAt(1)) + word.charAt(3) + word.charAt(5);
        } else {
            word = suf1(word);
            if (word.length() == 6) {
                word = pre1(word);
            }
        }

        return word;
    }

    private String pro_w64(String word) {
        if (word.charAt(0) == '\u0627' && word.charAt(4) == '\u0627') {
            word = word.substring(1, 4) + word.charAt(5);
        }
        else if (word.startsWith("\u0645\u062a")) {
            word = word.substring(2);
        }

        return word;
    }

    private String end_w6(String word) {
        if (word.length() == 5) {
            word = pro_w53(word);
            word = end_w5(word);
        }
        else if (word.length() == 6) {
            word = pro_w64(word);
        }

        return word;
    }

    private String suf1(String word) {
        for (String element : S1) {
            if (word.endsWith(element)) {
                return word.substring(0, word.length() - 1);
            }
        }

        return word;
    }

    private String pre1(String word) {
        for (String element : P1) {
            if (word.startsWith(element)) {
                return word.substring(1);
            }
        }

        return word;
    }
}
