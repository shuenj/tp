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
import seedu.address.model.person.Nurse;

/**
 * A utility class containing a list of {@code Nurse} objects to be used in tests.
 */
public class TypicalNurses {

    public static final Nurse ALICE = new NurseBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withAffiliations("Benson Meier")
            .withAffiliationHistory("Thomas Mink", "Benson Meier").build();
    public static final Nurse BENSON = new NurseBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withAffiliationHistory("Alice Menti", "Bonas Kurz").build();
    public static final Nurse CARL = new NurseBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Nurse DANIEL = new NurseBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").build();
    public static final Nurse ELLE = new NurseBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Nurse FIONA = new NurseBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Nurse GEORGE = new NurseBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Nurse HOON = new NurseBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Nurse IDA = new NurseBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Nurse's details found in {@code CommandTestUtil}
    public static final Nurse AMY = new NurseBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAffiliations(VALID_AFFILIATION_BOB)
            .withAffiliationHistory(VALID_AFFILIATION_BOB).build();
    public static final Nurse BOB = new NurseBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAffiliations(VALID_AFFILIATION_AMY, VALID_AFFILIATION_BOB)
            .withAffiliationHistory(VALID_AFFILIATION_AMY, VALID_AFFILIATION_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalNurses() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Nurses.
     */
    public static AddressBook getTypicalNurseAddressBook() {
        AddressBook ab = new AddressBook();
        for (Nurse nurse : getTypicalNurse()) {
            ab.addPerson(nurse);
        }
        return ab;
    }

    public static List<Nurse> getTypicalNurse() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}

