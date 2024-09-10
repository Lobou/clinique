package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicTest {

    String A_NAME = "Monsieur Soleil";
    String ANOTHER_NAME = "Docteur Queue";
    int A_GRAVITY = 3;
    int A_GRAVE_GRAVITY = 7;
    VisibleSymptom A_NON_RADIOLOGY_SYMPTOM = VisibleSymptom.COLD;
    VisibleSymptom A_RADIOLOGY_SYMPTOM = VisibleSymptom.SPRAIN;
    TriageType A_TRIAGE_TYPE = TriageType.FIFO;

    @Test
    void WhenCreatingClinic_ThenDoctorQueueShouldBeEmpty()
    {
        Clinic clinic = new Clinic(A_TRIAGE_TYPE, A_TRIAGE_TYPE);

        assertThrows(EmptyQueueException.class, clinic::getNextDoctorPatient);
    }

    @Test
    void WhenCreatingClinic_ThenRadiologyQueueShouldBeEmpty()
    {
        Clinic clinic = new Clinic(A_TRIAGE_TYPE, A_TRIAGE_TYPE);

        assertThrows(EmptyQueueException.class, clinic::getNextRadiologyPatient);
    }

    @Test
    void GivenAFifoClinicWithMultiplePatients_WhenGetNextDoctorPatient_ThenGetFirstPatientTriaged(){
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
        clinic.triagePatient(A_NAME, A_GRAVITY, A_NON_RADIOLOGY_SYMPTOM);
        clinic.triagePatient(ANOTHER_NAME, A_GRAVITY, A_NON_RADIOLOGY_SYMPTOM);

        String nextPatient = clinic.getNextDoctorPatient();

        assertEquals(A_NAME, nextPatient);
    }

    @Test
    void GivenAFifoClinic_WhenTriageNonRadiologyPatient_ThenRadiologyQueueShouldBeEmpty(){
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient(A_NAME, A_GRAVITY, A_NON_RADIOLOGY_SYMPTOM);

        assertThrows(EmptyQueueException.class, clinic::getNextRadiologyPatient);
    }

    @Test
    void GivenAFifoClinic_WhenTriageRadiologyPatient_ThenPatientShouldBeNextInRadiologyQueue(){
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient(A_NAME, A_GRAVITY, A_RADIOLOGY_SYMPTOM);

        assertEquals(A_NAME, clinic.getNextRadiologyPatient() );
    }

    @Test
    void GivenAGravityClinicWithAPatientInDoctorQueue_WhenTriageGravePatient_ThenGravePatientIsFirstInQueue(){
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.GRAVITY);
        clinic.triagePatient(A_NAME, A_GRAVITY, A_NON_RADIOLOGY_SYMPTOM);

        clinic.triagePatient(ANOTHER_NAME, A_GRAVE_GRAVITY, A_NON_RADIOLOGY_SYMPTOM);

        assertEquals(ANOTHER_NAME, clinic.getNextDoctorPatient() );
    }

    @Test
    void GivenAGravityClinicWithAPatientInRadiologyQueue_WhenTriageGravePatient_ThenGravePatientIsFirstInRadiologyQueue(){
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.GRAVITY);
        clinic.triagePatient(A_NAME, A_GRAVITY, A_RADIOLOGY_SYMPTOM);

        clinic.triagePatient(ANOTHER_NAME, A_GRAVE_GRAVITY, A_RADIOLOGY_SYMPTOM);

        assertEquals(ANOTHER_NAME, clinic.getNextRadiologyPatient() );
    }

    @Test
    void GivenAnEmptyClinic_WhenTriageCoronavirusPatient_ThenQueueIsEmpty(){
        Clinic clinic = new Clinic(A_TRIAGE_TYPE, A_TRIAGE_TYPE);

        clinic.triagePatient(A_NAME, A_GRAVITY, VisibleSymptom.CORONAVIRUS);

        assertThrows(EmptyQueueException.class, clinic::getNextDoctorPatient);
    }
}