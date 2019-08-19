package com.vlsu.demo.model.compositeKey;

import java.io.Serializable;

public class TestSymptomKey implements Serializable {
    private int testId;
    private int symptomId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }
}
