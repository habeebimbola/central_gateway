package com.centralgateway.task;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Actor")
public class DVDActor implements Serializable {

    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actor_id")
    @SequenceGenerator(name = "actor_id", sequenceName = "actor_actor_id_seq")
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column (name = "LAST_NAME")
    private String lastName;

    @Column(name = "LAST_UPDATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
