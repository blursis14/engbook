package lee.engbook.bookmark;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookmarkService {
	
	@Autowired
	BookmarkRepository repo;
	
	public List getList() {
		return (List) repo.findAll();
	}
	
	public List add(int pin, int din, String folder) {
		Bookmark bookmark=new Bookmark();
		bookmark.setPin(pin);
		bookmark.setDin(din);
		bookmark.setFolder(folder);
		bookmark.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
		repo.save(bookmark);
		return getList();
	}
	
	public List delete(int din) {
		Bookmark bookmark=repo.findByDin(din); //어떤북마크삭제할건지알아보고
		repo.delete(bookmark);//삭제
		return getList();
	}
	
	public Bookmark find(int din) {
		return repo.findByDin(din);
	}

}

























