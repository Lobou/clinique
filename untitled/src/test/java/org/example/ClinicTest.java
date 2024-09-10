package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicTest {

    String A_NAME = "Monsieur Soleil";
    String ANOTHER_NAME = "Docteur Queue";
    int A_GRAVITY = 3;
    VisibleSymptom A_NON_RADIOLOGY_SYMPTOM = VisibleSymptom.COLD;
    VisibleSymptom A_RADIOLOGY_SYMPTOM = VisibleSymptom.SPRAIN;

    @Test
    void WhenCreatingClinic_ThenDoctorQueueShouldBeEmpty()
    {
        Clinic clinic = new Clinic();

        assertThrows(EmptyQueueException.class, clinic::getNextDoctorPatient);
    }

    @Test
    void WhenCreatingClinic_ThenRadiologyQueueShouldBeEmpty()
    {
        Clinic clinic = new Clinic();

        assertThrows(EmptyQueueException.class, clinic::getNextRadiologyPatient);
    }

    @Test
    void GivenMultiplePatients_WhenGetNextDoctorPatient_ThenGetFirstPatientTriaged(){
        Clinic clinic = new Clinic();
        clinic.triagePatient(A_NAME, A_GRAVITY, A_NON_RADIOLOGY_SYMPTOM);
        clinic.triagePatient(ANOTHER_NAME, A_GRAVITY, A_NON_RADIOLOGY_SYMPTOM);

        String nextPatient = clinic.getNextDoctorPatient();

        assertEquals(A_NAME, nextPatient);
    }

    @Test
    void GivenANonRadiologyPatient_WhenTriage_ThenRadiologyQueueShouldBeEmpty(){
        Clinic clinic = new Clinic();

        clinic.triagePatient(A_NAME, A_GRAVITY, A_NON_RADIOLOGY_SYMPTOM);

        assertThrows(EmptyQueueException.class, clinic::getNextRadiologyPatient);
    }

    @Test
    void GivenARadiologyPatient_WhenTriage_ThenPatientShouldBeNextInRadiologyQueue(){
        Clinic clinic = new Clinic();

        clinic.triagePatient(A_NAME, A_GRAVITY, A_RADIOLOGY_SYMPTOM);

        assertEquals(A_NAME, clinic.getNextRadiologyPatient() );
    }

}