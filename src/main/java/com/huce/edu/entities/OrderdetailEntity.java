package com.huce.edu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "ORDERDETAIL", schema = "dbo", catalog = "dbedu")
public class OrderdetailEntity {
    @Id
    @Column(name = "ODID")
    private Integer odid;
    @Basic
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

    public Integer getOdid() {
        return odid;
    }

    public void setOdid(Integer odid) {
        this.odid = odid;
    }

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

        if (odid != null ? !odid.equals(that.odid) : that.odid != null) return false;
        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;
        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (pprice != null ? !pprice.equals(that.pprice) : that.pprice != null) return false;
        if (pquantity != null ? !pquantity.equals(that.pquantity) : that.pquantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = odid != null ? odid.hashCode() : 0;
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (pprice != null ? pprice.hashCode() : 0);
        result = 31 * result + (pquantity != null ? pquantity.hashCode() : 0);
        return result;
    }
}
