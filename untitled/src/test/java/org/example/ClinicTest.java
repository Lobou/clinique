package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClinicTest {
    TriageType A_TRIAGE_TYPE = TriageType.FIFO;

    @Test
    void GivenANewClinic_WhenGettingNextDoctorPatient_ThenEmptyQueueExceptionRaised()
    {
        Clinic clinic = new Clinic(A_TRIAGE_TYPE, A_TRIAGE_TYPE);

        assertThrows(EmptyQueueException.class, clinic::getNextDoctorPatient);
    }

    @Test
    void GivenANewClinic_WhenGettingNextRadiologyPatient_ThenEmptyQueueExceptionRaised()
    {
        Clinic clinic = new Clinic(A_TRIAGE_TYPE, A_TRIAGE_TYPE);

        assertThrows(EmptyQueueException.class, clinic::getNextRadiologyPatient);
    }

    @Test
    void GivenAFifoClinicWithMultiplePatients_WhenGetNextDoctorPatient_ThenGetFirstPatientTriaged(){
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
        Patient firstPatient = new PatientFixture().create();
        clinic.triagePatient(firstPatient);
        clinic.triagePatient(new PatientFixture().create());

        Patient nextPatient = clinic.getNextDoctorPatient();

        assertEquals(firstPatient, nextPatient);
    }

    @Test
    void GivenAFifoClinic_WhenTriageNonRadiologyPatient_ThenRadiologyQueueShouldBeEmpty(){
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);

        clinic.triagePatient(new PatientFixture().create());

        assertThrows(EmptyQueueException.class, clinic::getNextRadiologyPatient);
    }

    @Test
    void GivenAFifoClinic_WhenTriageRadiologyPatient_ThenPatientShouldBeNextInRadiologyQueue(){
        Clinic clinic = new Clinic(TriageType.FIFO, TriageType.FIFO);
        Patient patient = new PatientFixture().withRadiologySymptom().create();

        clinic.triagePatient(patient);

        assertEquals(patient, clinic.getNextRadiologyPatient() );
    }

    @Test
    void GivenAGravityClinicWithAPatientInDoctorQueue_WhenTriageSeverePatient_ThenSeverePatientIsFirstInQueue(){
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.GRAVITY);
        clinic.triagePatient(new PatientFixture().create());
        Patient severePatient = new PatientFixture().withSevereGravity().create();

        clinic.triagePatient(severePatient);

        assertEquals(severePatient, clinic.getNextDoctorPatient() );
    }

    @Test
    void GivenAGravityClinicWithAPatientInRadiologyQueue_WhenTriageSeverePatient_ThenSeverePatientIsFirstInRadiologyQueue(){
        Clinic clinic = new Clinic(TriageType.GRAVITY, TriageType.GRAVITY);
        clinic.triagePatient(new PatientFixture().withRadiologySymptom().create());
        Patient severePatient = new PatientFixture().withRadiologySymptom().withSevereGravity().create();

        clinic.triagePatient(severePatient);

        assertEquals(severePatient, clinic.getNextRadiologyPatient() );
    }

    @Test
    void GivenAnEmptyClinic_WhenTriageCoronavirusPatient_ThenQueueIsEmpty(){
        Clinic clinic = new Clinic(A_TRIAGE_TYPE, A_TRIAGE_TYPE);
        Patient coronaPatient = new PatientFixture().withCoronavirus().create();

        clinic.triagePatient(coronaPatient);

        assertThrows(EmptyQueueException.class, clinic::getNextDoctorPatient);
    }
}