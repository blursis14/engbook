package lee.engbook.member;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

	@Autowired
	MemberRepository repo;
	
	public List getList() {
		return (List)repo.findAll();
	}
	
	public List add(String id,String password,String email,String regType) {
		Member member=new Member();
		member.setId(id);
		member.setPassword(password);
		member.setEmail(email);
		member.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
		member.setRegType(regType);
		repo.save(member);
		return getList();
	}
	public List delete(String id) {
		Member member=repo.findById(id);
		repo.delete(member);
		return getList();
	}
	public Member find(String id) {
		return repo.findById(id);
	}
	
}
