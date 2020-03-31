package lee.engbook.folder;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface FolderRepository extends CrudRepository<Folder,Long>{
	List<Folder> findByPin(int pin);//특정회원이 가진 폴더들 반환 

	Folder findByPinAndFolder(int pin,String folder);
}
