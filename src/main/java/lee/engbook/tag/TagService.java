package lee.engbook.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.engbook.sentence.Sentence;
import lee.engbook.sentence.SentenceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TagService {
	
	@Autowired
	TagRepository repo;
	
	@Autowired
	SentenceService sentence_service;
	
	public List getList() {
		return (List)repo.findAll();
	}
	public List add(int din,String p_tag) {
		Tag tag=new Tag();
		tag.setDin(din);
		tag.setTag(p_tag); //인스턴스 tag랑 헷갈리지 않기 위해 파라미터 tag는 p_tag로 명명
		repo.save(tag);
		return getList();
	}
	public List delete(int din,String p_tag) {
		Tag tag=repo.findByDinAndTag(din,p_tag);
		repo.delete(tag);
		return getList();
	}
	public List find(String tag) {
		return repo.findByTag(tag); //sentence하나당 태그 여러개일 수 있으니까 List반환
	}
	public List getSentenceList(List<Tag> tagList) { //tag가 들어간 sentence테이블의 din리스트 반환
		List<Sentence> sentenceList=new ArrayList<>();
		for(Tag tag: tagList ) {
			int din=tag.getDin();
			sentenceList.add(sentence_service.findDin(din));
		}
		return sentenceList;
		
	}

}
