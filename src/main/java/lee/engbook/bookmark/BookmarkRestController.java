package lee.engbook.bookmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lee.engbook.AuthInfo;
import lee.engbook.member.MemberService;
import lee.engbook.sentence.SentenceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BookmarkRestController {

	@Autowired
	private BookmarkService service;
	@Autowired
	private MemberService memberService;
	@Autowired
	private SentenceService sentenceService;
	
	@RequestMapping(value="/blist",method=RequestMethod.GET)
	public List<Bookmark> list(){
		return service.getList();
	}
	
	@PostMapping("/bookmark/add")
	public void add(@RequestBody HashMap<String,Object> param,HttpSession session){
		System.out.println(param);
		
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		
		int pin=memberService.findPin(authInfo.getId()); //지금 로그인 한 회원의 pin 조회
		System.out.println(pin);
		int din=(int)param.get("din"); //북마크에 추가할 센텐스의 문서번호인 din 추출
		String folder=(String)param.get("folder"); //북마크의 폴더 파라미터 추출
		service.add(pin, din, folder); 
	}
	
	@PostMapping("/bookmark/delete") //북마크db에서 삭제되고 전체리스트에서도 삭제되는 기능 -센텐스의 작성자에만 해당
	public void delete(@RequestBody HashMap<String,Object> param,HttpSession session){
		
		int din=(int)param.get("din");
	
		service.deleteAll(din); //북마크db에서 din에 해당하는 것을 모두 삭제 (북마크 작성자, 북마크 퍼간 회원);
		sentenceService.delete(din);//전체 센텐스에서도 삭제
	}
	
	@PostMapping("/bookmark/pass") //외웠어요 버튼 누르면 자기 북마크에서만 안보이고 전체리스트에는 남아있는 기능-작성자의 삭제권한 없어짐
	public void pass(@RequestBody HashMap<String,Object> param,HttpSession session) {
		int din=(int)param.get("din");
		
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		System.out.println(param);
		int pin=memberService.findPin(authInfo.getId()); 

		service.deleteOne(pin,din); //자기 북마크에서만 삭제 
	}
	
	
	
}


















