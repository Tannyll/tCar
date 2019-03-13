package com.emirci.Model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (this.id == null) {
            return false;
        }

        if (obj instanceof AbstractEntity && obj.getClass().equals(getClass())) {
            return this.id.equals(((AbstractEntity) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }
}
