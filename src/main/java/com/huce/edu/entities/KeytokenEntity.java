package com.huce.edu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "KEYTOKEN", schema = "dbo", catalog = "dbedu")
public class KeytokenEntity {
    @Id
    @Column(name = "UID")
    private Integer uid;
    @Basic
    @Column(name = "PRIVATEKEY")
    private String privatekey;
    @Basic
    @Column(name = "PUBLICKEY")
    private String publickey;
    @Basic
    @Column(name = "REFRESHTOKEN")
    private String refreshtoken;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(String privatekey) {
        this.privatekey = privatekey;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeytokenEntity that = (KeytokenEntity) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (privatekey != null ? !privatekey.equals(that.privatekey) : that.privatekey != null) return false;
        if (publickey != null ? !publickey.equals(that.publickey) : that.publickey != null) return false;
        if (refreshtoken != null ? !refreshtoken.equals(that.refreshtoken) : that.refreshtoken != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (privatekey != null ? privatekey.hashCode() : 0);
        result = 31 * result + (publickey != null ? publickey.hashCode() : 0);
        result = 31 * result + (refreshtoken != null ? refreshtoken.hashCode() : 0);
        return result;
    }
}
