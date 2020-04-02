package lee.engbook.tag;

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
public class TagController {
	
	@Autowired
	private TagService service;
	
	@RequestMapping(value="/tlist",method=RequestMethod.GET)
	public List list() {
		return service.getList();
	}
	@RequestMapping(value="/tadd/{din}/{tag}",method=RequestMethod.GET)
	public List<Tag> add(@PathVariable int din,@PathVariable String tag){
		return service.add(din, tag);
	}
	
	@RequestMapping(value="/tdelete/{din}/{tag}",method=RequestMethod.GET)
	public List<Tag> delete(@PathVariable int din,@PathVariable String tag){
		return service.delete(din,tag);
	}
	@RequestMapping(value="/tfind/{din}",method=RequestMethod.GET)
	public List<Tag> find(@PathVariable int din){
		return service.find(din);
	}
	
	

	
}
