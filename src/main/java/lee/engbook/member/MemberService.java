package lee.engbook.member;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.engbook.DuplicateMemberException;
import lee.engbook.RegisterRequest;
import lee.engbook.WrongIdPasswordException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

	@Autowired
	MemberRepository repo;
	
	
	public List getList() {
		return (List)repo.findAll();
	}
	
	public List regist(RegisterRequest regReq) {
		Member member=repo.findById(regReq.getId());
		if(member!=null) {
			throw new DuplicateMemberException("duplicate email"+regReq.getEmail());
		}
		if(!regReq.isPasswordEqualToConfirmPassword()) {
			throw new WrongIdPasswordException();
		}
		Member newMember=new Member();
		newMember.setId(regReq.getId());
		newMember.setPassword(regReq.getPassword());
		newMember.setEmail(regReq.getEmail());
		newMember.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
		newMember.setRegType("home");
		repo.save(newMember);
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
