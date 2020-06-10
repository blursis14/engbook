package lee.engbook.sentence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepository extends JpaRepository<Sentence,Long> {
	Sentence findByDin(int din); //din(sentence 하나당 고유번호)으로 센텐스찾기 
	List<Sentence> findByPin(int pin);//특정회원이 등록한 sentences 반환
	Page<Sentence> findAll(Pageable page);
	
}
