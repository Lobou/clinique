package org.example;

import org.example.triage.TriageStrategy;
import org.example.triage.TriageStrategyFactory;

public class CommunityCenter {
    private TriageStrategy triageStrategy;

    public CommunityCenter(TriageType triageType) {
        TriageStrategyFactory triageStrategyFactory = new TriageStrategyFactory();
        triageStrategy = triageStrategyFactory.create(triageType);
    }

    public void triagePatient(Patient patient) {
        triageStrategy.triagePatient(patient);
    }

    public Patient getNextPatient(){
        return triageStrategy.getNext();
    }
}
