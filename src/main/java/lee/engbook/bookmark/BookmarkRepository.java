package lee.engbook.bookmark;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark,Long> {
	List<Bookmark> findByDin(int din); //din으로 북마크  찾아줌 din은 document
	
	List<Bookmark> findByPinAndFolder(int pin,String folder); //회원의 특정폴더 들어가면 북마크 리스트 반환,무한스크롤 완성시 지우셈 
	
	Bookmark findByPinAndDin(int pin,int din); //삭제 할 때 회원pin과 문서din으로 찾아봄
	
	Page<Bookmark> findByPinAndFolder(int pin,String folder,Pageable page); //회원의 특정폴더에 있는 북마크 리스트 페이징해서 반환
	

}
