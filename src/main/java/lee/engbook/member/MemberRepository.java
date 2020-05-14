package lee.engbook.member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
	Member findById(String id);
	Member findByEmail(String email);
}
