package lee.engbook.bookmark;


import org.springframework.data.repository.CrudRepository;
import lee.engbook.bookmark.Bookmark;

public interface BookmarkRepository extends CrudRepository<Bookmark,Long> {
	Bookmark findByDin(int din); //din으로 북마크 '하나' 찾아줌 din은 document
	

}
