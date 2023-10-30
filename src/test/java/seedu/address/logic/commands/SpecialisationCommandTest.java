package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalDoctors.getTypicalDoctorAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialisation;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.PatientBuilder;

public class SpecialisationCommandTest {

    private Model model = new ModelManager(getTypicalDoctorAddressBook(), new UserPrefs());

    @Test
    public void execute_roleIsDoctor_success() throws CommandException {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        HashSet<String> typicalSpecialisations = new HashSet<>();
        typicalSpecialisations.add("Heart");
        typicalSpecialisations.add("Kidney");
        typicalSpecialisations.add("Bones");

        HashSet<Specialisation> typicalSpecialisationsSet = typicalSpecialisations.stream()
                .map(Specialisation::new)
                .collect(java.util.stream.Collectors.toCollection(HashSet::new));
        DoctorBuilder personInList = new DoctorBuilder((Doctor) lastPerson);
        Doctor specialisationsChangedPerson = personInList.withSpecialisations(typicalSpecialisations).build();

        SpecialisationCommand specialisationCommand = new SpecialisationCommand(indexLastPerson,
                typicalSpecialisationsSet);
        String expectedMessage = SpecialisationCommand.MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, specialisationsChangedPerson);
        assertCommandSuccess(specialisationCommand, model, expectedMessage, expectedModel);
        SpecialisationCommand unmodifySpecialisationCommand = new SpecialisationCommand(
                indexLastPerson, new HashSet<>());
        unmodifySpecialisationCommand.execute(model);
    }

    @Test
    public void execute_roleIsPatient_failure() {
        Patient patient = new PatientBuilder().build();

        HashSet<String> typicalSpecialisations = new HashSet<>();
        typicalSpecialisations.add("Heart");
        typicalSpecialisations.add("Kidney");
        typicalSpecialisations.add("Bones");

        HashSet<Specialisation> typicalSpecialisationsSet = new HashSet<>();
        typicalSpecialisationsSet.add(new Specialisation("Heart"));
        typicalSpecialisationsSet.add(new Specialisation("Kidney"));
        typicalSpecialisationsSet.add(new Specialisation("Bones"));

        SpecialisationCommand specialisationCommand = new SpecialisationCommand(INDEX_FIRST_PERSON,
                typicalSpecialisationsSet);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Index indexFirstPerson = Index.fromOneBased(1);
        Person firstPerson = expectedModel.getFilteredPersonList().get(indexFirstPerson.getZeroBased());
        expectedModel.setPerson(firstPerson, patient);

        assertCommandFailure(specialisationCommand, expectedModel, Messages.MESSAGE_INVALID_ROLE);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        HashSet<String> typicalSpecialisations = new HashSet<>();
        typicalSpecialisations.add("Heart");
        typicalSpecialisations.add("Kidney");
        typicalSpecialisations.add("Bones");

        HashSet<Specialisation> typicalSpecialisationsSet = typicalSpecialisations.stream()
                .map(Specialisation::new)
                .collect(java.util.stream.Collectors.toCollection(HashSet::new));
        SpecialisationCommand specialisationCommand = new SpecialisationCommand(
                outOfBoundIndex, typicalSpecialisationsSet);
        assertCommandFailure(specialisationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of contact list
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        HashSet<String> typicalSpecialisations = new HashSet<>();
        typicalSpecialisations.add("Heart");
        typicalSpecialisations.add("Kidney");
        typicalSpecialisations.add("Bones");

        HashSet<Specialisation> typicalSpecialisationsSet = typicalSpecialisations.stream()
                .map(Specialisation::new)
                .collect(java.util.stream.Collectors.toCollection(HashSet::new));
        SpecialisationCommand specialisationCommand = new SpecialisationCommand(outOfBoundIndex,
                typicalSpecialisationsSet);

        assertCommandFailure(specialisationCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        HashSet<String> typicalSpecialisations = new HashSet<>();
        typicalSpecialisations.add("Heart");
        typicalSpecialisations.add("Kidney");
        typicalSpecialisations.add("Bones");

        HashSet<Specialisation> typicalSpecialisationsSet = new HashSet<>();
        typicalSpecialisationsSet.add(new Specialisation("Heart"));
        typicalSpecialisationsSet.add(new Specialisation("Kidney"));
        typicalSpecialisationsSet.add(new Specialisation("Bones"));

        final SpecialisationCommand standardCommand = new SpecialisationCommand(
                Index.fromOneBased(1), typicalSpecialisationsSet);

        // same values -> returns true
        HashSet<String> copyTypicalSpecialisations = new HashSet<>();
        copyTypicalSpecialisations.add("Heart");
        copyTypicalSpecialisations.add("Kidney");
        copyTypicalSpecialisations.add("Bones");

        HashSet<Specialisation> copyTypicalSpecialisationsSet = new HashSet<>();
        copyTypicalSpecialisationsSet.add(new Specialisation("Heart"));
        copyTypicalSpecialisationsSet.add(new Specialisation("Kidney"));
        copyTypicalSpecialisationsSet.add(new Specialisation("Bones"));

        SpecialisationCommand commandWithSameValues = new SpecialisationCommand(Index.fromOneBased(1),
                copyTypicalSpecialisationsSet);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new SpecialisationCommand(Index.fromOneBased(2),
                typicalSpecialisationsSet)));

        // different specialisations -> returns false
        HashSet<String> differentSpecialisations = new HashSet<>();
        differentSpecialisations.add("Heart");
        differentSpecialisations.add("Kidney");
        differentSpecialisations.add("Bones");
        differentSpecialisations.add("Lungs");

        HashSet<Specialisation> differentSpecialisationsSet = new HashSet<>();
        differentSpecialisationsSet.add(new Specialisation("Heart"));
        differentSpecialisationsSet.add(new Specialisation("Kidney"));
        differentSpecialisationsSet.add(new Specialisation("Bones"));
        differentSpecialisationsSet.add(new Specialisation("Lungs"));

        assertFalse(standardCommand.equals(new SpecialisationCommand(Index.fromOneBased(1),
                differentSpecialisationsSet)));

    }
}
