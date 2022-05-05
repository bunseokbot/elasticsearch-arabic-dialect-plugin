package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class ArabicDialectTokenFilterFactory extends AbstractTokenFilterFactory {
    public ArabicDialectTokenFilterFactory(
            IndexSettings indexSettings,
            Environment environment,
            String name,
            Settings settings
    ) {
        super(indexSettings, name, settings);
    }

    public TokenStream create(TokenStream tokenStream) {
        return new ArabicDialectTokenFilter(tokenStream);
    }
}
