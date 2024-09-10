package org.example;

import org.example.triage.TriageStrategy;
import org.example.triage.TriageStrategyFactory;

import static org.example.VisibleSymptom.SPRAIN;

public class Clinic {
    private TriageStrategy doctorTriageStrategy;
    private TriageStrategy radiologyTriageStrategy;
    public Clinic(TriageType doctorTriageType, TriageType radiologyTriageType) {
        TriageStrategyFactory triageFactory = new TriageStrategyFactory();
        doctorTriageStrategy = triageFactory.create(doctorTriageType);
        radiologyTriageStrategy = triageFactory.create(radiologyTriageType);
    }

    public void triagePatient(Patient patient) {
        doctorTriageStrategy.triagePatient(patient);
        if (isRadiologyPatient(patient.symptom))
        {
            radiologyTriageStrategy.triagePatient(patient);
        }
    }

    public Patient getNextDoctorPatient()
    {
        return doctorTriageStrategy.getNext();
    }

    public Patient getNextRadiologyPatient() {
        return radiologyTriageStrategy.getNext();
    }

    private boolean isRadiologyPatient(VisibleSymptom visibleSymptom) {
        return visibleSymptom == VisibleSymptom.BROKEN_BONE || visibleSymptom == SPRAIN;
    }
}