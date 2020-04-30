package lee.engbook.sentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lee.engbook.AuthInfo;
import lee.engbook.SentenceListForm;
import lee.engbook.bookmark.BookmarkService;
import lee.engbook.folder.FolderService;
import lee.engbook.member.MemberService;
import lee.engbook.tag.TagService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SentenceController {
	
	@Autowired
	private SentenceService service;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private BookmarkService bookmarkService;
	
	/**@RequestMapping(value="/slist",method=RequestMethod.GET)
	public List list() {
		return service.getList();
	}**/
	
	@PostMapping("/sadd")
	public void add(SentenceForm sentenceForm,HttpSession session){
		System.out.println(sentenceForm);
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		int pin =memberService.findPin(authInfo.getId()); 
		Sentence newSentence=service.add(pin,sentenceForm.getSentence(), sentenceForm.getMean(),sentenceForm.getMemo());
		tagService.add(newSentence.getDin(),sentenceForm.getTag()); 
		bookmarkService.add(pin, newSentence.getDin(), sentenceForm.getFolder()); //사용자의 북마크DB에 등록+오픈사전 격인 센텐스DB에 등록
	}
	@RequestMapping(value="/sdelete/{din}",method=RequestMethod.GET)
	public List delete(@PathVariable int din) {
		return service.delete(din);
	}
	@RequestMapping(value="/sfinddin/{din}",method=RequestMethod.GET)
	public Sentence findDin(@PathVariable int din) {
		return service.findDin(din);
	}
	@RequestMapping(value="/sfindpin/{pin}",method=RequestMethod.GET)
	public List findPin(@PathVariable int pin) {
		return service.findPin(pin);
	}
	
	@PostMapping("/list/sentence")
	public List<SentenceListForm> list(@RequestBody HashMap<String,Object> param){
		
		int page=(int)param.get("page");
		int size=(int)param.get("size");
		
		
		List<Sentence> sentenceList= service.findSentenceByPageable(page,size);
		List<SentenceListForm> sentenceListForm=new ArrayList<>();
		String tag;
		for(Sentence sentence:sentenceList) {
			tag=tagService.findTagByDin(sentence.getDin());
			SentenceListForm slf=new SentenceListForm();
			slf.setSentence(sentence);
			slf.setTag(tag);
			sentenceListForm.add(slf); //(sentenceListForm:센텐스객체+한줄화된 태그)객체들의 리스트를 메인에 보냄 
		}
		
		return sentenceListForm;
	}
	

}
























