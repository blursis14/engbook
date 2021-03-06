package lee.engbook.folder;

import java.util.HashMap;
import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FolderController {

	@Autowired
	private FolderService service;
	@Autowired 
	private MemberService memberService;
	
	@RequestMapping(value="/ffind",method=RequestMethod.POST)
	public List<Folder> find(HttpSession session){ //로그인한 회원이 가진 폴더리스트
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		int pin =memberService.findPin(authInfo.getId()); //회원고유번호알아내기
		return service.find(pin);
	}
	@RequestMapping(value="/fadd",method=RequestMethod.POST)
	public Folder add(@RequestParam(value="newFolder",defaultValue="")String newFolder,HttpSession session){
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		System.out.println(newFolder);
		int pin=memberService.findPin(authInfo.getId());
		Folder result=new Folder();
		result=service.add(pin, newFolder); //새폴더객체반환
		return result;
		
		
	}
	
	@PostMapping("/folder/delete")
	public void delete(@RequestBody HashMap<String,Object> param,HttpSession session) {
		System.out.println(param);
		String folder=(String)param.get("folder"); //어떤폴더를 삭제할건지 알아내기
		
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		int pin=memberService.findPin(authInfo.getId()); //회원pin 알아내기
		
		service.delete(pin, folder);
	
	}
	
	@PostMapping("/folder/edit")
	public void edit(@RequestBody HashMap<String,Object> param,HttpSession session) {
		System.out.println(param);
		
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		int pin=memberService.findPin(authInfo.getId()); //회원pin 알아내기
		
		String oldFolder=(String)param.get("old");//이전 이름
		String newFolder=(String)param.get("new");//새로운 이름
		
		service.edit(pin,oldFolder,newFolder);
	
		
		
	}
}






























