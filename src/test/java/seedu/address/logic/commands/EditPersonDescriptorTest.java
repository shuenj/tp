package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different role -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withRole(VALID_ROLE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different affiliations -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAffiliations(VALID_AFFILIATION_AMY)
        .withAffiliationHistory(VALID_AFFILIATION_AMY).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void setAffiliationHistory_validAffiliationHistory_setsHistoryCorrectly() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        Affiliation affiliation1 = new Affiliation("Affiliation1");
        Affiliation affiliation2 = new Affiliation("Affiliation2");
        Set<Affiliation> affiliations = new HashSet<>();
        affiliations.add(affiliation1);
        affiliations.add(affiliation2);
        descriptor.setAffiliationHistory(affiliations);

        assertTrue(descriptor.getAffiliationHistory().isPresent());
        assertEquals(affiliations, descriptor.getAffiliationHistory().get());

        Affiliation affiliation3 = new Affiliation("Affiliation3");
        affiliations.add(affiliation3);
        descriptor.setAffiliationHistory(affiliations, affiliations);
        assertTrue(descriptor.getAffiliationHistory().isPresent());
        assertEquals(affiliations, descriptor.getAffiliationHistory().get());
    }

    @Test
    public void addAffiliationsToHistory_validAffiliations_addsToHistoryCorrectly() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        Affiliation affiliation1 = new Affiliation("Affiliation1");
        Affiliation affiliation2 = new Affiliation("Affiliation2");
        Set<Affiliation> initialHistory = new HashSet<>();
        initialHistory.add(affiliation1);
        initialHistory.add(affiliation2);
        descriptor.setAffiliationHistory(initialHistory);
        Affiliation affiliation3 = new Affiliation("Affiliation3");
        Affiliation affiliation4 = new Affiliation("Affiliation4");
        Set<Affiliation> newAffiliations = new HashSet<>();
        newAffiliations.add(affiliation3);
        newAffiliations.add(affiliation4);
        descriptor.addAffiliationsToHistory(newAffiliations);
        assertTrue(descriptor.getAffiliationHistory().isPresent());
        Set<Affiliation> expectedHistory = new HashSet<>(initialHistory);
        expectedHistory.addAll(newAffiliations);
        assertEquals(expectedHistory, descriptor.getAffiliationHistory().get());
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", phone="
                + editPersonDescriptor.getPhone().orElse(null) + ", email="
                + editPersonDescriptor.getEmail().orElse(null) + ", role="
                + editPersonDescriptor.getRole().orElse(null) + ", affiliations="
                + editPersonDescriptor.getAffiliations().orElse(null) + ", affiliationHistory="
                + editPersonDescriptor.getAffiliationHistory().orElse(null) + ", shiftDays="
                + editPersonDescriptor.getShiftDays().orElse(null) + ", specialisations="
                + editPersonDescriptor.getSpecialisation().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
