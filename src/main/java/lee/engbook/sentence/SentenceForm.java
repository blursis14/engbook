package lee.engbook.sentence;

import lombok.Data;

@Data
public class SentenceForm { //추가할 때 입력받는폼

	private String sentence;
	private String mean;
	private String memo;
	private String folder;
}