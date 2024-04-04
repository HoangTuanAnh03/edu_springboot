package com.huce.edu.controllers;

import com.huce.edu.entities.*;
import com.huce.edu.models.ApiResult;
import com.huce.edu.models.dto.*;
import com.huce.edu.repositories.*;
import com.huce.edu.utils.BearerTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class WordController {
	//	@GetMapping("/api/words/getAllLevel")
//	public ArrayList<LevelDto> getAllLevel(HttpServletRequest request){
//		ArrayList<LevelDto> levelDtos = new ArrayList<>();
//
//		return levelDtos;
//	}
	private final WordRepo wordRepo;
	private final TopicRepo topicRepo;
	private final UserAccountRepo userAccountRepo;
	private final HistoryRepo historyRepo;
	private final LevelRepo levelRepo;

	@GetMapping("/api/words/test")
	public List<WordsEntity> getWordsByTid() {

		return new ArrayList<>(wordRepo.findByTid(1));
	}

	@GetMapping("/api/words/getAllLevels")
	public ResponseEntity<ApiResult<ArrayList<LevelDto>>>  getAllLevels(HttpServletRequest request) {
		ArrayList<LevelDto> levelDtos = new ArrayList<>();
		List<LevelsEntity> levelsEntities = levelRepo.findAll();
		String email = BearerTokenUtil.getUserName(request);
		UsersEntity user = userAccountRepo.findFirstByEmail(email);
		for (int i = 0; i < levelsEntities.size(); i++) {
			int lid = levelsEntities.get(i).getLid();
			List<TopicsEntity> topicsByLid = topicRepo.findByLid(lid);
			int numTopic = topicRepo.findByLid(lid).size();
			int numWord = 0;
			for (TopicsEntity t : topicsByLid) {
				numWord += wordRepo.findByTid(t.getTid()).size();
			}
			//levelDtos.add(new LevelDto(levelsEntities.get(i).getLevel(), , 1, 1));
			if(user == null){
				levelDtos.add(new LevelDto(levelsEntities.get(i).getLid(), levelsEntities.get(i).getLevel(), numTopic , numWord, 0));
			}
			else{
				List<HistoryEntity> h = historyRepo.findByUid(user.getUid());
				long numCorrects = h.stream().filter(l -> l.getIscorrect() == 1).count();
				levelDtos.add(new LevelDto(levelsEntities.get(i).getLid(), levelsEntities.get(i).getLevel(), numTopic , numWord, (int) (numCorrects/numWord)));
			}
		}
		ApiResult<ArrayList<LevelDto>> result = ApiResult.create(HttpStatus.OK, "Lấy thành công level", levelDtos);
		return ResponseEntity.ok(result);
	}
	@GetMapping("/api/words/getTopicByLid")
	public ResponseEntity<ApiResult<TopicDto>> getTopicByLid(HttpServletRequest request, @RequestParam(defaultValue = "1") int lid){
		ApiResult<TopicDto> result;
		LevelsEntity level = levelRepo.findByLid(lid);
		if(level == null) result = ApiResult.create(HttpStatus.BAD_REQUEST, "Không tìm thấy level này", null);
		else{
			String email = BearerTokenUtil.getUserName(request);
			UsersEntity user = userAccountRepo.findFirstByEmail(email);

			ArrayList<TopicsEntity> topicsEntities = new ArrayList<>(topicRepo.findByLid(lid));
			ArrayList<ExtendedTopicDto> extendedTopicDtos = new ArrayList<>();
			int numWord = 0;
			for (TopicsEntity t : topicsEntities) {
				numWord += wordRepo.findByTid(t.getTid()).size();
				if(user != null) {
					List<HistoryEntity> h = historyRepo.findByUid(user.getUid());
					List<HistoryEntity> historyByTid = h.stream().filter(l -> wordRepo.findByWid(l.getWid()).getTid().equals(t.getTid())).toList();
					Set<Integer> seenWid = new HashSet<>();
					for(HistoryEntity historyEntity : historyByTid){
						if(!seenWid.contains(historyEntity.getWid())){
							seenWid.add(historyEntity.getWid());
						}
					}
					//int correct = (int) h.stream().filter(l -> wordRepo.findByWid(l.getWid()).getTid().equals(t.getTid())).count();
					extendedTopicDtos.add(new ExtendedTopicDto(t.getTid(), t.getTopic(), t.getLid(),(int)(seenWid.size()*100)/(wordRepo.findByTid(t.getTid()).size())));
				}
				else{
					extendedTopicDtos.add(new ExtendedTopicDto(t.getTid(), t.getTopic(), t.getLid(),0));
				}
			}
			LevelDto levelDto = new LevelDto(level.getLid(), level.getLevel(), topicsEntities.size(), numWord,0);
			result = ApiResult.create(HttpStatus.OK, "Lấy danh sách chủ đề thành công", new TopicDto(levelDto, extendedTopicDtos));
		}
		return ResponseEntity.ok(result);
	}
	@GetMapping("/api/words/getWordsByTid")
	public ResponseEntity<ApiResult<ArrayList<WordQuestion>>> getWordsByTid(HttpServletRequest request, @RequestParam(defaultValue = "1") int tid){
		Random random = new Random();
		ApiResult<ArrayList<WordQuestion>> result = null;
		ArrayList<WordQuestion> data = new ArrayList<>();
		TopicsEntity topicsEntity = topicRepo.findByTid(tid);
		if(topicsEntity == null){
			//.....
		}else{
			List<WordsEntity> wordsEntities = wordRepo.findByTid(tid);
			List<WordsEntity> words;
			words = wordRepo.findByTid(tid + 1);
			if(words == null){
				words = wordRepo.findByTid(tid - 1);
			}
			Collections.shuffle(wordsEntities);

			for(WordsEntity w : wordsEntities){
				Collections.shuffle(words);
				int ran = random.nextInt(4);
				switch (ran){
					case 0:
						data.add(new WordQuestion(w, w.getWord(), words.get(1).getWord(), words.get(2).getWord(), words.get(0).getWord()));
						break;
					case 1:
						data.add(new WordQuestion(w, words.get(1).getWord(), w.getWord(), words.get(2).getWord(), words.get(0).getWord()));
						break;
					case 2:
						data.add(new WordQuestion(w, words.get(1).getWord(),  words.get(2).getWord(), w.getWord(), words.get(0).getWord()));
						break;
					case 3:
						data.add(new WordQuestion(w, words.get(1).getWord(), words.get(2).getWord(), words.get(0).getWord(), w.getWord()));
						break;
				}
			}
			result = ApiResult.create(HttpStatus.OK, "Lấy danh sách câu hỏi thành công", data);

		}
		return ResponseEntity.ok(result);
	}
	@GetMapping("/api/words/getTest")
	public ResponseEntity<ApiResult<ArrayList<WordQuestion>>> getTestWords(HttpServletRequest request){
		ApiResult<ArrayList<WordQuestion>> result;
		ArrayList<WordQuestion> data = new ArrayList<>();
		String email = BearerTokenUtil.getUserName(request);
		if(email == null) return ResponseEntity.ok(ApiResult.create(HttpStatus.BAD_REQUEST, "Bạn phải đăng nhập để tiếp tục", null));
		UsersEntity user = userAccountRepo.findFirstByEmail(email);
		if(user == null) return ResponseEntity.ok(ApiResult.create(HttpStatus.BAD_REQUEST, "Bạn phải đăng nhập để tiếp tục", null));
		List<HistoryEntity> historyByUid = historyRepo.findByUid(user.getUid());
		Set<Integer> seenWid = new HashSet<>();
		List<HistoryEntity> re = new ArrayList<>();
		for(HistoryEntity historyEntity : historyByUid){
			if(!seenWid.contains(historyEntity.getWid())){
				seenWid.add(historyEntity.getWid());
				re.add(historyEntity);
			}
		}
		if(seenWid.size() <= 10) return ResponseEntity.ok(ApiResult.create(HttpStatus.NOT_FOUND, "Bạn phải học trên 10 câu để truy cập", null));
		Collections.shuffle(re);
		Random random = new Random();
		int count = 0;
		for(HistoryEntity h : re){
			if(count >= 20) break;
			count+=1;
			WordsEntity w = wordRepo.findByWid(h.getWid());
			List<WordsEntity> words = wordRepo.findByTid(w.getTid() + 1);
			if(words == null){
				words = wordRepo.findByTid(w.getTid() - 1);
			}
			Collections.shuffle(words);
			int ran = random.nextInt(4);
			switch (ran){
				case 0:
					data.add(new WordQuestion(w, w.getWord(), words.get(1).getWord(), words.get(2).getWord(), words.get(0).getWord()));
					break;
				case 1:
					data.add(new WordQuestion(w, words.get(1).getWord(), w.getWord(), words.get(2).getWord(), words.get(0).getWord()));
					break;
				case 2:
					data.add(new WordQuestion(w, words.get(1).getWord(),  words.get(2).getWord(), w.getWord(), words.get(0).getWord()));
					break;
				case 3:
					data.add(new WordQuestion(w, words.get(1).getWord(), words.get(2).getWord(), words.get(0).getWord(), w.getWord()));
					break;
			}
		}
		return ResponseEntity.ok(ApiResult.create(HttpStatus.OK, "Lấy danh sách câu hỏi thành công", data));
	}
	@GetMapping("/api/words/getScrambleWord")
	public ResponseEntity<ApiResult<WordsEntity>> getScrambleWord(){
		ArrayList<WordsEntity> words = new ArrayList<>(wordRepo.findAll());
		Collections.shuffle(words);
		WordsEntity w = words.get(0);
		return ResponseEntity.ok(ApiResult.create(HttpStatus.OK, "Lấy ScrambleWord thành công", w));
	}
}