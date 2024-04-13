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
        int numWord = 0;
        int totalProcessLevel = 0;
        for (TopicEntity t : listTopic) {
            numWord += wordRepo.findByTid(t.getTid()).size();
            if (user == null) {
                listTopicDto.add(new TopicByLevel(t.getTid(), t.getTopic(), t.getLid(), 0));
            } else {
                List<HistoryEntity> h = historyRepo.findByUid(user.getUid());
                List<HistoryEntity> historyByTid = h.stream().filter(row -> wordRepo.findFirstByWid(row.getWid()).getTid().equals(t.getTid())).toList();
                Set<Integer> seenWid = new HashSet<>();
                for (HistoryEntity historyEntity : historyByTid) {
                    seenWid.add(historyEntity.getWid());
                }
                int process = (int) (seenWid.size() * 100) / (wordRepo.findByTid(t.getTid()).size());
                totalProcessLevel += process;
                listTopicDto.add(new TopicByLevel(t.getTid(), t.getTopic(), t.getLid(), process));
            }
        }
        LevelDto levelDto = new LevelDto(level.getLid(), level.getLevel(), listTopic.size(), numWord, (int) totalProcessLevel/listTopic.size());

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
            List<WordId> words = wordRepo.findByTidIn(listTopicsByLid.stream().map(TopicEntity::getTid).toList());
            long numCorrects = historyRepo.findByUid(uid).stream().filter(t -> t.getIscorrect() == 1 && words.contains(t.getWid())).count();
            listLevelDto.add(new LevelDto(levelEntity.getLid(), levelEntity.getLevel(), numTopic, words.size(), (int) (numCorrects / words.size())));
        }
        return listLevelDto;
    }
}
