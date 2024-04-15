package com.huce.edu.services.Impls;

import com.huce.edu.entities.LevelEntity;
import com.huce.edu.entities.TopicEntity;
import com.huce.edu.entities.UserEntity;
import com.huce.edu.entities.WordEntity;
import com.huce.edu.models.dto.LevelDto;
import com.huce.edu.models.dto.ListTopicByLevel;
import com.huce.edu.models.dto.TopicByLevel;
import com.huce.edu.repositories.HistoryRepo;
import com.huce.edu.repositories.LevelRepo;
import com.huce.edu.repositories.TopicRepo;
import com.huce.edu.repositories.WordRepo;
import com.huce.edu.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
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
            numWord += numWordsByTopic;
            numCorrects += numCorrectAns;
            listTopicDto.add(new TopicByLevel(tid, topic.getTopic(), topic.getLid(), numWordsByTopic != 0 ? (float) numCorrectAns * 100 / numWordsByTopic : 0));
        }
        float totalProcessLevel = numWord != 0 ? (float) numCorrects * 100 / numWord : 0; // số từ đúng / tổng số từ
        LevelDto levelDto = LevelDto.create(level.getLid(), level.getLevel(), listTopic.size(), numWord, totalProcessLevel);
        return new ListTopicByLevel(levelDto, listTopicDto);
    }

    @Override
    public TopicEntity add(Integer lid, String name) {
        TopicEntity newTopic = TopicEntity.create(
                0,
                name,
                lid
        );
        topicRepo.save(newTopic);
        return topicRepo.findByTopicAndLid(name, lid);
    }

    @Override
    public TopicEntity edit(TopicEntity topicEntity) {
        topicRepo.save(topicEntity);
        return topicEntity;
    }

    @Override
    public TopicEntity delete(Integer id) {
        TopicEntity topic = topicRepo.findFirstByTid(id);
        topicRepo.delete(topic);
        return topic;
    }


}
