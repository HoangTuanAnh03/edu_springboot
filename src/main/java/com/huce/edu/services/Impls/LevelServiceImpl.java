package com.huce.edu.services.Impls;

import com.huce.edu.entities.*;
import com.huce.edu.models.dto.LevelDto;
import com.huce.edu.models.dto.ListTopicByLevel;
import com.huce.edu.models.dto.TopicByLevel;
import com.huce.edu.repositories.*;
import com.huce.edu.services.LevelService;
import com.nimbusds.oauth2.sdk.util.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final WordRepo wordRepo;
    private final TopicRepo topicRepo;
    private final HistoryRepo historyRepo;
    private final LevelRepo levelRepo;
    @Override
    public ListTopicByLevel getTopicByLevel(int lid, UserEntity user) {
        LevelEntity level = levelRepo.findByLid(lid);
        ArrayList<TopicEntity> listTopic = new ArrayList<>(topicRepo.findByLid(lid));
        ArrayList<TopicByLevel> listTopicDto = new ArrayList<>();
        int uid = 0;
        if (user != null) {
            uid = user.getUid();
        }
        int numCorrects = 0;
        int numWord = 0; // tổng số từ
        for (TopicEntity topic : listTopic) {
            int tid = topic.getTid();
            List<Integer> wids = wordRepo.findByTid(tid).stream().map(WordEntity::getWid).toList();
            int numWordsByTopic = wids.size();
            int numCorrectAns = historyRepo.findByUidAndIscorrectAndWidIn(uid, 1, wids).size();
            numWord+=numWordsByTopic;
            numCorrects+=numCorrectAns;
            listTopicDto.add(new TopicByLevel(tid, topic.getTopic(), topic.getLid(), numWordsByTopic!=0?(int)numCorrectAns*100/numWordsByTopic:0));
        }
        int totalProcessLevel = numWord != 0?(int)numCorrects*100/numWord : 0; // số từ đúng / tổng số từ
        LevelDto levelDto = new LevelDto(level.getLid(), level.getLevel(), listTopic.size(), numWord, totalProcessLevel);
        return new ListTopicByLevel(levelDto, listTopicDto);
    }

    @Override
    public ArrayList<LevelDto> getAll(UserEntity user) {
        ArrayList<LevelDto> listLevelDto = new ArrayList<>();
        List<LevelEntity> listLevelsEntities = levelRepo.findAll();
        int uid;
        if(user != null) uid = user.getUid();
		else {
			uid = 0;
		}

		for (LevelEntity levelEntity : listLevelsEntities) {
            List<TopicEntity> listTopicsByLid = topicRepo.findByLid(levelEntity.getLid());
            int numTopic = listTopicsByLid.size();
            List<Integer> words = new ArrayList<>(wordRepo.getByTidIn(listTopicsByLid.stream().map(TopicEntity::getTid).toList())).stream().map(WordId::getWid).toList();
            long numCorrects = historyRepo.findByUid(uid).stream().filter(t -> t.getIscorrect() == 1 && words.contains(t.getWid())).count();
            listLevelDto.add(new LevelDto(levelEntity.getLid(), levelEntity.getLevel(), numTopic, words.size(), (int) (numCorrects*100 / words.size())));

        }
        return listLevelDto;
    }
}
