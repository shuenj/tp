package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Doctor;

/**
 * A utility class containing a list of {@code Doctor} objects to be used in tests.
 */
public class TypicalDoctors {

    public static final Doctor ALICE = new DoctorBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withAffiliations("Benson Meier")
            .withAffiliationHistory("Thomas Mink", "Benson Meier").build();
    public static final Doctor BENSON = new DoctorBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withAffiliationHistory("Alice Menti", "Bonas Kurz").build();
    public static final Doctor CARL = new DoctorBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Doctor DANIEL = new DoctorBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").build();
    public static final Doctor ELLE = new DoctorBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Doctor FIONA = new DoctorBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Doctor GEORGE = new DoctorBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Doctor HOON = new DoctorBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Doctor IDA = new DoctorBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Doctor's details found in {@code CommandTestUtil}
    public static final Doctor AMY = new DoctorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAffiliations(VALID_AFFILIATION_BOB)
            .withAffiliationHistory(VALID_AFFILIATION_BOB).build();
    public static final Doctor BOB = new DoctorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAffiliations(VALID_AFFILIATION_AMY, VALID_AFFILIATION_BOB)
            .withAffiliationHistory(VALID_AFFILIATION_AMY, VALID_AFFILIATION_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalDoctors() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Doctors.
     */
    public static AddressBook getTypicalDoctorAddressBook() {
        AddressBook ab = new AddressBook();
        for (Doctor doctor : getTypicalDoctor()) {
            ab.addPerson(doctor);
        }
        return ab;
    }

    public static List<Doctor> getTypicalDoctor() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

