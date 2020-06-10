package lee.engbook.bookmark;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark,Long> {
	List<Bookmark> findByDin(int din); //din으로 북마크  찾아줌 din은 document
	
	List<Bookmark> findByPinAndFolder(int pin,String folder); //폴더를 지워서 안에 북마크까지 지워야 될 때 사용
	
	List<Bookmark> findByPinAndDin(int pin,int din); //외운거 지울 때 회원pin과 문서din으로 찾아봄
	
	Page<Bookmark> findByPinAndFolder(int pin,String folder,Pageable page); //회원의 특정폴더에 있는 북마크 리스트 페이징해서 반환
	

}
