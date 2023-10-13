package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AFFILIATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withRole("Doctor").withEmail("alice@example.com")
            .withPhone("94351253")
            .withAffiliations("Benson Meier").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withRole("Patient")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withAffiliations("Alice Pauline", "Carl Kurz").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withRole("Doctor").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withRole("Patient").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withRole("Doctor").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withRole("Patient").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withRole("Doctor").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withRole("Patient").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withRole("Doctor").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withRole(VALID_ROLE_AMY).withAffiliations(VALID_AFFILIATION_BOB).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withRole(VALID_ROLE_BOB)
            .withAffiliations(VALID_AFFILIATION_AMY, VALID_AFFILIATION_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withRole("Doctor").withEmail("alice@example.com")
                .withPhone("94351253")
                .withAffiliations("Benson Meier").build();
        Person benson = new PersonBuilder().withName("Benson Meier")
                .withRole("Patient")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withAffiliations("Alice Pauline", "Carl Kurz").build();
        Person carl = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
                .withEmail("heinz@example.com").withRole("Doctor").build();
        Person daniel = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
                .withEmail("cornelia@example.com").withRole("Patient").build();
        Person elle = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
                .withEmail("werner@example.com").withRole("Doctor").build();
        Person fiona = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
                .withEmail("lydia@example.com").withRole("Patient").build();
        Person george = new PersonBuilder().withName("George Best").withPhone("9482442")
                .withEmail("anna@example.com").withRole("Doctor").build();
        return new ArrayList<>(Arrays.asList(alice, benson, carl, daniel, elle, fiona, george));
    }
}
