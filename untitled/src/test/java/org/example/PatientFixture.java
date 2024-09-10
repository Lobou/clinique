package org.example;

public class PatientFixture {
    private String name = "FirstPatient";
    private int gravity = 2;
    private VisibleSymptom symptom = VisibleSymptom.MIGRAINE;

    public Patient create(){
        return new Patient(name, gravity, symptom);
    }

    public PatientFixture withRadiologySymptom(){
        this.symptom = VisibleSymptom.SPRAIN;
        return this;
    }

    public PatientFixture withSevereGravity(){
        this.gravity = 7;
        return this;
    }

    public PatientFixture withName(String name){
        this.name = name;
        return this;
    }

    public PatientFixture withCoronavirus(){
        this.symptom = VisibleSymptom.CORONAVIRUS;
        return this;
    }
}
