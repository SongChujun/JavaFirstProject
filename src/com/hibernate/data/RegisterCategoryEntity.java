package com.hibernate.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "register_category", schema = "his", catalog = "")
public class RegisterCategoryEntity {
    private String catid;
    private String name;
    private String py;
    private String depid;
    private byte speciallist;
    private int maxRegNumber;
    private BigDecimal regFee;

    @Id
    @Column(name = "catid", nullable = false, length = 6)
    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 12)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "py", nullable = false, length = 4)
    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    @Basic
    @Column(name = "depid", nullable = false, length =6)
    public String getDepid() {
        return depid;
    }

    public void setDepid(String depid) {
        this.depid = depid;
    }

    @Basic
    @Column(name = "speciallist", nullable = false)
    public byte getSpeciallist() {
        return speciallist;
    }

    public void setSpeciallist(byte speciallist) {
        this.speciallist = speciallist;
    }

    @Basic
    @Column(name = "max_reg_number", nullable = false)
    public int getMaxRegNumber() {
        return maxRegNumber;
    }

    public void setMaxRegNumber(int maxRegNumber) {
        this.maxRegNumber = maxRegNumber;
    }

    @Basic
    @Column(name = "reg_fee", nullable = false, precision = 2)
    public BigDecimal getRegFee() {
        return regFee;
    }

    public void setRegFee(BigDecimal regFee) {
        this.regFee = regFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterCategoryEntity that = (RegisterCategoryEntity) o;
        return speciallist == that.speciallist &&
                maxRegNumber == that.maxRegNumber &&
                Objects.equals(catid, that.catid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(py, that.py) &&
                Objects.equals(regFee, that.regFee);
    }

    @Override
    public int hashCode() {

        return Objects.hash(catid, name, py, speciallist, maxRegNumber, regFee);
    }
}
