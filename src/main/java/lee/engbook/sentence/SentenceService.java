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
	
	public void buildIndex() throws InterruptedException{
		
			FullTextEntityManager fullTextEntityManager=Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.createIndexer().startAndWait();
		
	}
	@SuppressWarnings("unckecked")
	public void search() {
		FullTextEntityManager fullTextEntityManager=Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder =fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Sentence.class).get();
		Query query=queryBuilder.keyword().wildcard().onField("sentence")
				.matching("*"+"i"+"*").createQuery();
		FullTextQuery fullTextQuery=fullTextEntityManager.createFullTextQuery(query, Sentence.class);
		System.out.println(fullTextQuery.getResultList());
	}
}


























