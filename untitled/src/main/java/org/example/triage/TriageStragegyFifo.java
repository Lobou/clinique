package org.example.triage;

import org.example.EmptyQueueException;
import org.example.Patient;
import org.example.VisibleSymptom;

import java.util.ArrayList;
import java.util.List;

public class TriageStragegyFifo implements TriageStrategy {

    private List<Patient> queue = new ArrayList<>();

    public void triagePatient(Patient patient) {
        if (patient.symptom != VisibleSymptom.CORONAVIRUS){
            queue.add(patient);
        }
    }

    public Patient getNext(){
        if (queue.isEmpty()){
            throw new EmptyQueueException();
        }
        return queue.remove(0);
    }
}
