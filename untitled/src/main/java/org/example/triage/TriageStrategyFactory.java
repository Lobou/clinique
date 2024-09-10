package org.example.triage;

import org.example.TriageType;

public class TriageStrategyFactory {
    public TriageStrategy create(TriageType triageType) {
        return switch (triageType) {
            case FIFO -> new TriageStragegyFifo();
            case GRAVITY -> new TriageStrategyGravity();
        };
    }
}
