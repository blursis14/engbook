package lee.engbook.sentence;

import lee.engbook.tag.Tag;
import lombok.Data;

@Data
public class SentenceListForm { //리스트보낼때 요소 폼 

	private Sentence sentence;
	private String tag;
}
