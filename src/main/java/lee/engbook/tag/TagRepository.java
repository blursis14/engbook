package lee.engbook.tag;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends PagingAndSortingRepository<Tag,Long>{
	List<Tag> findByTag(String tag);
	Tag findByDinAndTag(int din,String tag);
	List<Tag> findByDin(int din);
	

}
