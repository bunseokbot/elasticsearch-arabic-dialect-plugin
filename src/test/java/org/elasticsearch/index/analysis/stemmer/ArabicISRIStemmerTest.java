package org.elasticsearch.index.analysis.stemmer;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class ArabicISRIStemmerTest {
    @Test
    public void shouldLoadArabicISRIStemmer() {
        ArabicISRIStemmer stemmer = new ArabicISRIStemmer();
        assertNotNull(stemmer);
    }

    @Test
    public void stemModernStandardArabic() {
        ArabicISRIStemmer stemmer = new ArabicISRIStemmer();
        // Title of Universal Declaration of Human Rights
        String sentence = "الإعلان العالمي لحقوق الإنسان";
        ArrayList<String> arrayList = new ArrayList<>();

        for (String token : sentence.split(" ")) {
            arrayList.add(stemmer.stem(token));
        }

        assertEquals(4, arrayList.size());
    }

    @Test
    public void stemArabicDialectEgyptian() {
        ArabicISRIStemmer stemmer = new ArabicISRIStemmer();
        // What's your news - Egyptian
        String sentence = "أخبارك إيه";
        ArrayList<String> arrayList = new ArrayList<>();

        for (String token : sentence.split(" ")) {
            arrayList.add(stemmer.stem(token));
        }

        assertEquals(2, arrayList.size());
    }

    @Test
    public void stemArabicDialectLevantine() {
        ArabicISRIStemmer stemmer = new ArabicISRIStemmer();
        // Is everything going well? - Levantine
        String sentence = "ماشي الحال";
        ArrayList<String> arrayList = new ArrayList<>();

        for (String token : sentence.split(" ")) {
            arrayList.add(stemmer.stem(token));
        }

        assertEquals(2, arrayList.size());
    }

    @Test
    public void stemArabicDialectGulf() {
        ArabicISRIStemmer stemmer = new ArabicISRIStemmer();
        // How are you? - Gulf
        String sentence = "كيف الحال";
        ArrayList<String> arrayList = new ArrayList<>();

        for (String token : sentence.split(" ")) {
            arrayList.add(stemmer.stem(token));
        }

        assertEquals(2, arrayList.size());
    }

    @Test
    public void stemArabicDialectMaghrebi() {
        ArabicISRIStemmer stemmer = new ArabicISRIStemmer();
        // Is everything fine? - Maghrebi
        String sentence = "كلشي بخير";
        ArrayList<String> arrayList = new ArrayList<>();

        for (String token : sentence.split(" ")) {
            arrayList.add(stemmer.stem(token));
        }

        assertEquals(2, arrayList.size());
    }
}
