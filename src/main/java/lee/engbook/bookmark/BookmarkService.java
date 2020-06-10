package lee.engbook.bookmark;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	
	
	
	public List<Bookmark> findBookmarkByPageable(int page,int size,int pin,String folder){
		Pageable pageable=(Pageable) PageRequest.of(page, size,Sort.by("id").descending());//최신순정렬
		
		System.out.println((repo.findByPinAndFolder(pin,folder,pageable)).getContent());
		
		return (repo.findByPinAndFolder(pin,folder,pageable)).getContent();
	}
	
	public void add(int pin, int din, String folder) {
		Bookmark bookmark=new Bookmark();
		bookmark.setPin(pin);
		bookmark.setDin(din);
		bookmark.setFolder(folder);
		bookmark.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
		repo.save(bookmark);
		
	}
	
	public void deleteAll(int din) { //원작성자가 삭제한 북마크를 db에서 모두 삭제->다른회원들이 삭제된 북마크를 불러오는 것을 방지 
		
		List<Bookmark> bookmarks=new ArrayList<>(); 
		
		bookmarks=repo.findByDin(din);
		
		for(Bookmark b :bookmarks) {
			repo.delete(b);
		}
		
		
	}
	public void pass(int pin,int din) { //외웠어요 버튼 누르면 북마크에서 삭제
		List<Bookmark> bookmarks=repo.findByPinAndDin(pin,din); //어떤북마크삭제할건지 알아내고
		
		for(Bookmark bookmark:bookmarks) { //하나의 폴더에 여러개추가했을 수도 있으니까 pin과 din 해당하는거 리스트로 받아서 전부 삭제
			repo.delete(bookmark); 
		}
		
	}
	
	public void deleteFolder(int pin,String folder) {
		List<Bookmark> bookmarks=repo.findByPinAndFolder(pin,folder);
		
		for(Bookmark bookmark:bookmarks) {
			repo.delete(bookmark);
		}
	
	}
	
	
	public List<Sentence> getListOfFolder(int pin,String folder) {//무한스크롤 구현했으면 이거 지워
		List<Bookmark> bookmarkList=new ArrayList<>();
		bookmarkList=repo.findByPinAndFolder(pin,folder); //북마크리스트 찾기
		
		System.out.println(bookmarkList);
		List<Sentence> sentences=new ArrayList<>();
		
		
		for(Bookmark bookmark:bookmarkList) {
			//System.out.println(bookmark.getDin());
			Sentence sentence=sentenceService.findByDin(bookmark.getDin());
			sentence.setRegDate(bookmark.getRegDate()); 
			//내가 등록한거면 등록일이 같겠지만, 남이 쓴 걸 북마크로 추가했을 때는 내가 추가한 날이 북마크의 등록일이 되는 것임
			
			sentences.add(sentence);
		} //북마크의 din정보를 이용해 sentence리스트들을 만들기
		
		return sentences; 
	}
	
	public List<Bookmark> getListOfBookmark(int pin,String folder){
		return repo.findByPinAndFolder(pin,folder);
	}

	public void editFolder(int pin, String oldFolder,String newFolder) {//폴더 수정시 북마크가 속한 폴더이름도 수정
		List<Bookmark> bookmarks=getListOfBookmark(pin,oldFolder);
		for(Bookmark bookmark:bookmarks) {
			bookmark.setFolder(newFolder);
			repo.save(bookmark);
		}
	}
}

























