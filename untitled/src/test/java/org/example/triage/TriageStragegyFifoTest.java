package org.example.triage;

import org.example.EmptyQueueException;
import org.example.Patient;
import org.example.PatientFixture;
import org.example.TriageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriageStragegyFifoTest {

    @Test
    public void GivenANewFifoTriage_WhenGettingNextPatient_ThenEmptyQueueExceptionRaised(){
        TriageStrategy strategy = new TriageStrategyFactory().create(TriageType.FIFO);

        assertThrows(EmptyQueueException.class, strategy::getNext);
    }

    @Test
    public void GivenMultiplePatients_WhenGettingNextPatient_GetFirstArrived(){
        TriageStrategy strategy = new TriageStrategyFactory().create(TriageType.FIFO);
        Patient firstPatient = new PatientFixture().withName("First Patient").create();
        strategy.triagePatient(firstPatient);
        strategy.triagePatient(new PatientFixture().withName("Second Patient").create());

        Patient nextPatient = strategy.getNext();

        assertEquals(firstPatient, nextPatient);
    }

}