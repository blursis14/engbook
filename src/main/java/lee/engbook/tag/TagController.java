package lee.engbook.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lee.engbook.sentence.Sentence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TagController {
	
	@Autowired
	private TagService service;
	
	
	@RequestMapping(value="/tlist",method=RequestMethod.GET)
	public List list() {
		return service.getList();
	}
	@RequestMapping(value="/tadd/{din}/{tag}",method=RequestMethod.GET)
	public List<Tag> add(@PathVariable int din,@PathVariable String tag){
		return service.add(din, tag);
	}
	
	@RequestMapping(value="/tdelete/{din}/{tag}",method=RequestMethod.GET)
	public List<Tag> delete(@PathVariable int din,@PathVariable String tag){
		return service.delete(din,tag);
	}
	@PostMapping("/tsearch")
	public ResponseEntity<?> search(@RequestParam(value="search-tag", defaultValue="") String tag){
		System.out.println(tag);
		List<Tag> tagList= service.find(tag);//사용자가 검색한 태그가 들어간 태그객체리스트 반환
		List<Sentence> sentenceList=service.getSentenceList(tagList); //태그리스트 보고 태그에있는 din들어간 sentence리스트 얻기
		System.out.println(sentenceList);
		return ResponseEntity.ok(sentenceList);
	}
	
	
	
}





















