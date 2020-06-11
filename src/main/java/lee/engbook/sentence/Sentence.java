package lee.engbook.sentence;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.search.annotations.*;

import lombok.Data;

import org.apache.lucene.analysis.ko.KoreanFilterFactory;
import org.apache.lucene.analysis.ko.KoreanTokenizerFactory;





@Entity
@Data
@Table(name="sentence")
@Indexed
@AnalyzerDef(name = "koreanAnalyzer"
, tokenizer = @TokenizerDef(factory = KoreanTokenizerFactory.class)
, filters = { @TokenFilterDef(factory = KoreanFilterFactory.class)})
public class Sentence {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int din;
	
	@Column(nullable=false)
	private int pin;
	
	@Column(nullable=false)
	@Field
	private String sentence;
	
	@Field
	@Analyzer(definition="koreanAnalyzer")
	private String mean;
	
	@Field
	@Analyzer(definition="koreanAnalyzer")
	private String memo;
	
	@Column(nullable=false,name="regdate")
	private Timestamp regDate;
	
	

}













