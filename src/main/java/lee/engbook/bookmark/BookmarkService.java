package lee.engbook.bookmark;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.engbook.sentence.Sentence;
import lee.engbook.sentence.SentenceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookmarkService {
	
	@Autowired
	BookmarkRepository repo;
	
	@Autowired
	SentenceService sentenceService;
	
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
	
	
	
	public List<Sentence> getListOfFolder(int pin,String folder) {
		List<Bookmark> bookmarkList=new ArrayList<>();
		bookmarkList=repo.findByPinAndFolder(pin,folder); //북마크리스트 찾기
		
		List<Sentence> sentenceList=new ArrayList<>();
		
		for(Bookmark bookmark:bookmarkList) {
			sentenceList.add(sentenceService.findDin(bookmark.getDin()));
		} //북마크의 din정보를 이용해 sentence리스트들을 만들기
		return sentenceList; 
	}

}

























