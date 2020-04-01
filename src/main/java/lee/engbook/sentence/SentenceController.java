package lee.engbook.sentence;

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
public class SentenceController {
	
	@Autowired
	private SentenceService service;
	
	@RequestMapping(value="/slist",method=RequestMethod.GET)
	@ResponseBody
	public List list() {
		return service.getList();
	}
	
	@RequestMapping(value="/sadd/{pin}/{sentence}/{mean}/{memo}",method=RequestMethod.GET)
	@ResponseBody
	public List add(@PathVariable int pin,@PathVariable String sentence,@PathVariable String mean,@PathVariable String memo){
		return service.add(pin,sentence,mean,memo);
	}
	@RequestMapping(value="/sdelete/{din}",method=RequestMethod.GET)
	@ResponseBody
	public List delete(@PathVariable int din) {
		return service.delete(din);
	}
	@RequestMapping(value="/sfinddin/{din}",method=RequestMethod.GET)
	@ResponseBody
	public Sentence findDin(@PathVariable int din) {
		return service.findDin(din);
	}
	@RequestMapping(value="/sfindpin/{pin}",method=RequestMethod.GET)
	@ResponseBody
	public List findPin(@PathVariable int pin) {
		return service.findPin(pin);
	}

}
