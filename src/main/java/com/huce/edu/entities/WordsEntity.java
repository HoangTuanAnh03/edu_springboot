package com.huce.edu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "WORDS", schema = "dbo", catalog = "dbedu")
public class WordsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "WID")
    private Integer wid;
    @Basic
    @Column(name = "WORD")
    private String word;
    @Basic
    @Column(name = "PRONUN")
    private String pronun;
    @Basic
    @Column(name = "ENTYPE")
    private String entype;
    @Basic
    @Column(name = "VIETYPE")
    private String vietype;
    @Basic
    @Column(name = "VOICE")
    private String voice;
    @Basic
    @Column(name = "PHOTO")
    private String photo;
    @Basic
    @Column(name = "MEANING")
    private String meaning;
    @Basic
    @Column(name = "ENDESC")
    private String endesc;
    @Basic
    @Column(name = "VIEDESC")
    private String viedesc;
    @Basic
    @Column(name = "LEVEL")
    private String level;
    @Basic
    @Column(name = "TOPIC")
    private String topic;
    @Basic
    @Column(name = "TID")
    private Integer tid;

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronun() {
        return pronun;
    }

    public void setPronun(String pronun) {
        this.pronun = pronun;
    }

    public String getEntype() {
        return entype;
    }

    public void setEntype(String entype) {
        this.entype = entype;
    }

    public String getVietype() {
        return vietype;
    }

    public void setVietype(String vietype) {
        this.vietype = vietype;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getEndesc() {
        return endesc;
    }

    public void setEndesc(String endesc) {
        this.endesc = endesc;
    }

    public String getViedesc() {
        return viedesc;
    }

    public void setViedesc(String viedesc) {
        this.viedesc = viedesc;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordsEntity that = (WordsEntity) o;

        if (wid != null ? !wid.equals(that.wid) : that.wid != null) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;
        if (pronun != null ? !pronun.equals(that.pronun) : that.pronun != null) return false;
        if (entype != null ? !entype.equals(that.entype) : that.entype != null) return false;
        if (vietype != null ? !vietype.equals(that.vietype) : that.vietype != null) return false;
        if (voice != null ? !voice.equals(that.voice) : that.voice != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        if (meaning != null ? !meaning.equals(that.meaning) : that.meaning != null) return false;
        if (endesc != null ? !endesc.equals(that.endesc) : that.endesc != null) return false;
        if (viedesc != null ? !viedesc.equals(that.viedesc) : that.viedesc != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wid != null ? wid.hashCode() : 0;
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + (pronun != null ? pronun.hashCode() : 0);
        result = 31 * result + (entype != null ? entype.hashCode() : 0);
        result = 31 * result + (vietype != null ? vietype.hashCode() : 0);
        result = 31 * result + (voice != null ? voice.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (meaning != null ? meaning.hashCode() : 0);
        result = 31 * result + (endesc != null ? endesc.hashCode() : 0);
        result = 31 * result + (viedesc != null ? viedesc.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
