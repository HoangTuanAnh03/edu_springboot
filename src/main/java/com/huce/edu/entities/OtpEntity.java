package com.huce.edu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "OTP", schema = "dbo", catalog = "dbedu")
public class OtpEntity {
    @Id
    @Column(name = "UID")
    private Integer uid;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "FAILATTEMPTS")
    private Integer failattempts;
    @Basic
    @Column(name = "EXP")
    private Date exp;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFailattempts() {
        return failattempts;
    }

    public void setFailattempts(Integer failattempts) {
        this.failattempts = failattempts;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OtpEntity otpEntity = (OtpEntity) o;

        if (uid != null ? !uid.equals(otpEntity.uid) : otpEntity.uid != null) return false;
        if (email != null ? !email.equals(otpEntity.email) : otpEntity.email != null) return false;
        if (code != null ? !code.equals(otpEntity.code) : otpEntity.code != null) return false;
        if (failattempts != null ? !failattempts.equals(otpEntity.failattempts) : otpEntity.failattempts != null)
            return false;
        if (exp != null ? !exp.equals(otpEntity.exp) : otpEntity.exp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (failattempts != null ? failattempts.hashCode() : 0);
        result = 31 * result + (exp != null ? exp.hashCode() : 0);
        return result;
    }
}
