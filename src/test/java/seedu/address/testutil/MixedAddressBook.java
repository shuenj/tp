package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Address Book that contains all roles.
 */
public class MixedAddressBook {
    public static final Doctor ALICE = new DoctorBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withAffiliations("Benson Meier")
            .withAffiliationHistory("Thomas Mink", "Benson Meier").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withAffiliationHistory("Alice Menti", "Bonas Kurz").build();
    public static final Doctor CARL = new DoctorBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").build();
    public static final Nurse ELLE = new NurseBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Doctor GEORGE = new DoctorBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Doctor HOON = new DoctorBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    private MixedAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Doctors.
     */
    public static AddressBook getTypicalMixedAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalMixed()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalMixed() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
