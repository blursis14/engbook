package lee.engbook.member;

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
@Table(name="t_member")
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pin;
	
	@Column(nullable=false)
	private String id;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false,name="regdate")
	private Timestamp regDate;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false,name="regtype")
	private String regType;

}
