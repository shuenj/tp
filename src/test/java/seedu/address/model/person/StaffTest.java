package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHIFTDAYS_AMY;
import static seedu.address.testutil.TypicalStaff.ALICE;
import static seedu.address.testutil.TypicalStaff.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.affiliation.Affiliation;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.TypicalPersons;

public class StaffTest {

    @Test
    public void asObservableList_modifyList_success() {
        Staff staff = new StaffBuilder().build();
        staff.getAffiliations().add(new Affiliation(VALID_AFFILIATION_AMY));
        assertTrue(staff.getAffiliations().contains(new Affiliation(VALID_AFFILIATION_AMY)));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Staff editedAlice = new StaffBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAffiliations(VALID_AFFILIATION_AMY)
                .withAffiliationHistory(VALID_AFFILIATION_AMY).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StaffBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Staff editedBob = new StaffBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StaffBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Staff aliceCopy = new StaffBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different staff -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Staff editedAlice = new StaffBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StaffBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StaffBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different affiliations -> returns false
        editedAlice = new StaffBuilder(ALICE).withAffiliations(VALID_AFFILIATION_AMY)
        .withAffiliationHistory(VALID_AFFILIATION_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different shift days -> return false
        editedAlice = new StaffBuilder(ALICE).withShiftDays(VALID_SHIFTDAYS_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        //different class instance -> return false
        assertFalse(TypicalPersons.ALICE.hashCode() == aliceCopy.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Staff.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", role=" + ALICE.getRole() + ", affiliations="
                + ALICE.getAffiliations() + ", affiliationHistory=" + ALICE.getAffiliationHistory()
                + ", shiftDays=" + ALICE.getShiftDays() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
