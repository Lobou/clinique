package org.example.triage;

import org.example.VisibleSymptom;

import java.util.List;

public class TriageStrategyGravity implements TriageStrategy {
    public void triagePatient(String name, int gravity, VisibleSymptom visibleSymptom, List<String> queue) {
        if (visibleSymptom != VisibleSymptom.CORONAVIRUS){
            if (isGrave(gravity)) {
                queue.add(0, name);
            }
            queue.add(name);
        }
    }

    private boolean isGrave(int gravity){
        return gravity > 5;
    }
}
