package com.huce.edu.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDERDETAIL", schema = "dbo", catalog = "dbedu")
public class OrderdetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OID")
    private Integer oid;
    @Basic
    @Column(name = "PID")
    private Integer pid;
    @Basic
    @Column(name = "PPRICE")
    private Double pprice;
    @Basic
    @Column(name = "PQUANTITY")
    private Integer pquantity;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Double getPprice() {
        return pprice;
    }

    public void setPprice(Double pprice) {
        this.pprice = pprice;
    }

    public Integer getPquantity() {
        return pquantity;
    }

    public void setPquantity(Integer pquantity) {
        this.pquantity = pquantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderdetailEntity that = (OrderdetailEntity) o;

        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (pprice != null ? !pprice.equals(that.pprice) : that.pprice != null) return false;
        if (pquantity != null ? !pquantity.equals(that.pquantity) : that.pquantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid != null ? oid.hashCode() : 0;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (pprice != null ? pprice.hashCode() : 0);
        result = 31 * result + (pquantity != null ? pquantity.hashCode() : 0);
        return result;
    }
}
