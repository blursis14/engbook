package lee.engbook.sentence;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lee.engbook.AuthInfo;
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
	
	@RequestMapping(value="/slist",method=RequestMethod.GET)
	public List list() {
		return service.getList();
	}
	
	@PostMapping("/sadd")
	public void add(SentenceForm sentenceForm,HttpSession session){
		System.out.println(sentenceForm);
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		int pin =memberService.findPin(authInfo.getId()); 
		Sentence newSentence=service.add(pin,sentenceForm.getSentence(), sentenceForm.getMean(),sentenceForm.getMemo());
		tagService.add(newSentence.getDin(),sentenceForm.getTag()); 
		folderService.add(pin, sentenceForm.getFolder());
		bookmarkService.add(pin, newSentence.getDin(), sentenceForm.getFolder());
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

}
