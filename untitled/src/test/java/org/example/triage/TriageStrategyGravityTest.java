package org.example.triage;

import org.example.EmptyQueueException;
import org.example.Patient;
import org.example.PatientFixture;
import org.example.TriageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriageStrategyGravityTest {

    @Test
    public void GivenANewGravityTriage_WhenGettingNextPatient_ThenEmptyQueueExceptionRaised(){
        TriageStrategy strategy = new TriageStrategyFactory().create(TriageType.GRAVITY);

        assertThrows(EmptyQueueException.class, strategy::getNext);
    }

    @Test
    public void GivenMultipleNonSeverePatients_WhenGettingNextPatient_GetFirstArrived(){
        TriageStrategy strategy = new TriageStrategyFactory().create(TriageType.GRAVITY);
        Patient firstPatient = new PatientFixture().create();
        strategy.triagePatient(firstPatient);
        strategy.triagePatient(new PatientFixture().create());

        Patient nextPatient = strategy.getNext();

        assertEquals(firstPatient, nextPatient);
    }

    @Test
    public void GivenANonSeverePatientInQueue_WhenTriageSeverePatient_SeverePatientNextInQueue(){
        TriageStrategy strategy = new TriageStrategyFactory().create(TriageType.GRAVITY);
        strategy.triagePatient(new PatientFixture().create());
        Patient severePatient = new PatientFixture().withSevereGravity().create();

        strategy.triagePatient(severePatient);

        assertEquals(severePatient, strategy.getNext());
    }
}