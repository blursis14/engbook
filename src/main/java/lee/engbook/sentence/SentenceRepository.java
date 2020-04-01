package lee.engbook.sentence;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SentenceRepository extends CrudRepository<Sentence,Long> {
	Sentence findByDin(int din); //din(sentence 하나당 고유번호) 
	List<Sentence> findByPin(int pin);//특정회원이 등록한 sentences 반환
	
}
