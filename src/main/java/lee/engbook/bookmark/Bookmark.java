package lee.engbook.bookmark;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="bookmark")
public class Bookmark {

	@Id
	private int din;
	
	@Column(nullable=false)
	private int pin;
	
	@Column(nullable=false)
	private String folder;
	
	@Column(nullable=false,name="REGDATE")
	private Timestamp regDate;
	
	
	
	
	
}
