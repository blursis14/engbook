package lee.engbook.bookmark;

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
public class BookmarkController {

	@Autowired
	private BookmarkService service;
	
	@RequestMapping(value="/blist",method=RequestMethod.GET)
	@ResponseBody
	public List<Bookmark> list(){
		return service.getList();
	}
	
	@RequestMapping(value="/badd/{pin}/{din}/{folder}",method=RequestMethod.GET)
	@ResponseBody
	public List<Bookmark> add(@PathVariable int pin, @PathVariable int din,@PathVariable String folder){
		return service.add(pin, din, folder);
	}
	
	@RequestMapping(value="/bdelete/{din}",method=RequestMethod.GET)
	@ResponseBody
	public List<Bookmark> delete(@PathVariable int din){
		return service.delete(din);
	}
	
	@RequestMapping(value="/bfind/{din}",method=RequestMethod.GET)
	@ResponseBody
	public Bookmark find(@PathVariable int din) {
		return service.find(din);
	}
}


















