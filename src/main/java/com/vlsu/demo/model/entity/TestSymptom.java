package com.vlsu.demo.model.entity;

import com.vlsu.demo.model.compositeKey.TestSymptomKey;

import javax.persistence.*;

@Entity
@IdClass(TestSymptomKey.class)
@Table(name = "test_symptom", schema = "online_doctor")
public class TestSymptom {
    private int testId;
    private int symptomId;
    private Test testByTestId;
    private Symptom symptomBySymptomId;

    @Basic
    @Id
    @Column(name = "test_id")
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Id
    @Column(name = "symptom_id")
    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestSymptom that = (TestSymptom) o;

        if (testId != that.testId) return false;
        return symptomId == that.symptomId;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + symptomId;
        return result;
    }

    public TestSymptom(int testId, int symptomId) {
        this.testId = testId;
        this.symptomId = symptomId;
    }

    public TestSymptom(){
    }

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "test_id", nullable = false, insertable = false, updatable = false)
    public Test getTestByTestId() {
        return testByTestId;
    }

    public void setTestByTestId(Test testByTestId) {
        this.testByTestId = testByTestId;
    }

    @ManyToOne
    @JoinColumn(name = "symptom_id", referencedColumnName = "symptom_id", nullable = false, insertable = false, updatable = false)
    public Symptom getSymptomBySymptomId() {
        return symptomBySymptomId;
    }

    public void setSymptomBySymptomId(Symptom symptomBySymptomId) {
        this.symptomBySymptomId = symptomBySymptomId;
    }
}
