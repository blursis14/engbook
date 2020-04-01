package lee.engbook.tag;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TagService {
	
	@Autowired
	TagRepository repo;
	
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
	public List find(int din) {
		return repo.findByDin(din); //sentence하나당 태그 여러개일 수 있으니까 List반환
	}

}
