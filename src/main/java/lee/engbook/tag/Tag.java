package lee.engbook.tag;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="tag")
public class Tag {
	
	@Id
	@GeneratedValue
	private int id; //필요x. identifier를 위해 생성
	
	@Column(nullable=false)
	private int din;
	
	@Column(nullable=false)
	private String tag;
	

}
