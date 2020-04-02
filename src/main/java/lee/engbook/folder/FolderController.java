package lee.engbook.folder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FolderController {

	@Autowired
	private FolderService service;
	
	@RequestMapping(value="/flist",method=RequestMethod.GET)
	public List<Folder> list(){
		return service.getList();
	}
	@RequestMapping(value="/ffind/{pin}",method=RequestMethod.GET)
	public List<Folder> find(@PathVariable int pin){ //특정회원이가진폴더리스트
		return service.find(pin);
	}
	@RequestMapping(value="/fadd/{pin}/{folder}",method=RequestMethod.GET)
	public List<Folder> add(@PathVariable int pin,@PathVariable String folder){
		return service.add(pin, folder);
	}
	
	@RequestMapping(value="/fdelete/{pin}/{folder}",method=RequestMethod.GET)
	public List<Folder> delete(@PathVariable int pin,@PathVariable String folder){ 
		return service.delete(pin,folder); //삭제 후 특정회원의 폴더리스트 반환
	}
}
