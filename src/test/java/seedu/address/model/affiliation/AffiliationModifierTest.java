package seedu.address.model.affiliation;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class AffiliationModifierTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void addAffiliation_validParam_success() {
        Person PatientB = model.getFilteredPersonList().get(1); // B Affn to A and C
        Person DoctorC = model.getFilteredPersonList().get(2); // C Affn to None

        AffiliationModifier.addAffiliations(PatientB.getAffiliations(), PatientB, model);
        assertTrue(DoctorC.getAffiliations().contains(new Affiliation(PatientB.getName().fullName)));
    }

    @Test
    public void removeAffiliation_validParam_success() {
        Person DoctorA = model.getFilteredPersonList().get(0); // A Affn to B
        Person PatientB = model.getFilteredPersonList().get(1); // B Affn to A and C

        AffiliationModifier.removeAffiliations(PatientB.getAffiliations(), PatientB, model);
        assertFalse(DoctorA.getAffiliations().contains(new Affiliation(PatientB.getName().fullName)));
    }

    @Test
    public void nameChangeAffiliation_validParam_success() {
        Person DoctorA = model.getFilteredPersonList().get(0); // A Affn to B
        Person PatientB = model.getFilteredPersonList().get(1); // B Affn to A and C
        Name newName = new Name("Ben");

        AffiliationModifier.nameChangeAffiliations(PatientB.getAffiliations(),
                PatientB.getName(), newName, model);
        System.out.println(PatientB);
        assertTrue(DoctorA.getAffiliations().contains(new Affiliation(newName.fullName)));
    }
}
