package org.example.triage;

import org.example.Patient;
import java.util.List;

public interface TriageStrategy {
    public void triagePatient(Patient patient);

    public Patient getNext();
}
