package lee.engbook.bookmark;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name="bookmark")
public class Bookmark {

	@Id
	@GeneratedValue
	private int id;//identifier & 최신순정렬하는데씀 
	
	@Column(nullable=false)
	private int din;
	
	@Column(nullable=false)
	private int pin;
	
	@Column(nullable=false)
	private String folder;
	
	@Column(nullable=false,name="REGDATE")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp regDate;
	
	
	
	
	
}
