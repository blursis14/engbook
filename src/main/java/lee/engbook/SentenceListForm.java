package lee.engbook;

import lee.engbook.sentence.Sentence;
import lee.engbook.tag.Tag;
import lombok.Data;

@Data
public class SentenceListForm {

	private Sentence sentence;
	private String tag;
}
