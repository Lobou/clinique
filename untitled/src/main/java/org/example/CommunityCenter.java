package org.example;

import org.example.triage.TriageStrategy;
import org.example.triage.TriageStrategyFactory;

import java.util.ArrayList;
import java.util.List;

public class CommunityCenter {
    List<String> queue = new ArrayList<>();
    private TriageStrategy triageStrategy;

    public CommunityCenter(TriageType triageType) {
        TriageStrategyFactory triageStrategyFactory = new TriageStrategyFactory();
        triageStrategy = triageStrategyFactory.create(triageType);
    }

    public void triagePatient(String name, int gravity) {
        triageStrategy.triagePatient(name, gravity, null, queue);
    }
}
