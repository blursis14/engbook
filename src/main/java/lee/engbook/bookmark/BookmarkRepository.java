package lee.engbook.bookmark;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark,Long> {
	Bookmark findByDin(int din); //din으로 북마크 '하나' 찾아줌 din은 document
	
	List<Bookmark> findByPinAndFolder(int pin,String folder); //회원의 특정폴더 들어가면 북마크 리스트 반환
	
	Bookmark findByPinAndDin(int pin,int din); //삭제 할 때 회원pin과 문서din으로 찾아봄
	

}
