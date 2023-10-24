package seedu.address.model.affiliation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

public class AffiliationModifierTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void addAffiliation_validParam_success() {
        Person doctorA = new DoctorBuilder().withName("Alice").build();
        Person patientB = new PatientBuilder().withName("Bob").withAffiliations("Alice")
            .withAffiliationHistory("Alice").build();
        model.addPerson(doctorA);
        model.addPerson(patientB);

        AffiliationModifier.addAffiliations(patientB.getAffiliations(), patientB, model);
        assertTrue(doctorA.getAffiliations().contains(new Affiliation(patientB.getName().fullName)));
    }

    @Test
    public void addAffiliationHistory_validParam_success() {
        Person doctorAlice = new DoctorBuilder().withName("Alice").build();
        Person doctorCharlie = new DoctorBuilder().withName("Charlie").build();
        Person patientBob = new PatientBuilder().withName("Bob")
            .withAffiliations("Alice", "Charlie")
            .withAffiliationHistory("Alice", "Charlie").build();
        model.addPerson(doctorAlice);
        model.addPerson(doctorCharlie);
        model.addPerson(patientBob);
        Set<Affiliation> affiliationSet = new HashSet<>(Arrays.asList(new Affiliation("Alice"),
            new Affiliation("Charlie")));
        AffiliationModifier.addAffiliationHistory(affiliationSet, patientBob, model);
        assertTrue(doctorAlice.getAffiliationHistory().contains(new Affiliation(patientBob.getName().fullName)));
        assertTrue(doctorCharlie.getAffiliationHistory().contains(new Affiliation(patientBob.getName().fullName)));
    }

    @Test
    public void removeAffiliation_validParam_success() {
        Person doctorA = new DoctorBuilder().withName("Alice").withAffiliations("Bob")
            .withAffiliationHistory("Bob").build();
        Person patientB = new PatientBuilder().withName("Bob").withAffiliations("Alice")
            .withAffiliationHistory("Alice").build();
        model.addPerson(doctorA);
        model.addPerson(patientB);

        AffiliationModifier.removeAffiliations(patientB.getAffiliations(), patientB, model);
        assertFalse(doctorA.getAffiliations().contains(new Affiliation(patientB.getName().fullName)));
    }

    @Test
    public void removeAffiliationHistory_validParam_success() {
        Person doctorAlice = new DoctorBuilder().withName("Alice")
            .withAffiliationHistory("Bob", "Thomas").build();
        Person patientBob = new PatientBuilder().withName("Bob")
            .withAffiliations("Alice")
            .withAffiliationHistory("Alice").build();
        model.addPerson(doctorAlice);
        model.addPerson(patientBob);

        AffiliationModifier.removeAffiliationHistory(patientBob.getAffiliationHistory(), patientBob, model);
        assertFalse(doctorAlice.getAffiliationHistory().contains(new Affiliation(patientBob.getName().fullName)));
    }

    @Test
    public void nameChangeAffiliation_validParam_success() {
        Person doctorA = new DoctorBuilder().withName("Alice").withAffiliations("Bob")
            .withAffiliationHistory("Bob").build();
        Person patientB = new PatientBuilder().withName("Bob").withAffiliations("Alice")
            .withAffiliationHistory("Alice").build();
        model.addPerson(doctorA);
        model.addPerson(patientB);
        Name newName = new Name("Ben");

        AffiliationModifier.nameChangeAffiliations(patientB.getAffiliations(),
                patientB.getName(), newName, model);
        assertTrue(doctorA.getAffiliations().contains(new Affiliation(newName.fullName)));
    }

    @Test
    public void nameChangeAffiliationHistory_validParam_success() {
        Person doctorAlice = new DoctorBuilder().withName("Alice")
            .withAffiliationHistory("Bob", "Thomas").build();
        Person doctorCharlie = new DoctorBuilder().withName("Charlie")
            .withAffiliationHistory("Bob").build();
        Doctor doctorDonald = new DoctorBuilder().withName("Donald").build();
        Person patientBob = new PatientBuilder().withName("Bob")
            .withAffiliations("Alice", "Charlie")
            .withAffiliationHistory("Alice",
                "Charlie", "Donald").build();
        model.addPerson(doctorAlice);
        model.addPerson(doctorCharlie);
        model.addPerson(patientBob);
        model.addPerson(doctorDonald);
        Name newName = new Name("Ben");
        AffiliationModifier.nameChangeAffiliationHistory(patientBob.getAffiliationHistory(), patientBob.getName(),
                newName, model);
        assertTrue(doctorAlice.getAffiliationHistory().contains(new Affiliation(newName.fullName)));
        assertTrue(doctorCharlie.getAffiliationHistory().contains(new Affiliation(newName.fullName)));
    }
}
