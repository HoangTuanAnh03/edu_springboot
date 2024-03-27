package com.huce.edu.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCT", schema = "dbo", catalog = "dbedu")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PID")
    private Integer pid;
    @Basic
    @Column(name = "PNAME")
    private String pname;
    @Basic
    @Column(name = "PPRICE")
    private Double pprice;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getPprice() {
        return pprice;
    }

    public void setPprice(Double pprice) {
        this.pprice = pprice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (pid != null ? !pid.equals(that.pid) : that.pid != null) return false;
        if (pname != null ? !pname.equals(that.pname) : that.pname != null) return false;
        if (pprice != null ? !pprice.equals(that.pprice) : that.pprice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        result = 31 * result + (pname != null ? pname.hashCode() : 0);
        result = 31 * result + (pprice != null ? pprice.hashCode() : 0);
        return result;
    }
}
