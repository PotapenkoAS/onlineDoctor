package com.vlsu.demo.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Test {
    private Long testId;
    private Long clientId;
    private Date date;
    private Client clientByClientId;

    @Id
    @Column(name = "test_id")
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "client_id")
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return testId.equals(test.testId) &&
                clientId.equals(test.clientId) &&
                Objects.equals(date, test.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, clientId, date);
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false, insertable = false, updatable = false)
    public Client getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(Client clientByClientId) {
        this.clientByClientId = clientByClientId;
    }
}
