package org.example;

public class Patient {
    public String name;
    public int gravity;
    public VisibleSymptom symptom;

    public Patient(String name, int gravity, VisibleSymptom symptom){
        this.name = name;
        this.gravity = gravity;
        this.symptom = symptom;
    }
}
