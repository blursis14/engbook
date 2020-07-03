package lee.engbook.member;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lee.engbook.DuplicateMemberException;
import lee.engbook.RegisterRequest;
import lee.engbook.WrongIdPasswordException;
import lee.engbook.folder.FolderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

	@Autowired
	MemberRepository repo;
	@Autowired
	FolderService folderService;
	
	public List getList() {
		return (List)repo.findAll();
	}
	
	
	public List regist(RegisterRequest regReq) {
		Member member=repo.findById(regReq.getId());
		
		if(!regReq.isPasswordEqualToConfirmPassword()) {//비번불일치
			throw new WrongIdPasswordException();
		}
		Member newMember=new Member();
		newMember.setId(regReq.getId());
		newMember.setPassword(regReq.getPassword());
		newMember.setEmail(regReq.getEmail());
		newMember.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
		newMember.setRegType("home");
		newMember=repo.save(newMember); //save하면 repo에서 새로만들어진거 반환함->디폴트폴더 저장을 위해 자동생성된 pin정보가 필요하니 newMember에 담아놓음
		int pin=newMember.getPin();
		folderService.add(pin, "북마크");
	
		return getList();
	}
	public List delete(String id) {
		Member member=repo.findById(id);
		repo.delete(member);
		return getList();
	}
	public Member findById(String id) {
		return repo.findById(id);
	}
	public Member findByEmail(String email) {
		return repo.findByEmail(email);
	}
	public int findPin(String id) {
		Member member=repo.findById(id);
		return member.getPin();
	}
	
}
