package com.hibernate.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department", schema = "his", catalog = "")
public class DepartmentEntity {
    private String depid;
    private String name;
    private String py;

    @Id
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
    @Column(name = "py", nullable = false, length = 8)
    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(depid, that.depid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(py, that.py);
    }

    @Override
    public int hashCode() {

        return Objects.hash(depid, name, py);
    }
}
