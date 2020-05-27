package lee.engbook.bookmark;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lee.engbook.AuthInfo;
import lee.engbook.folder.FolderService;
import lee.engbook.member.MemberService;

@Controller
public class BookmarkController {
	
	@Autowired
	BookmarkService service;
	@Autowired
	MemberService memberService;

	@Autowired
	FolderService folderService;
	
	

	@RequestMapping("/bookmark") //폴더목록
	public ModelAndView bookmarkFolder(HttpSession session) { 
		ModelAndView mav = new ModelAndView();

		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 로그인정보 얻기

		int pin = memberService.findPin(authInfo.getId()); // 사용자의 pin정보 찾기

		mav.addObject("folders", folderService.find(pin)); // 사용자의 폴더리스트 저장

		mav.setViewName("bookmark/folder"); // 북마크/폴더로 뷰 경로 지정
		return mav;
	}

	@RequestMapping("/bookmark/{folder}") //폴더클릭하면 bookmark/list로 이동,어떤폴더 눌렀는지 속성 추가해줌 
	public ModelAndView bookmarkList(@PathVariable String folder,HttpSession session) {
		
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("folder",folder);
		
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); 

		int pin = memberService.findPin(authInfo.getId()); //사용자의 pin 찾기
		
		mav.addObject("pin",pin); //북마크작성자와 bookmark.sentence.pin은 다를 수 있으므로! 북마크작성자의 pin도 보냄.이에따라 삭제버튼이 보이거나 안보임 
		
		mav.setViewName("bookmark/list"); //북마크/리스트로 뷰 경로 지정
		
		return mav;
		
	}
	@RequestMapping("/bookmark/folder/edit")//폴더편집
	public ModelAndView editFolder(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 로그인정보 얻기

		int pin = memberService.findPin(authInfo.getId()); // 사용자의 pin정보 찾기

		mav.addObject("folders", folderService.find(pin)); // 사용자의 폴더리스트 저장

		mav.setViewName("bookmark/edit-folder"); // 북마크/폴더로 뷰 경로 지정
		return mav;
	}
}









