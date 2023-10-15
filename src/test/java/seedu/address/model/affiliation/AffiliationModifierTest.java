package seedu.address.model.affiliation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
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
}
