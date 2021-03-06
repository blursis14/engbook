package lee.engbook.sentence;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lee.engbook.AuthInfo;
import lee.engbook.bookmark.BookmarkService;
import lee.engbook.folder.FolderService;
import lee.engbook.member.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SentenceController {
	
	@Autowired
	private SentenceService service;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private BookmarkService bookmarkService;
	
	
	@PostMapping("/sadd")
	public void add(SentenceForm sentenceForm,HttpSession session){ //자기가 쓴 센텐스 저장할때 
		System.out.println(sentenceForm);
		
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		int pin =memberService.findPin(authInfo.getId());//회원의 pin 알아내기 
		
		Sentence newSentence=service.add(pin,sentenceForm.getSentence(), sentenceForm.getMean(),sentenceForm.getMemo());
		 
		bookmarkService.add(pin, newSentence.getId(), sentenceForm.getFolder()); //사용자의 북마크DB에 등록+(전체)센텐스DB에 등록
		
		
	}
	
	
	
	
	@PostMapping("/list/sentence")
	public List<Sentence> list(@RequestBody HashMap<String,Object> param){ //메인에 보이는 리스트
		
		int page=(int)param.get("page");
		int size=(int)param.get("size");
		
		
		List<Sentence> sentences= service.findSentenceByPageable(page,size);//페이징해서 목록 보냄 
		
		
	
		return sentences;
	}
	
	
	

}
