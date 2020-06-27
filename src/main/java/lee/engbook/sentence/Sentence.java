package lee.engbook.sentence;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Entity
@Data
@Table(name="sentence")
@Indexed

public class Sentence {
	
	@GeneratedValue
	private int id;
	
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
	private String memo;
	
	@Column(nullable=false,name="regdate")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp regDate;
	
	

}













