package lee.engbook.bookmark;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="bookmark")
public class Bookmark {

	@Id
	@GeneratedValue
	private int id;//필요없는 프로퍼티인데 identifier없다고 해서 써야됨 
	
	@Column(nullable=false)
	private int din;
	
	@Column(nullable=false)
	private int pin;
	
	@Column(nullable=false)
	private String folder;
	
	@Column(nullable=false,name="REGDATE")
	private Timestamp regDate;
	
	
	
	
	
}
