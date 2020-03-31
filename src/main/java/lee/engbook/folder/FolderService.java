package lee.engbook.folder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FolderService {

	@Autowired
	FolderRepository repo;
	
	public List getList() {
		return (List)repo.findAll();
	}
	public List add(int pin,String folderName) { //인스턴스folder랑 파라미터folder(폴더이름)헷갈릴까봐 파라미터는 folderName으로 정함 
		Folder folder=new Folder();
		folder.setPin(pin);
		folder.setFolder(folderName);
		repo.save(folder);
		return getList();
	}
	
	public List delete(int pin,String folderName) {
		Folder folder=repo.findByPinAndFolder(pin, folderName);
		repo.delete(folder);
		return find(pin);
	}
	
	public List find(int pin) { //특정회원이가진 폴더들
		return (List)repo.findByPin(pin);
	}
}
