package org.example.triage;

import org.example.VisibleSymptom;

import java.util.List;

import static org.example.VisibleSymptom.SPRAIN;

public interface TriageStrategy {
    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom, List<String> queue);
}
