package org.example;

import org.example.triage.TriageStrategy;
import org.example.triage.TriageStrategyFactory;

import java.util.ArrayList;
import java.util.List;

import static org.example.VisibleSymptom.SPRAIN;

public class Clinic {

    private List<String> doctorQueue = new ArrayList<>();
    private List<String> radiologyQueue = new ArrayList<>();
    private TriageStrategy doctorTriageStrategy;
    private TriageStrategy radiologyTriageStrategy;
    public Clinic(TriageType doctorTriageType, TriageType radiologyTriageType) {
        TriageStrategyFactory triageFactory = new TriageStrategyFactory();
        doctorTriageStrategy = triageFactory.create(doctorTriageType);
        radiologyTriageStrategy = triageFactory.create(radiologyTriageType);
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        doctorTriageStrategy.triagePatient(name, gravity, visibleSymptom, doctorQueue);
        if (isRadiologyPatient(visibleSymptom))
        {
            radiologyTriageStrategy.triagePatient(name, gravity, visibleSymptom, radiologyQueue);
        }
    }

    public String getNextDoctorPatient()
    {
        if (doctorQueue.isEmpty()) {
            throw new EmptyQueueException();
        }
        return doctorQueue.remove(0);
    }

    public String getNextRadiologyPatient() {
        if (radiologyQueue.isEmpty())
        {
            throw new EmptyQueueException();
        }
        return radiologyQueue.remove(0);
    }

    private boolean isRadiologyPatient(VisibleSymptom visibleSymptom) {
        return visibleSymptom == VisibleSymptom.BROKEN_BONE || visibleSymptom == SPRAIN;
    }
}