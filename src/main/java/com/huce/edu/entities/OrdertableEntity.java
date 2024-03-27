package com.huce.edu.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ORDERTABLE", schema = "dbo", catalog = "dbedu")
public class OrdertableEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OID")
    private Integer oid;
    @Basic
    @Column(name = "UID")
    private Integer uid;
    @Basic
    @Column(name = "ORDERDATE")
    private Date orderdate;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdertableEntity that = (OrdertableEntity) o;

        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (orderdate != null ? !orderdate.equals(that.orderdate) : that.orderdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid != null ? oid.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (orderdate != null ? orderdate.hashCode() : 0);
        return result;
    }
}
