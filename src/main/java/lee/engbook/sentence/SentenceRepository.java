package lee.engbook.sentence;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SentenceRepository extends PagingAndSortingRepository<Sentence,Long> {
	Sentence findByDin(int din); //din(sentence 하나당 고유번호) 
	List<Sentence> findByPin(int pin);//특정회원이 등록한 sentences 반환
	Page<Sentence> findAll(Pageable page);
	
}
