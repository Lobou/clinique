package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.VisibleSymptom.SPRAIN;

public class Clinic {

    private List<String> doctorQueue = new ArrayList<>();
    private List<String> radiologyQueue = new ArrayList<>();
    public Clinic() {
    }

    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {
        doctorQueue.add(name);
        if (isRadiologyPatient(visibleSymptom))
        {
            radiologyQueue.add(name);
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

    private boolean isRadiologyPatient(VisibleSymptom visibleSymptom)
    {
        return visibleSymptom == VisibleSymptom.BROKEN_BONE || visibleSymptom == SPRAIN;
    }
}