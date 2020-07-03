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
	private int id; //identifier
	
	@Column(nullable=false)
	private int pin;
	
	@Column(nullable=false)
	private String folder;
	

}
