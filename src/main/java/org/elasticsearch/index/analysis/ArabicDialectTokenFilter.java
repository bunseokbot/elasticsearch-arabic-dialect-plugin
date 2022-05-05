package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.index.analysis.stemmer.ArabicISRIStemmer;

import java.io.IOException;

public class ArabicDialectTokenFilter extends TokenFilter {
    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);
    private ArabicISRIStemmer arabicISRIStemmer = new ArabicISRIStemmer();

    public ArabicDialectTokenFilter(TokenStream input) {
        super(input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (input.incrementToken()) {
            CharSequence parsedData = arabicISRIStemmer.stem(termAttribute.toString());
            termAttribute.setEmpty().append(parsedData);
            return true;
        }
        return false;
    }
}
