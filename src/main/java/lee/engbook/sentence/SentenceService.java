package lee.engbook.sentence;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SentenceService {
	
	@Autowired
	SentenceRepository repo;
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	
	
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
	public void delete(int id) {
		Sentence sentence=repo.findById(id);
		repo.delete(sentence);
		
	}
	public Sentence findById(int id) {
		return repo.findById(id); 
	}
	public List findByPin(int pin) {
		return (List)repo.findByPin(pin);
	}
	
	public List<Sentence> findSentenceByPageable(int page,int size){
		Pageable pageable=(Pageable) PageRequest.of(page, size,Sort.by("id").descending());//최신순정렬
		return (repo.findAll(pageable)).getContent();
	}
	
	public void buildIndex() throws InterruptedException{
		
			FullTextEntityManager fullTextEntityManager=Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.createIndexer().startAndWait();
		
	}
	
	public List<Sentence> search(String str) {

		FullTextEntityManager fullTextEntityManager=Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder =fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Sentence.class).get();
		
		if(str.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) { //한글검색시 sentence의 mean필드에서 검색 
			Query query=queryBuilder.phrase().withSlop(5).onField("mean") 
					.sentence(str).createQuery();
			FullTextQuery fullTextQuery=fullTextEntityManager.createFullTextQuery(query, Sentence.class);
			System.out.println(fullTextQuery.getResultList());
			return (List<Sentence>)fullTextQuery.getResultList();
			}
			else {                                 //영어검색시 sentence의 sentence필드에서 검색
				Query query=queryBuilder.phrase().withSlop(5).onField("sentence")
						.sentence(str).createQuery();
				FullTextQuery fullTextQuery=fullTextEntityManager.createFullTextQuery(query, Sentence.class);
				
			    System.out.println(fullTextQuery.getResultList());
			    return (List<Sentence>)fullTextQuery.getResultList();
				
			}
		
		  
	}
}


























