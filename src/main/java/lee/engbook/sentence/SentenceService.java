package lee.engbook.sentence;

import org.springframework.data.domain.Pageable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SentenceService {
	
	@Autowired
	SentenceRepository repo;
	
	public List getList() {
		return (List)repo.findAll();
	}
	
	public Sentence add(int pin,String p_sentence,String mean,String memo) {
		Sentence sentence=new Sentence();
		sentence.setPin(pin);
		sentence.setSentence(p_sentence); //인스턴스랑 이름 겹치니까 파라미터인 센텐스는 p_sentence로 명명 
		sentence.setMean(mean);
		sentence.setMemo(memo);
		sentence.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
		sentence=repo.save(sentence);
		
		return sentence; //sentence의 자동생성된 din값이, 다른 테이블에 값저장할 때 필요하므로 새로 저장된거 반환
	}
	public void delete(int din) {
		Sentence sentence=repo.findByDin(din);
		repo.delete(sentence);
		
	}
	public Sentence findByDin(int din) {
		return repo.findByDin(din); 
	}
	public List findByPin(int pin) {
		return (List)repo.findByPin(pin);
	}
	
	public List<Sentence> findSentenceByPageable(int page,int size){
		Pageable pageable=(Pageable) PageRequest.of(page, size,Sort.by("din").descending());//최신순정렬
		return (repo.findAll(pageable)).getContent();
	}
	
	public List<Sentence> search(String str){
		
		//String str=search
		
		return (repo.findBySentenceContainingOrMeanContainingOrMemoContaining(str,str,str));
	}
}


























