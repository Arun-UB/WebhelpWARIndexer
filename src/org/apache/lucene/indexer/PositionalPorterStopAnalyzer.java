
package org.apache.lucene.indexer;

import java.io.Reader;
import java.util.Set;
import org.apache.lucene.analysis.*;
public class PositionalPorterStopAnalyzer extends Analyzer {
  private Set stopWords;

  public PositionalPorterStopAnalyzer() {
    this(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
  }

  public PositionalPorterStopAnalyzer(Set stopWords) {
    this.stopWords = stopWords;
  }

    @Override
  public TokenStream tokenStream(String fieldName, Reader reader) {
    StopFilter stopFilter = new StopFilter(true,
                                           new LowerCaseTokenizer(reader),
                                           stopWords);
    stopFilter.setEnablePositionIncrements(true);
    return new PorterStemFilter(stopFilter);
  }
}
