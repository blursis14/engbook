package lee.engbook.tag;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TagRepository extends CrudRepository<Tag,Long>{
	List<Tag> findByTag(String tag);
	Tag findByDinAndTag(int din,String tag);

}
