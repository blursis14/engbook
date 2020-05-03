package lee.engbook.bookmark;

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
	
	

	@RequestMapping("/bookmark")
	public ModelAndView bookmarkFolder(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); // 로그인정보 얻기

		int pin = memberService.findPin(authInfo.getId()); // 사용자의 pin정보 찾기

		mav.addObject("folders", folderService.find(pin)); // 사용자의 폴더리스트 저장

		mav.setViewName("bookmark/folder"); // 북마크/폴더로 뷰 경로 지정
		return mav;
	}

	@RequestMapping("/bookmark/{folder}")
	public ModelAndView bookmarkList(@PathVariable String folder,HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); 

		int pin = memberService.findPin(authInfo.getId()); //사용자의 pin 찾기
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("bookmarks",service.getListOfFolder(pin,folder)); //회원이 폴더에 저장한 북마크 리스트(실제론 sentence객체의 리스트임) 저장
		mav.setViewName("bookmark/list"); //북마크/리스트로 뷰 경로 지정
		
		return mav;
		
	}
}
