package com.hibernate.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "register", schema = "his", catalog = "")
public class RegisterEntity {
    private String regId;
    private String catid;
    private String docid;
    private String pid;
    private int currentRegCount;
    private byte unreg;
    private BigDecimal regFee;
    private Timestamp regDatetime;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "reg_id", nullable = false, length = 6)
    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    @Basic
    @Column(name = "catid", nullable = false)
    public String getCatid() {
        return catid;
    }
    public void setCatid(String catid) {
        this.catid=catid;
    }

    @Basic
    @Column(name = "docid", nullable = false)
    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }


    @Basic
    @Column(name = "pid", nullable = false)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "current_reg_count", nullable = false)
    public int getCurrentRegCount() {
        return currentRegCount;
    }

    public void setCurrentRegCount(int currentRegCount) {
        this.currentRegCount = currentRegCount;
    }

    @Basic
    @Column(name = "unreg", nullable = false)
    public byte getUnreg() {
        return unreg;
    }

    public void setUnreg(byte unreg) {
        this.unreg = unreg;
    }

    @Basic
    @Column(name = "reg_fee", nullable = false, precision = 2)
    public BigDecimal getRegFee() {
        return regFee;
    }

    public void setRegFee(BigDecimal regFee) {
        this.regFee = regFee;
    }

    @Basic
    @Column(name = "reg_datetime", nullable = false)
    public Timestamp getRegDatetime() {
        return regDatetime;
    }

    public void setRegDatetime(Timestamp regDatetime) {
        this.regDatetime = regDatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterEntity that = (RegisterEntity) o;
        return currentRegCount == that.currentRegCount &&
                unreg == that.unreg &&
                Objects.equals(regId, that.regId) &&
                Objects.equals(regFee, that.regFee) &&
                Objects.equals(regDatetime, that.regDatetime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(regId, currentRegCount, unreg, regFee, regDatetime);
    }

}
