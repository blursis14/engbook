package lee.engbook;

import lombok.Data;

@Data
public class AuthInfo {

	private String id;
	
	public AuthInfo(String id) {
		this.id=id;
	}
}
