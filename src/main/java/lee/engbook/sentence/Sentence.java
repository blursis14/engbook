package lee.engbook.sentence;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="sentence")
public class Sentence {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int din;
	
	@Column(nullable=false)
	private int pin;
	
	@Column(nullable=false)
	private String sentence;
	
	private String mean;
	
	private String memo;
	
	@Column(nullable=false,name="regdate")
	private Timestamp regDate;
	
	

}
