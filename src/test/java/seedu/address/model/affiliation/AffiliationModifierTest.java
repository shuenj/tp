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
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.StaffBuilder;

public class AffiliationModifierTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void addAffiliation_validParam_success() {
        Person staffA = new StaffBuilder().withName("Alice").build();
        Person patientB = new PatientBuilder().withName("Bob").withAffiliations("Alice")
            .withAffiliationHistory("Alice").build();
        model.addPerson(staffA);
        model.addPerson(patientB);

        AffiliationModifier.addAffiliations(patientB.getAffiliations(), patientB, model);
        assertTrue(staffA.getAffiliations().contains(new Affiliation(patientB.getName().fullName)));
    }

    @Test
    public void addAffiliationHistory_validParam_success() {
        Person staffAlice = new StaffBuilder().withName("Alice").build();
        Person staffCharlie = new StaffBuilder().withName("Charlie").build();
        Person patientBob = new PatientBuilder().withName("Bob")
            .withAffiliations("Alice", "Charlie")
            .withAffiliationHistory("Alice", "Charlie").build();
        model.addPerson(staffAlice);
        model.addPerson(staffCharlie);
        model.addPerson(patientBob);
        Set<Affiliation> affiliationSet = new HashSet<>(Arrays.asList(new Affiliation("Alice"),
            new Affiliation("Charlie")));
        AffiliationModifier.addAffiliationHistory(affiliationSet, patientBob, model);
        assertTrue(staffAlice.getAffiliationHistory().contains(new Affiliation(patientBob.getName().fullName)));
        assertTrue(staffCharlie.getAffiliationHistory().contains(new Affiliation(patientBob.getName().fullName)));
    }

    @Test
    public void removeAffiliation_validParam_success() {
        Person staffA = new StaffBuilder().withName("Alice").withAffiliations("Bob")
            .withAffiliationHistory("Bob").build();
        Person patientB = new PatientBuilder().withName("Bob").withAffiliations("Alice")
            .withAffiliationHistory("Alice").build();
        model.addPerson(staffA);
        model.addPerson(patientB);

        AffiliationModifier.removeAffiliations(patientB.getAffiliations(), patientB, model);
        assertFalse(staffA.getAffiliations().contains(new Affiliation(patientB.getName().fullName)));
    }

    @Test
    public void nameChangeAffiliation_validParam_success() {
        Person staffA = new StaffBuilder().withName("Alice").withAffiliations("Bob")
            .withAffiliationHistory("Bob").build();
        Person patientB = new PatientBuilder().withName("Bob").withAffiliations("Alice")
            .withAffiliationHistory("Alice").build();
        model.addPerson(staffA);
        model.addPerson(patientB);
        Name newName = new Name("Ben");

        AffiliationModifier.nameChangeAffiliations(patientB.getAffiliations(),
                patientB.getName(), newName, model);
        assertTrue(staffA.getAffiliations().contains(new Affiliation(newName.fullName)));
    }

    @Test
    public void nameChangeAffiliationHistory_validParam_success() {
        Person staffAlice = new StaffBuilder().withName("Alice")
            .withAffiliationHistory("Bob", "Thomas").build();
        Person staffCharlie = new StaffBuilder().withName("Charlie")
            .withAffiliationHistory("Bob").build();
        Staff staffDonald = new StaffBuilder().withName("Donald").build();
        Person patientBob = new PatientBuilder().withName("Bob")
            .withAffiliations("Alice", "Charlie")
            .withAffiliationHistory("Alice",
                "Charlie", "Donald").build();
        model.addPerson(staffAlice);
        model.addPerson(staffCharlie);
        model.addPerson(patientBob);
        model.addPerson(staffDonald);
        Name newName = new Name("Ben");
        AffiliationModifier.nameChangeAffiliationHistory(patientBob.getAffiliationHistory(), patientBob.getName(),
                newName, model);
        assertTrue(staffAlice.getAffiliationHistory().contains(new Affiliation(newName.fullName)));
        assertTrue(staffCharlie.getAffiliationHistory().contains(new Affiliation(newName.fullName)));
    }
}
