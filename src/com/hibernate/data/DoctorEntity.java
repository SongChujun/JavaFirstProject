package com.hibernate.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "doctor", schema = "his", catalog = "")
public class DoctorEntity {
    private String docid;
    private String depid;
    private String name;
    private String py;
    private String password;
    private byte speciallist;
    private Timestamp lastLoginDatetime;

    @Id
    @Column(name = "docid", nullable = false, length = 6)
    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    @Basic
    @Column(name = "depid", nullable = false, length = 6)
    public String getDepid() {
        return depid;
    }
    public void setDepid(String depid) {
        this.depid = depid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Basic
    @Column(name = "py", nullable = true, length = 4)
    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 8)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "last_login_datetime", nullable = true)
    public Timestamp getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    public void setLastLoginDatetime(Timestamp lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorEntity that = (DoctorEntity) o;
        return speciallist == that.speciallist &&
                Objects.equals(docid, that.docid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(py, that.py) &&
                Objects.equals(password, that.password) &&
                Objects.equals(lastLoginDatetime, that.lastLoginDatetime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(docid, name, py, password, speciallist, lastLoginDatetime);
    }
}
