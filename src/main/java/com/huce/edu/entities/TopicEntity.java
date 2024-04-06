package com.huce.edu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "TOPICS", schema = "dbo", catalog = "dbedu")
public class TopicEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TID")
    private Integer tid;
    @Basic
    @Column(name = "TOPIC")
    private String topic;
    @Basic
    @Column(name = "LID")
    private Integer lid;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicEntity that = (TopicEntity) o;

        if (tid != null ? !tid.equals(that.tid) : that.tid != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (lid != null ? !lid.equals(that.lid) : that.lid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid != null ? tid.hashCode() : 0;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (lid != null ? lid.hashCode() : 0);
        return result;
    }
}
