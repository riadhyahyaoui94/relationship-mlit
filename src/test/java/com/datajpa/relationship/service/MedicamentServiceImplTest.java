package com.datajpa.relationship.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.datajpa.relationship.dto.request.MedicamentRequestDto;
import com.datajpa.relationship.dto.response.MedicamentResponseDto;
import com.datajpa.relationship.model.Accueil;
import com.datajpa.relationship.model.Assurance;
import com.datajpa.relationship.model.Consultant;
import com.datajpa.relationship.model.Consultation;
import com.datajpa.relationship.model.DetailsPatient;
import com.datajpa.relationship.model.DossierMedical;
import com.datajpa.relationship.model.Medicament;
import com.datajpa.relationship.model.Ordonnance;
import com.datajpa.relationship.model.Paiement;
import com.datajpa.relationship.model.Patient;
import com.datajpa.relationship.model.SalleAttente;
import com.datajpa.relationship.model.ServiceConsultation;
import com.datajpa.relationship.repository.MedicamentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MedicamentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MedicamentServiceImplTest {
    @MockBean
    private MedicamentRepository medicamentRepository;

    @Autowired
    private MedicamentServiceImpl medicamentServiceImpl;

    @MockBean
    private OrdonnanceService ordonnanceService;

    /**
     * Method under test: {@link MedicamentServiceImpl#addMedicament(MedicamentRequestDto)}
     */
    @Test
    void testAddMedicament() {
        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.medicamentRepository.save((Medicament) any())).thenReturn(medicament);

        MedicamentRequestDto medicamentRequestDto = new MedicamentRequestDto();
        medicamentRequestDto.setDureeDePrise("Duree De Prise");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentRequestDto.setNomMedicament("Nom Medicament");
        medicamentRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        MedicamentResponseDto actualAddMedicamentResult = this.medicamentServiceImpl.addMedicament(medicamentRequestDto);
        assertEquals("Duree De Prise", actualAddMedicamentResult.getDureeDePrise());
        assertTrue(actualAddMedicamentResult.getOrdonnances().isEmpty());
        assertEquals("Nom Medicament", actualAddMedicamentResult.getNomMedicament());
        assertEquals(123L, actualAddMedicamentResult.getId().longValue());
        verify(this.medicamentRepository).save((Medicament) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#addMedicament(MedicamentRequestDto)}
     */
    @Test
    void testAddMedicament2() {
        when(this.medicamentRepository.save((Medicament) any())).thenThrow(new IllegalArgumentException("foo"));

        MedicamentRequestDto medicamentRequestDto = new MedicamentRequestDto();
        medicamentRequestDto.setDureeDePrise("Duree De Prise");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentRequestDto.setNomMedicament("Nom Medicament");
        medicamentRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.addMedicament(medicamentRequestDto));
        verify(this.medicamentRepository).save((Medicament) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicaments()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMedicaments() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:221)
        //       at java.util.stream.StreamSupport.stream(StreamSupport.java:68)
        //       at com.datajpa.relationship.service.MedicamentServiceImpl.getMedicaments(MedicamentServiceImpl.java:47)
        //   In order to prevent getMedicaments()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getMedicaments().
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.medicamentRepository.findAll()).thenReturn((Iterable<Medicament>) mock(Iterable.class));
        this.medicamentServiceImpl.getMedicaments();
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicaments()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetMedicaments2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: foo
        //       at com.datajpa.relationship.service.MedicamentServiceImpl.getMedicaments(MedicamentServiceImpl.java:47)
        //   In order to prevent getMedicaments()
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getMedicaments().
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.medicamentRepository.findAll()).thenReturn((Iterable<Medicament>) mock(Iterable.class));
        new IllegalArgumentException("foo");
        this.medicamentServiceImpl.getMedicaments();
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicaments()}
     */
    @Test
    void testGetMedicaments3() {
        when(this.medicamentRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.getMedicaments());
        verify(this.medicamentRepository).findAll();
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicamentById(Long)}
     */
    @Test
    void testGetMedicamentById() {
        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);
        MedicamentResponseDto actualMedicamentById = this.medicamentServiceImpl.getMedicamentById(123L);
        assertEquals("Duree De Prise", actualMedicamentById.getDureeDePrise());
        assertTrue(actualMedicamentById.getOrdonnances().isEmpty());
        assertEquals("Nom Medicament", actualMedicamentById.getNomMedicament());
        assertEquals(123L, actualMedicamentById.getId().longValue());
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicamentById(Long)}
     */
    @Test
    void testGetMedicamentById2() {
        when(this.medicamentRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.getMedicamentById(123L));
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicamentById(Long)}
     */
    @Test
    void testGetMedicamentById3() {
        when(this.medicamentRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.getMedicamentById(123L));
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicament(Long)}
     */
    @Test
    void testGetMedicament() {
        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(medicament, this.medicamentServiceImpl.getMedicament(123L));
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicament(Long)}
     */
    @Test
    void testGetMedicament2() {
        when(this.medicamentRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.getMedicament(123L));
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#getMedicament(Long)}
     */
    @Test
    void testGetMedicament3() {
        when(this.medicamentRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.getMedicament(123L));
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#deleteMedicament(Long)}
     */
    @Test
    void testDeleteMedicament() {
        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        doNothing().when(this.medicamentRepository).delete((Medicament) any());
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);
        MedicamentResponseDto actualDeleteMedicamentResult = this.medicamentServiceImpl.deleteMedicament(123L);
        assertEquals("Duree De Prise", actualDeleteMedicamentResult.getDureeDePrise());
        assertTrue(actualDeleteMedicamentResult.getOrdonnances().isEmpty());
        assertEquals("Nom Medicament", actualDeleteMedicamentResult.getNomMedicament());
        assertEquals(123L, actualDeleteMedicamentResult.getId().longValue());
        verify(this.medicamentRepository).findById((Long) any());
        verify(this.medicamentRepository).delete((Medicament) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#deleteMedicament(Long)}
     */
    @Test
    void testDeleteMedicament2() {
        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        doThrow(new IllegalArgumentException("foo")).when(this.medicamentRepository).delete((Medicament) any());
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.deleteMedicament(123L));
        verify(this.medicamentRepository).findById((Long) any());
        verify(this.medicamentRepository).delete((Medicament) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#deleteMedicament(Long)}
     */
    @Test
    void testDeleteMedicament3() {
        doNothing().when(this.medicamentRepository).delete((Medicament) any());
        when(this.medicamentRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.deleteMedicament(123L));
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#editMedicament(Long, MedicamentRequestDto)}
     */
    @Test
    void testEditMedicament() {
        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);

        MedicamentRequestDto medicamentRequestDto = new MedicamentRequestDto();
        medicamentRequestDto.setDureeDePrise("Duree De Prise");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentRequestDto.setNomMedicament("Nom Medicament");
        medicamentRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        MedicamentResponseDto actualEditMedicamentResult = this.medicamentServiceImpl.editMedicament(123L,
                medicamentRequestDto);
        assertEquals("Duree De Prise", actualEditMedicamentResult.getDureeDePrise());
        assertTrue(actualEditMedicamentResult.getOrdonnances().isEmpty());
        assertEquals("Nom Medicament", actualEditMedicamentResult.getNomMedicament());
        assertEquals(123L, actualEditMedicamentResult.getId().longValue());
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#editMedicament(Long, MedicamentRequestDto)}
     */
    @Test
    void testEditMedicament2() {
        when(this.medicamentRepository.findById((Long) any())).thenReturn(Optional.empty());

        MedicamentRequestDto medicamentRequestDto = new MedicamentRequestDto();
        medicamentRequestDto.setDureeDePrise("Duree De Prise");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentRequestDto.setNomMedicament("Nom Medicament");
        medicamentRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        assertThrows(IllegalArgumentException.class,
                () -> this.medicamentServiceImpl.editMedicament(123L, medicamentRequestDto));
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#editMedicament(Long, MedicamentRequestDto)}
     */
    @Test
    void testEditMedicament3() {
        when(this.medicamentRepository.findById((Long) any())).thenThrow(new IllegalArgumentException("foo"));

        MedicamentRequestDto medicamentRequestDto = new MedicamentRequestDto();
        medicamentRequestDto.setDureeDePrise("Duree De Prise");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicamentRequestDto.setNomMedicament("Nom Medicament");
        medicamentRequestDto.setOrdonnanceId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicamentRequestDto.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        assertThrows(IllegalArgumentException.class,
                () -> this.medicamentServiceImpl.editMedicament(123L, medicamentRequestDto));
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#addOrdonnanceToMedicament(Long, Long)}
     */
    @Test
    void testAddOrdonnanceToMedicament() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        ArrayList<Consultation> consultationList = new ArrayList<>();
        consultant.setConsultations(consultationList);
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(serviceConsultation1);
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(new SalleAttente());
        serviceConsultation2.setTypeService("Type Service");

        Consultant consultant2 = new Consultant();
        consultant2.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant2.setConsultations(new ArrayList<>());
        consultant2.setDossierMedicals(new ArrayList<>());
        consultant2.setEmail("jane.doe@example.org");
        consultant2.setFonction("Fonction");
        consultant2.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant2.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        consultant2.setNom("Nom");
        consultant2.setOrdonnances(new ArrayList<>());
        consultant2.setPassword("iloveyou");
        consultant2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant2.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        consultant2.setPrenom("Prenom");
        consultant2.setPriseRDVs(new ArrayList<>());
        consultant2.setRoles(new HashSet<>());
        consultant2.setServiceConsultation(serviceConsultation2);
        consultant2.setTelephone("4105551212");
        consultant2.setTitre("Titre");
        consultant2.setUsername("janedoe");

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(new Patient());
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Paiement paiement = new Paiement();
        paiement.setAccueil(new Accueil());
        paiement.setAssurance(new Assurance());
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(detailsPatient);
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(paiement);
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(salleAttente2);
        patient.setTauxAssurance(10.0d);

        SalleAttente salleAttente3 = new SalleAttente();
        salleAttente3.setEtage("Etage");
        salleAttente3.setId(123L);
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente3.setLastUpdatedAt(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente3.setNom("Nom");
        salleAttente3.setNumOrdre(10);
        salleAttente3.setPatients(new ArrayList<>());
        salleAttente3.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente3.setPostedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente3.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation3 = new ServiceConsultation();
        serviceConsultation3.setConsultants(new ArrayList<>());
        serviceConsultation3.setDossierMedicals(new ArrayList<>());
        serviceConsultation3.setId(123L);
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation3.setLastUpdatedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation3.setNomService("Nom Service");
        serviceConsultation3.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation3.setPostedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation3.setPriseRDVS(new ArrayList<>());
        serviceConsultation3.setSalleAttente(salleAttente3);
        serviceConsultation3.setTypeService("Type Service");

        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setAncienPatient(true);
        dossierMedical.setConsultant(consultant2);
        dossierMedical.setConsultationEffectuee("Consultation Effectuee");
        dossierMedical.setConsultations(new ArrayList<>());
        dossierMedical.setId(123L);
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dossierMedical.setLastUpdatedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        dossierMedical.setNomPatient("Nom Patient");
        dossierMedical.setPatient(patient);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dossierMedical.setPostedAt(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        dossierMedical.setPrenomPatient("Prenom Patient");
        dossierMedical.setResultatPrestation("Resultat Prestation");
        dossierMedical.setServiceConsultation(serviceConsultation3);

        ServiceConsultation serviceConsultation4 = new ServiceConsultation();
        serviceConsultation4.setConsultants(new ArrayList<>());
        serviceConsultation4.setDossierMedicals(new ArrayList<>());
        serviceConsultation4.setId(123L);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation4.setLastUpdatedAt(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation4.setNomService("Nom Service");
        serviceConsultation4.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation4.setPostedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation4.setPriseRDVS(new ArrayList<>());
        serviceConsultation4.setSalleAttente(new SalleAttente());
        serviceConsultation4.setTypeService("Type Service");

        Consultant consultant3 = new Consultant();
        consultant3.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant3.setConsultations(new ArrayList<>());
        consultant3.setDossierMedicals(new ArrayList<>());
        consultant3.setEmail("jane.doe@example.org");
        consultant3.setFonction("Fonction");
        consultant3.setId(123L);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant3.setLastUpdatedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        consultant3.setNom("Nom");
        consultant3.setOrdonnances(new ArrayList<>());
        consultant3.setPassword("iloveyou");
        consultant3.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant3.setPostedAt(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        consultant3.setPrenom("Prenom");
        consultant3.setPriseRDVs(new ArrayList<>());
        consultant3.setRoles(new HashSet<>());
        consultant3.setServiceConsultation(serviceConsultation4);
        consultant3.setTelephone("4105551212");
        consultant3.setTitre("Titre");
        consultant3.setUsername("janedoe");

        Consultant consultant4 = new Consultant();
        consultant4.setCalendrier(null);
        consultant4.setConsultations(new ArrayList<>());
        consultant4.setDossierMedicals(new ArrayList<>());
        consultant4.setEmail("jane.doe@example.org");
        consultant4.setFonction("Fonction");
        consultant4.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant4.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        consultant4.setNom("Nom");
        consultant4.setOrdonnances(new ArrayList<>());
        consultant4.setPassword("iloveyou");
        consultant4.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant4.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        consultant4.setPrenom("Prenom");
        consultant4.setPriseRDVs(new ArrayList<>());
        consultant4.setRoles(new HashSet<>());
        consultant4.setServiceConsultation(new ServiceConsultation());
        consultant4.setTelephone("4105551212");
        consultant4.setTitre("Titre");
        consultant4.setUsername("janedoe");

        DossierMedical dossierMedical1 = new DossierMedical();
        dossierMedical1.setAncienPatient(true);
        dossierMedical1.setConsultant(new Consultant());
        dossierMedical1.setConsultationEffectuee("Consultation Effectuee");
        dossierMedical1.setConsultations(new ArrayList<>());
        dossierMedical1.setId(123L);
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dossierMedical1.setLastUpdatedAt(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        dossierMedical1.setNomPatient("Nom Patient");
        dossierMedical1.setPatient(new Patient());
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dossierMedical1.setPostedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        dossierMedical1.setPrenomPatient("Prenom Patient");
        dossierMedical1.setResultatPrestation("Resultat Prestation");
        dossierMedical1.setServiceConsultation(new ServiceConsultation());

        Ordonnance ordonnance = new Ordonnance();
        ordonnance.setConsultant(new Consultant());
        ordonnance.setConsultation(new Consultation());
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance.setDateOrdonnance(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance.setExamens(new ArrayList<>());
        ordonnance.setId(123L);
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance.setLastUpdatedAt(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance.setMedicaments(new ArrayList<>());
        ordonnance.setNatureOrdonnance("Nature Ordonnance");
        ordonnance.setNomPatient("Nom Patient");
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance.setPostedAt(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(new DetailsPatient());
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(new Paiement());
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(new SalleAttente());
        patient1.setTauxAssurance(10.0d);

        Consultation consultation = new Consultation();
        consultation.setConsultant(consultant4);
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation.setDateConsultation(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        consultation.setDiagnostic("Diagnostic");
        consultation.setDossierMedical(dossierMedical1);
        consultation.setId(123L);
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation.setLastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        consultation.setOrdonnance(ordonnance);
        consultation.setPatient(patient1);
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        consultation.setTypeConsultation("Type Consultation");

        Ordonnance ordonnance1 = new Ordonnance();
        ordonnance1.setConsultant(consultant3);
        ordonnance1.setConsultation(consultation);
        LocalDateTime atStartOfDayResult50 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance1.setDateOrdonnance(Date.from(atStartOfDayResult50.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance1.setExamens(new ArrayList<>());
        ordonnance1.setId(123L);
        LocalDateTime atStartOfDayResult51 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance1.setLastUpdatedAt(Date.from(atStartOfDayResult51.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance1.setMedicaments(new ArrayList<>());
        ordonnance1.setNatureOrdonnance("Nature Ordonnance");
        ordonnance1.setNomPatient("Nom Patient");
        LocalDateTime atStartOfDayResult52 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance1.setPostedAt(Date.from(atStartOfDayResult52.atZone(ZoneId.of("UTC")).toInstant()));

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult53 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setDateNaissance(Date.from(atStartOfDayResult53.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setDetailsPatient(new DetailsPatient());
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult54 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setLastUpdatedAt(Date.from(atStartOfDayResult54.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(new Paiement());
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult55 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setPostedAt(Date.from(atStartOfDayResult55.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(new SalleAttente());
        patient2.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient1 = new DetailsPatient();
        detailsPatient1.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult56 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setHeurePriseCharge(Date.from(atStartOfDayResult56.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setId(123L);
        LocalDateTime atStartOfDayResult57 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setLastUpdatedAt(Date.from(atStartOfDayResult57.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setPatient(patient2);
        LocalDateTime atStartOfDayResult58 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setPostedAt(Date.from(atStartOfDayResult58.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setServiceAffectation("Service Affectation");
        detailsPatient1.setStatus("Status");

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult59 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult59.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult60 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult60.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult61 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult61.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult62 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult62.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult63 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult63.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil);
        paiement1.setAssurance(assurance);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult64 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult64.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult65 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult65.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult66 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult66.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente4 = new SalleAttente();
        salleAttente4.setEtage("Etage");
        salleAttente4.setId(123L);
        LocalDateTime atStartOfDayResult67 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente4.setLastUpdatedAt(Date.from(atStartOfDayResult67.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente4.setNom("Nom");
        salleAttente4.setNumOrdre(10);
        salleAttente4.setPatients(new ArrayList<>());
        salleAttente4.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult68 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente4.setPostedAt(Date.from(atStartOfDayResult68.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente4.setServiceConsultations(new ArrayList<>());

        Patient patient3 = new Patient();
        patient3.setAssurance("Assurance");
        patient3.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult69 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient3.setDateNaissance(Date.from(atStartOfDayResult69.atZone(ZoneId.of("UTC")).toInstant()));
        patient3.setDetailsPatient(detailsPatient1);
        patient3.setDomicile("Domicile");
        patient3.setDossierMedicals(new ArrayList<>());
        patient3.setEmail("jane.doe@example.org");
        patient3.setEstAssure(true);
        patient3.setGenre("Genre");
        patient3.setId(123L);
        LocalDateTime atStartOfDayResult70 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient3.setLastUpdatedAt(Date.from(atStartOfDayResult70.atZone(ZoneId.of("UTC")).toInstant()));
        patient3.setNom("Nom");
        patient3.setNumIdendite(3);
        patient3.setPaiement(paiement1);
        patient3.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult71 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient3.setPostedAt(Date.from(atStartOfDayResult71.atZone(ZoneId.of("UTC")).toInstant()));
        patient3.setPrenom("Prenom");
        patient3.setPriseRDVs(new ArrayList<>());
        patient3.setSalleAttente(salleAttente4);
        patient3.setTauxAssurance(10.0d);

        Consultation consultation1 = new Consultation();
        consultation1.setConsultant(consultant1);
        LocalDateTime atStartOfDayResult72 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation1.setDateConsultation(Date.from(atStartOfDayResult72.atZone(ZoneId.of("UTC")).toInstant()));
        consultation1.setDiagnostic("Diagnostic");
        consultation1.setDossierMedical(dossierMedical);
        consultation1.setId(123L);
        LocalDateTime atStartOfDayResult73 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation1.setLastUpdatedAt(Date.from(atStartOfDayResult73.atZone(ZoneId.of("UTC")).toInstant()));
        consultation1.setOrdonnance(ordonnance1);
        consultation1.setPatient(patient3);
        LocalDateTime atStartOfDayResult74 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation1.setPostedAt(Date.from(atStartOfDayResult74.atZone(ZoneId.of("UTC")).toInstant()));
        consultation1.setTypeConsultation("Type Consultation");

        Ordonnance ordonnance2 = new Ordonnance();
        ordonnance2.setConsultant(consultant);
        ordonnance2.setConsultation(consultation1);
        LocalDateTime atStartOfDayResult75 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance2.setDateOrdonnance(Date.from(atStartOfDayResult75.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance2.setExamens(new ArrayList<>());
        ordonnance2.setId(123L);
        LocalDateTime atStartOfDayResult76 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance2.setLastUpdatedAt(Date.from(atStartOfDayResult76.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance2.setMedicaments(new ArrayList<>());
        ordonnance2.setNatureOrdonnance("Nature Ordonnance");
        ordonnance2.setNomPatient("Nom Patient");
        LocalDateTime atStartOfDayResult77 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance2.setPostedAt(Date.from(atStartOfDayResult77.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.ordonnanceService.getOrdonnance((Long) any())).thenReturn(ordonnance2);

        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult78 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult78.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult79 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult79.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);
        MedicamentResponseDto actualAddOrdonnanceToMedicamentResult = this.medicamentServiceImpl
                .addOrdonnanceToMedicament(123L, 123L);
        assertEquals("Duree De Prise", actualAddOrdonnanceToMedicamentResult.getDureeDePrise());
        assertEquals(consultationList, actualAddOrdonnanceToMedicamentResult.getOrdonnances());
        assertEquals("Nom Medicament", actualAddOrdonnanceToMedicamentResult.getNomMedicament());
        assertEquals(123L, actualAddOrdonnanceToMedicamentResult.getId().longValue());
        verify(this.ordonnanceService).getOrdonnance((Long) any());
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#addOrdonnanceToMedicament(Long, Long)}
     */
    @Test
    void testAddOrdonnanceToMedicament2() {
        when(this.ordonnanceService.getOrdonnance((Long) any())).thenThrow(new IllegalArgumentException("foo"));

        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> this.medicamentServiceImpl.addOrdonnanceToMedicament(123L, 123L));
        verify(this.ordonnanceService).getOrdonnance((Long) any());
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#removeOrdonnanceFromMedicament(Long)}
     */
    @Test
    void testRemoveOrdonnanceFromMedicament() {
        SalleAttente salleAttente = new SalleAttente();
        salleAttente.setEtage("Etage");
        salleAttente.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setNom("Nom");
        salleAttente.setNumOrdre(10);
        salleAttente.setPatients(new ArrayList<>());
        salleAttente.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation = new ServiceConsultation();
        serviceConsultation.setConsultants(new ArrayList<>());
        serviceConsultation.setDossierMedicals(new ArrayList<>());
        serviceConsultation.setId(123L);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setLastUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setNomService("Nom Service");
        serviceConsultation.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation.setPostedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation.setPriseRDVS(new ArrayList<>());
        serviceConsultation.setSalleAttente(salleAttente);
        serviceConsultation.setTypeService("Type Service");

        Consultant consultant = new Consultant();
        consultant.setCalendrier(new RollingCalendar("2020-03-01"));
        ArrayList<Consultation> consultationList = new ArrayList<>();
        consultant.setConsultations(consultationList);
        consultant.setDossierMedicals(new ArrayList<>());
        consultant.setEmail("jane.doe@example.org");
        consultant.setFonction("Fonction");
        consultant.setId(123L);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setLastUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setNom("Nom");
        consultant.setOrdonnances(new ArrayList<>());
        consultant.setPassword("iloveyou");
        consultant.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant.setPostedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        consultant.setPrenom("Prenom");
        consultant.setPriseRDVs(new ArrayList<>());
        consultant.setRoles(new HashSet<>());
        consultant.setServiceConsultation(serviceConsultation);
        consultant.setTelephone("4105551212");
        consultant.setTitre("Titre");
        consultant.setUsername("janedoe");

        SalleAttente salleAttente1 = new SalleAttente();
        salleAttente1.setEtage("Etage");
        salleAttente1.setId(123L);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setLastUpdatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setNom("Nom");
        salleAttente1.setNumOrdre(10);
        salleAttente1.setPatients(new ArrayList<>());
        salleAttente1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente1.setPostedAt(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente1.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation1 = new ServiceConsultation();
        serviceConsultation1.setConsultants(new ArrayList<>());
        serviceConsultation1.setDossierMedicals(new ArrayList<>());
        serviceConsultation1.setId(123L);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setLastUpdatedAt(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setNomService("Nom Service");
        serviceConsultation1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation1.setPostedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation1.setPriseRDVS(new ArrayList<>());
        serviceConsultation1.setSalleAttente(salleAttente1);
        serviceConsultation1.setTypeService("Type Service");

        Consultant consultant1 = new Consultant();
        consultant1.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant1.setConsultations(new ArrayList<>());
        consultant1.setDossierMedicals(new ArrayList<>());
        consultant1.setEmail("jane.doe@example.org");
        consultant1.setFonction("Fonction");
        consultant1.setId(123L);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setLastUpdatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setNom("Nom");
        consultant1.setOrdonnances(new ArrayList<>());
        consultant1.setPassword("iloveyou");
        consultant1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant1.setPostedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        consultant1.setPrenom("Prenom");
        consultant1.setPriseRDVs(new ArrayList<>());
        consultant1.setRoles(new HashSet<>());
        consultant1.setServiceConsultation(serviceConsultation1);
        consultant1.setTelephone("4105551212");
        consultant1.setTitre("Titre");
        consultant1.setUsername("janedoe");

        ServiceConsultation serviceConsultation2 = new ServiceConsultation();
        serviceConsultation2.setConsultants(new ArrayList<>());
        serviceConsultation2.setDossierMedicals(new ArrayList<>());
        serviceConsultation2.setId(123L);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setLastUpdatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setNomService("Nom Service");
        serviceConsultation2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation2.setPostedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation2.setPriseRDVS(new ArrayList<>());
        serviceConsultation2.setSalleAttente(new SalleAttente());
        serviceConsultation2.setTypeService("Type Service");

        Consultant consultant2 = new Consultant();
        consultant2.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant2.setConsultations(new ArrayList<>());
        consultant2.setDossierMedicals(new ArrayList<>());
        consultant2.setEmail("jane.doe@example.org");
        consultant2.setFonction("Fonction");
        consultant2.setId(123L);
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant2.setLastUpdatedAt(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        consultant2.setNom("Nom");
        consultant2.setOrdonnances(new ArrayList<>());
        consultant2.setPassword("iloveyou");
        consultant2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant2.setPostedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        consultant2.setPrenom("Prenom");
        consultant2.setPriseRDVs(new ArrayList<>());
        consultant2.setRoles(new HashSet<>());
        consultant2.setServiceConsultation(serviceConsultation2);
        consultant2.setTelephone("4105551212");
        consultant2.setTitre("Titre");
        consultant2.setUsername("janedoe");

        DetailsPatient detailsPatient = new DetailsPatient();
        detailsPatient.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setHeurePriseCharge(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setId(123L);
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setLastUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setPatient(new Patient());
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient.setPostedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient.setServiceAffectation("Service Affectation");
        detailsPatient.setStatus("Status");

        Paiement paiement = new Paiement();
        paiement.setAccueil(new Accueil());
        paiement.setAssurance(new Assurance());
        paiement.setCodeFacture(1);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setDatePaiement(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setFacturations(new ArrayList<>());
        paiement.setId(123L);
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setLastUpdatedAt(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setMontantPrestation(10.0d);
        paiement.setNomAssurance("Nom Assurance");
        paiement.setPatients(new ArrayList<>());
        paiement.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement.setPostedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        paiement.setPrestation("Prestation");
        paiement.setTauxCouverture(10.0d);

        SalleAttente salleAttente2 = new SalleAttente();
        salleAttente2.setEtage("Etage");
        salleAttente2.setId(123L);
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setLastUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setNom("Nom");
        salleAttente2.setNumOrdre(10);
        salleAttente2.setPatients(new ArrayList<>());
        salleAttente2.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente2.setPostedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente2.setServiceConsultations(new ArrayList<>());

        Patient patient = new Patient();
        patient.setAssurance("Assurance");
        patient.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setDateNaissance(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setDetailsPatient(detailsPatient);
        patient.setDomicile("Domicile");
        patient.setDossierMedicals(new ArrayList<>());
        patient.setEmail("jane.doe@example.org");
        patient.setEstAssure(true);
        patient.setGenre("Genre");
        patient.setId(123L);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setLastUpdatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setNom("Nom");
        patient.setNumIdendite(3);
        patient.setPaiement(paiement);
        patient.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient.setPostedAt(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        patient.setPrenom("Prenom");
        patient.setPriseRDVs(new ArrayList<>());
        patient.setSalleAttente(salleAttente2);
        patient.setTauxAssurance(10.0d);

        SalleAttente salleAttente3 = new SalleAttente();
        salleAttente3.setEtage("Etage");
        salleAttente3.setId(123L);
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente3.setLastUpdatedAt(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente3.setNom("Nom");
        salleAttente3.setNumOrdre(10);
        salleAttente3.setPatients(new ArrayList<>());
        salleAttente3.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente3.setPostedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente3.setServiceConsultations(new ArrayList<>());

        ServiceConsultation serviceConsultation3 = new ServiceConsultation();
        serviceConsultation3.setConsultants(new ArrayList<>());
        serviceConsultation3.setDossierMedicals(new ArrayList<>());
        serviceConsultation3.setId(123L);
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation3.setLastUpdatedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation3.setNomService("Nom Service");
        serviceConsultation3.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation3.setPostedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation3.setPriseRDVS(new ArrayList<>());
        serviceConsultation3.setSalleAttente(salleAttente3);
        serviceConsultation3.setTypeService("Type Service");

        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setAncienPatient(true);
        dossierMedical.setConsultant(consultant2);
        dossierMedical.setConsultationEffectuee("Consultation Effectuee");
        dossierMedical.setConsultations(new ArrayList<>());
        dossierMedical.setId(123L);
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dossierMedical.setLastUpdatedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        dossierMedical.setNomPatient("Nom Patient");
        dossierMedical.setPatient(patient);
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dossierMedical.setPostedAt(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        dossierMedical.setPrenomPatient("Prenom Patient");
        dossierMedical.setResultatPrestation("Resultat Prestation");
        dossierMedical.setServiceConsultation(serviceConsultation3);

        ServiceConsultation serviceConsultation4 = new ServiceConsultation();
        serviceConsultation4.setConsultants(new ArrayList<>());
        serviceConsultation4.setDossierMedicals(new ArrayList<>());
        serviceConsultation4.setId(123L);
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation4.setLastUpdatedAt(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation4.setNomService("Nom Service");
        serviceConsultation4.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        serviceConsultation4.setPostedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        serviceConsultation4.setPriseRDVS(new ArrayList<>());
        serviceConsultation4.setSalleAttente(new SalleAttente());
        serviceConsultation4.setTypeService("Type Service");

        Consultant consultant3 = new Consultant();
        consultant3.setCalendrier(new RollingCalendar("2020-03-01"));
        consultant3.setConsultations(new ArrayList<>());
        consultant3.setDossierMedicals(new ArrayList<>());
        consultant3.setEmail("jane.doe@example.org");
        consultant3.setFonction("Fonction");
        consultant3.setId(123L);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant3.setLastUpdatedAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        consultant3.setNom("Nom");
        consultant3.setOrdonnances(new ArrayList<>());
        consultant3.setPassword("iloveyou");
        consultant3.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant3.setPostedAt(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        consultant3.setPrenom("Prenom");
        consultant3.setPriseRDVs(new ArrayList<>());
        consultant3.setRoles(new HashSet<>());
        consultant3.setServiceConsultation(serviceConsultation4);
        consultant3.setTelephone("4105551212");
        consultant3.setTitre("Titre");
        consultant3.setUsername("janedoe");

        Consultant consultant4 = new Consultant();
        consultant4.setCalendrier(null);
        consultant4.setConsultations(new ArrayList<>());
        consultant4.setDossierMedicals(new ArrayList<>());
        consultant4.setEmail("jane.doe@example.org");
        consultant4.setFonction("Fonction");
        consultant4.setId(123L);
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant4.setLastUpdatedAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        consultant4.setNom("Nom");
        consultant4.setOrdonnances(new ArrayList<>());
        consultant4.setPassword("iloveyou");
        consultant4.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult38 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultant4.setPostedAt(Date.from(atStartOfDayResult38.atZone(ZoneId.of("UTC")).toInstant()));
        consultant4.setPrenom("Prenom");
        consultant4.setPriseRDVs(new ArrayList<>());
        consultant4.setRoles(new HashSet<>());
        consultant4.setServiceConsultation(new ServiceConsultation());
        consultant4.setTelephone("4105551212");
        consultant4.setTitre("Titre");
        consultant4.setUsername("janedoe");

        DossierMedical dossierMedical1 = new DossierMedical();
        dossierMedical1.setAncienPatient(true);
        dossierMedical1.setConsultant(new Consultant());
        dossierMedical1.setConsultationEffectuee("Consultation Effectuee");
        dossierMedical1.setConsultations(new ArrayList<>());
        dossierMedical1.setId(123L);
        LocalDateTime atStartOfDayResult39 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dossierMedical1.setLastUpdatedAt(Date.from(atStartOfDayResult39.atZone(ZoneId.of("UTC")).toInstant()));
        dossierMedical1.setNomPatient("Nom Patient");
        dossierMedical1.setPatient(new Patient());
        LocalDateTime atStartOfDayResult40 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dossierMedical1.setPostedAt(Date.from(atStartOfDayResult40.atZone(ZoneId.of("UTC")).toInstant()));
        dossierMedical1.setPrenomPatient("Prenom Patient");
        dossierMedical1.setResultatPrestation("Resultat Prestation");
        dossierMedical1.setServiceConsultation(new ServiceConsultation());

        Ordonnance ordonnance = new Ordonnance();
        ordonnance.setConsultant(new Consultant());
        ordonnance.setConsultation(new Consultation());
        LocalDateTime atStartOfDayResult41 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance.setDateOrdonnance(Date.from(atStartOfDayResult41.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance.setExamens(new ArrayList<>());
        ordonnance.setId(123L);
        LocalDateTime atStartOfDayResult42 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance.setLastUpdatedAt(Date.from(atStartOfDayResult42.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance.setMedicaments(new ArrayList<>());
        ordonnance.setNatureOrdonnance("Nature Ordonnance");
        ordonnance.setNomPatient("Nom Patient");
        LocalDateTime atStartOfDayResult43 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance.setPostedAt(Date.from(atStartOfDayResult43.atZone(ZoneId.of("UTC")).toInstant()));

        Patient patient1 = new Patient();
        patient1.setAssurance("Assurance");
        patient1.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult44 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setDateNaissance(Date.from(atStartOfDayResult44.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setDetailsPatient(new DetailsPatient());
        patient1.setDomicile("Domicile");
        patient1.setDossierMedicals(new ArrayList<>());
        patient1.setEmail("jane.doe@example.org");
        patient1.setEstAssure(true);
        patient1.setGenre("Genre");
        patient1.setId(123L);
        LocalDateTime atStartOfDayResult45 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setLastUpdatedAt(Date.from(atStartOfDayResult45.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setNom("Nom");
        patient1.setNumIdendite(3);
        patient1.setPaiement(new Paiement());
        patient1.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult46 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient1.setPostedAt(Date.from(atStartOfDayResult46.atZone(ZoneId.of("UTC")).toInstant()));
        patient1.setPrenom("Prenom");
        patient1.setPriseRDVs(new ArrayList<>());
        patient1.setSalleAttente(new SalleAttente());
        patient1.setTauxAssurance(10.0d);

        Consultation consultation = new Consultation();
        consultation.setConsultant(consultant4);
        LocalDateTime atStartOfDayResult47 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation.setDateConsultation(Date.from(atStartOfDayResult47.atZone(ZoneId.of("UTC")).toInstant()));
        consultation.setDiagnostic("Diagnostic");
        consultation.setDossierMedical(dossierMedical1);
        consultation.setId(123L);
        LocalDateTime atStartOfDayResult48 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation.setLastUpdatedAt(Date.from(atStartOfDayResult48.atZone(ZoneId.of("UTC")).toInstant()));
        consultation.setOrdonnance(ordonnance);
        consultation.setPatient(patient1);
        LocalDateTime atStartOfDayResult49 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation.setPostedAt(Date.from(atStartOfDayResult49.atZone(ZoneId.of("UTC")).toInstant()));
        consultation.setTypeConsultation("Type Consultation");

        Ordonnance ordonnance1 = new Ordonnance();
        ordonnance1.setConsultant(consultant3);
        ordonnance1.setConsultation(consultation);
        LocalDateTime atStartOfDayResult50 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance1.setDateOrdonnance(Date.from(atStartOfDayResult50.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance1.setExamens(new ArrayList<>());
        ordonnance1.setId(123L);
        LocalDateTime atStartOfDayResult51 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance1.setLastUpdatedAt(Date.from(atStartOfDayResult51.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance1.setMedicaments(new ArrayList<>());
        ordonnance1.setNatureOrdonnance("Nature Ordonnance");
        ordonnance1.setNomPatient("Nom Patient");
        LocalDateTime atStartOfDayResult52 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance1.setPostedAt(Date.from(atStartOfDayResult52.atZone(ZoneId.of("UTC")).toInstant()));

        Patient patient2 = new Patient();
        patient2.setAssurance("Assurance");
        patient2.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult53 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setDateNaissance(Date.from(atStartOfDayResult53.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setDetailsPatient(new DetailsPatient());
        patient2.setDomicile("Domicile");
        patient2.setDossierMedicals(new ArrayList<>());
        patient2.setEmail("jane.doe@example.org");
        patient2.setEstAssure(true);
        patient2.setGenre("Genre");
        patient2.setId(123L);
        LocalDateTime atStartOfDayResult54 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setLastUpdatedAt(Date.from(atStartOfDayResult54.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setNom("Nom");
        patient2.setNumIdendite(3);
        patient2.setPaiement(new Paiement());
        patient2.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult55 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient2.setPostedAt(Date.from(atStartOfDayResult55.atZone(ZoneId.of("UTC")).toInstant()));
        patient2.setPrenom("Prenom");
        patient2.setPriseRDVs(new ArrayList<>());
        patient2.setSalleAttente(new SalleAttente());
        patient2.setTauxAssurance(10.0d);

        DetailsPatient detailsPatient1 = new DetailsPatient();
        detailsPatient1.setEtatDuPatient("Etat Du Patient");
        LocalDateTime atStartOfDayResult56 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setHeurePriseCharge(Date.from(atStartOfDayResult56.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setId(123L);
        LocalDateTime atStartOfDayResult57 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setLastUpdatedAt(Date.from(atStartOfDayResult57.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setPatient(patient2);
        LocalDateTime atStartOfDayResult58 = LocalDate.of(1970, 1, 1).atStartOfDay();
        detailsPatient1.setPostedAt(Date.from(atStartOfDayResult58.atZone(ZoneId.of("UTC")).toInstant()));
        detailsPatient1.setServiceAffectation("Service Affectation");
        detailsPatient1.setStatus("Status");

        Accueil accueil = new Accueil();
        accueil.setId(123L);
        LocalDateTime atStartOfDayResult59 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setLastUpdatedAt(Date.from(atStartOfDayResult59.atZone(ZoneId.of("UTC")).toInstant()));
        accueil.setNom("Nom");
        accueil.setNouveauPatient(true);
        accueil.setPaiements(new ArrayList<>());
        accueil.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult60 = LocalDate.of(1970, 1, 1).atStartOfDay();
        accueil.setPostedAt(Date.from(atStartOfDayResult60.atZone(ZoneId.of("UTC")).toInstant()));

        Assurance assurance = new Assurance();
        LocalDateTime atStartOfDayResult61 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setDatePrestation(Date.from(atStartOfDayResult61.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setId(123L);
        assurance.setIdentitePatient("Identite Patient");
        assurance.setMontantConvention(10.0d);
        assurance.setNomPatient("Nom Patient");
        assurance.setNumAffilation(10);
        assurance.setPaiements(new ArrayList<>());
        LocalDateTime atStartOfDayResult62 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPastUpdatedAt(Date.from(atStartOfDayResult62.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult63 = LocalDate.of(1970, 1, 1).atStartOfDay();
        assurance.setPostedAt(Date.from(atStartOfDayResult63.atZone(ZoneId.of("UTC")).toInstant()));
        assurance.setPrenomPatient("Prenom Patient");
        assurance.setTypePrestation("Type Prestation");

        Paiement paiement1 = new Paiement();
        paiement1.setAccueil(accueil);
        paiement1.setAssurance(assurance);
        paiement1.setCodeFacture(1);
        LocalDateTime atStartOfDayResult64 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setDatePaiement(Date.from(atStartOfDayResult64.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setFacturations(new ArrayList<>());
        paiement1.setId(123L);
        LocalDateTime atStartOfDayResult65 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setLastUpdatedAt(Date.from(atStartOfDayResult65.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setMontantPrestation(10.0d);
        paiement1.setNomAssurance("Nom Assurance");
        paiement1.setPatients(new ArrayList<>());
        paiement1.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult66 = LocalDate.of(1970, 1, 1).atStartOfDay();
        paiement1.setPostedAt(Date.from(atStartOfDayResult66.atZone(ZoneId.of("UTC")).toInstant()));
        paiement1.setPrestation("Prestation");
        paiement1.setTauxCouverture(10.0d);

        SalleAttente salleAttente4 = new SalleAttente();
        salleAttente4.setEtage("Etage");
        salleAttente4.setId(123L);
        LocalDateTime atStartOfDayResult67 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente4.setLastUpdatedAt(Date.from(atStartOfDayResult67.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente4.setNom("Nom");
        salleAttente4.setNumOrdre(10);
        salleAttente4.setPatients(new ArrayList<>());
        salleAttente4.setPersonnelMedicals(new ArrayList<>());
        LocalDateTime atStartOfDayResult68 = LocalDate.of(1970, 1, 1).atStartOfDay();
        salleAttente4.setPostedAt(Date.from(atStartOfDayResult68.atZone(ZoneId.of("UTC")).toInstant()));
        salleAttente4.setServiceConsultations(new ArrayList<>());

        Patient patient3 = new Patient();
        patient3.setAssurance("Assurance");
        patient3.setConsultations(new ArrayList<>());
        LocalDateTime atStartOfDayResult69 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient3.setDateNaissance(Date.from(atStartOfDayResult69.atZone(ZoneId.of("UTC")).toInstant()));
        patient3.setDetailsPatient(detailsPatient1);
        patient3.setDomicile("Domicile");
        patient3.setDossierMedicals(new ArrayList<>());
        patient3.setEmail("jane.doe@example.org");
        patient3.setEstAssure(true);
        patient3.setGenre("Genre");
        patient3.setId(123L);
        LocalDateTime atStartOfDayResult70 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient3.setLastUpdatedAt(Date.from(atStartOfDayResult70.atZone(ZoneId.of("UTC")).toInstant()));
        patient3.setNom("Nom");
        patient3.setNumIdendite(3);
        patient3.setPaiement(paiement1);
        patient3.setPhoto("alice.liddell@example.org");
        LocalDateTime atStartOfDayResult71 = LocalDate.of(1970, 1, 1).atStartOfDay();
        patient3.setPostedAt(Date.from(atStartOfDayResult71.atZone(ZoneId.of("UTC")).toInstant()));
        patient3.setPrenom("Prenom");
        patient3.setPriseRDVs(new ArrayList<>());
        patient3.setSalleAttente(salleAttente4);
        patient3.setTauxAssurance(10.0d);

        Consultation consultation1 = new Consultation();
        consultation1.setConsultant(consultant1);
        LocalDateTime atStartOfDayResult72 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation1.setDateConsultation(Date.from(atStartOfDayResult72.atZone(ZoneId.of("UTC")).toInstant()));
        consultation1.setDiagnostic("Diagnostic");
        consultation1.setDossierMedical(dossierMedical);
        consultation1.setId(123L);
        LocalDateTime atStartOfDayResult73 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation1.setLastUpdatedAt(Date.from(atStartOfDayResult73.atZone(ZoneId.of("UTC")).toInstant()));
        consultation1.setOrdonnance(ordonnance1);
        consultation1.setPatient(patient3);
        LocalDateTime atStartOfDayResult74 = LocalDate.of(1970, 1, 1).atStartOfDay();
        consultation1.setPostedAt(Date.from(atStartOfDayResult74.atZone(ZoneId.of("UTC")).toInstant()));
        consultation1.setTypeConsultation("Type Consultation");

        Ordonnance ordonnance2 = new Ordonnance();
        ordonnance2.setConsultant(consultant);
        ordonnance2.setConsultation(consultation1);
        LocalDateTime atStartOfDayResult75 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance2.setDateOrdonnance(Date.from(atStartOfDayResult75.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance2.setExamens(new ArrayList<>());
        ordonnance2.setId(123L);
        LocalDateTime atStartOfDayResult76 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance2.setLastUpdatedAt(Date.from(atStartOfDayResult76.atZone(ZoneId.of("UTC")).toInstant()));
        ordonnance2.setMedicaments(new ArrayList<>());
        ordonnance2.setNatureOrdonnance("Nature Ordonnance");
        ordonnance2.setNomPatient("Nom Patient");
        LocalDateTime atStartOfDayResult77 = LocalDate.of(1970, 1, 1).atStartOfDay();
        ordonnance2.setPostedAt(Date.from(atStartOfDayResult77.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.ordonnanceService.getOrdonnance((Long) any())).thenReturn(ordonnance2);

        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult78 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult78.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult79 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult79.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);
        MedicamentResponseDto actualRemoveOrdonnanceFromMedicamentResult = this.medicamentServiceImpl
                .removeOrdonnanceFromMedicament(123L);
        assertEquals("Duree De Prise", actualRemoveOrdonnanceFromMedicamentResult.getDureeDePrise());
        assertEquals(consultationList, actualRemoveOrdonnanceFromMedicamentResult.getOrdonnances());
        assertEquals("Nom Medicament", actualRemoveOrdonnanceFromMedicamentResult.getNomMedicament());
        assertEquals(123L, actualRemoveOrdonnanceFromMedicamentResult.getId().longValue());
        verify(this.ordonnanceService).getOrdonnance((Long) any());
        verify(this.medicamentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link MedicamentServiceImpl#removeOrdonnanceFromMedicament(Long)}
     */
    @Test
    void testRemoveOrdonnanceFromMedicament2() {
        when(this.ordonnanceService.getOrdonnance((Long) any())).thenThrow(new IllegalArgumentException("foo"));

        Medicament medicament = new Medicament();
        medicament.setDureeDePrise("Duree De Prise");
        medicament.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setLastUpdatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        medicament.setNomMedicament("Nom Medicament");
        medicament.setOrdonnances(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        medicament.setPostedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Medicament> ofResult = Optional.of(medicament);
        when(this.medicamentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> this.medicamentServiceImpl.removeOrdonnanceFromMedicament(123L));
        verify(this.ordonnanceService).getOrdonnance((Long) any());
        verify(this.medicamentRepository).findById((Long) any());
    }
}

