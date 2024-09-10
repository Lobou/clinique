package org.example.triage;

import org.example.VisibleSymptom;

import java.util.List;

public class TriageStragegyFifo implements TriageStrategy {
    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom, List<String> queue) {
        queue.add(name);
    }
}
