package com.vlsu.demo.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Test {
    private int testId;
    private Timestamp date;
    private String symptoms;
    private int userId;
    private User userByUserId;
    private Collection<TestSymptom> testSymptomsByTestId;

    public Test(int userId, String symptoms, Timestamp date) {
        this.userId = userId;
        this.symptoms = symptoms;
        this.date = date;
    }

    public Test() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_id", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (testId != test.testId) return false;
        if (userId != test.userId) return false;
        return Objects.equals(date, test.date);
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + userId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "symptoms")
    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "testByTestId")
    public Collection<TestSymptom> getTestSymptomsByTestId() {
        return testSymptomsByTestId;
    }

    public void setTestSymptomsByTestId(Collection<TestSymptom> testSymptomsByTestId) {
        this.testSymptomsByTestId = testSymptomsByTestId;
    }
}
