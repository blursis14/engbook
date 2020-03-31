package lee.engbook.folder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="folder")
public class Folder {
	
	@Id
	@GeneratedValue
	private int id; //필요없는 프로퍼티인데 identifier없다고 오류떠서 씀
	
	@Column(nullable=false)
	private int pin;
	
	@Column(nullable=false)
	private String folder;
	

}
