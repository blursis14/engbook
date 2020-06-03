package lee.engbook.sentence;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import lombok.Data;

@Entity
@Data
@Table(name="sentence")
@Indexed
public class Sentence {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int din;
	
	@Column(nullable=false)
	private int pin;
	
	@Column(nullable=false)
	@Field
	private String sentence;
	
	
	private String mean;
	
	
	private String memo;
	
	@Column(nullable=false,name="regdate")
	private Timestamp regDate;
	
	

}
