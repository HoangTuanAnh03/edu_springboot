package com.huce.edu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "TESTHISTORY", schema = "dbo", catalog = "dbedu")
public class TesthistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "THID")
    private Integer thid;
    @Basic
    @Column(name = "UID")
    private Integer uid;
    @Basic
    @Column(name = "NUMQUES")
    private Integer numques;
    @Basic
    @Column(name = "NUMCORRECTQUES")
    private Integer numcorrectques;
    @Basic
    @Column(name = "TDATE")
    private Date tdate;

    public Integer getThid() {
        return thid;
    }

    public void setThid(Integer thid) {
        this.thid = thid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNumques() {
        return numques;
    }

    public void setNumques(Integer numques) {
        this.numques = numques;
    }

    public Integer getNumcorrectques() {
        return numcorrectques;
    }

    public void setNumcorrectques(Integer numcorrectques) {
        this.numcorrectques = numcorrectques;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TesthistoryEntity that = (TesthistoryEntity) o;

        if (thid != null ? !thid.equals(that.thid) : that.thid != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (numques != null ? !numques.equals(that.numques) : that.numques != null) return false;
        if (numcorrectques != null ? !numcorrectques.equals(that.numcorrectques) : that.numcorrectques != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = thid != null ? thid.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (numques != null ? numques.hashCode() : 0);
        result = 31 * result + (numcorrectques != null ? numcorrectques.hashCode() : 0);
        return result;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }
}
