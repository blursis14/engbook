package lee.engbook.folder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.engbook.bookmark.Bookmark;
import lee.engbook.bookmark.BookmarkService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FolderService {

	@Autowired
	FolderRepository repo;
	@Autowired
	BookmarkService bookmarkService;
	
	
	public Folder add(int pin,String folderName) { //인스턴스folder랑 파라미터folder(폴더이름)헷갈릴까봐 파라미터는 folderName으로 정함 
		Folder folder=new Folder();
		folder.setPin(pin);
		folder.setFolder(folderName);
		return repo.save(folder);
		
	}
	
	public void delete(int pin,String folderName) {
		List<Bookmark> bookmarks=new ArrayList<>(); 
		bookmarkService.getListOfBookmark(pin,folderName); //폴더 삭제시,폴더 안에 있는 북마크들 또한 삭제해야 되므로 리스트 받아옴
		
		System.out.println(bookmarks);
		
		Folder folder=repo.findByPinAndFolder(pin, folderName); 
		repo.delete(folder);//폴더삭제
		
		bookmarkService.deleteFolder(pin,folderName);//폴더 속 북마크들 삭제
		
	}
	
	public void edit(int pin,String oldFolder,String newFolder) {//폴더이름수정
		Folder folder=repo.findByPinAndFolder(pin,oldFolder);
		folder.setFolder(newFolder);
		repo.save(folder);//새로운 폴더이름으로 업데이트
		
		bookmarkService.editFolder(pin, oldFolder, newFolder);//북마크db에서도 폴더이름 수정
		
	}
	
	
	public List find(int pin) { //특정회원이가진 폴더들
		return (List)repo.findByPin(pin);
	}
}




















