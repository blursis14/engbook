package lee.engbook.folder;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lee.engbook.AuthInfo;
import lee.engbook.member.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FolderController {

	@Autowired
	private FolderService service;
	@Autowired 
	private MemberService memberService;
	
	@RequestMapping(value="/flist",method=RequestMethod.GET)
	public List<Folder> list(){
		return service.getList();
	}
	@RequestMapping(value="/ffind",method=RequestMethod.POST)
	public List<Folder> find(HttpSession session){ //로그인한 회원이 가진 폴더리스트
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		int pin =memberService.findPin(authInfo.getId()); //회원고유번호알아내기
		return service.find(pin);
	}
	@RequestMapping(value="/fadd",method=RequestMethod.POST)
	public String add(@RequestParam(value="newFolder",defaultValue="")String newFolder,HttpSession session){
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		System.out.println(newFolder);
		int pin=memberService.findPin(authInfo.getId());
	
		 service.add(pin, newFolder);
		 return newFolder;
	
		
	}
	
	@RequestMapping(value="/fdelete/{pin}/{folder}",method=RequestMethod.GET)
	public List<Folder> delete(@PathVariable int pin,@PathVariable String folder){ 
		return service.delete(pin,folder); //삭제 후 특정회원의 폴더리스트 반환
	}
}






























