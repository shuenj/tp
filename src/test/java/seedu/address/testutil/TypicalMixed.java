package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Persons} objects to be used in tests.
 */
public class TypicalMixed {

    public static final Doctor ALEX = new DoctorBuilder().withName("Alex Yeoh").withPhone("87438807")
            .withEmail("alexyeoh@example.com")
            .withAffiliations("Bernice Yu", "Charlotte Oliveiro")
            .withAffiliationHistory("Bernice Yu", "Charlotte Oliveiro")
            .withSpecialisations(new HashSet<>(Arrays.asList("ENT", "Radiologist")))
            .withShiftDays(new HashSet<>(Arrays.asList(1, 3, 6))).build();

    public static final Patient BERNICE = new PatientBuilder().withName("Bernice Yu").withPhone("99272758")
            .withEmail("berniceyu@example.com").withAffiliations("Alex Yeoh")
            .withAffiliationHistory("Alex Yeoh").build();

    public static final Nurse MAY = new NurseBuilder().withName("May Ho").withPhone("94437233")
            .withEmail("homimay@example.com").withAffiliations("Irfan Ibrahim")
            .withAffiliationHistory("Irfan Ibrahim")
            .withShiftDays(new HashSet<>(Arrays.asList(3, 4, 5))).build();

    public static final Patient CHARLOTTE = new PatientBuilder().withName("Charlotte Oliveiro").withPhone("93210283")
            .withEmail("charlotte@example.com")
            .withAffiliations("Alex Yeoh", "Evelyn Ng")
            .withAffiliationHistory("Alex Yeoh", "Evelyn Ng").build();

    public static final Doctor DAVID = new DoctorBuilder().withName("David Li").withPhone("91031282")
            .withEmail("lidavid@example.com")
            .withAffiliationHistory("Irfan Ibrahim")
            .withSpecialisations(new HashSet<>(List.of("Cardiologist")))
            .withShiftDays(new HashSet<>(Arrays.asList(2, 5, 7))).build();

    public static final Patient IRFAN = new PatientBuilder().withName("Irfan Ibrahim").withPhone("92492021")
            .withEmail("irfan@example.com")
            .withAffiliations("May Ho")
            .withAffiliationHistory("May Ho").build();

    public static final Nurse EVELYN = new NurseBuilder().withName("Evelyn Ng").withPhone("92624417")
            .withEmail("eveng@example.com").withAffiliations("Charlotte Oliveiro")
            .withAffiliationHistory("Charlotte Oliveiro")
            .withShiftDays(new HashSet<>(Arrays.asList(1, 2, 4, 6))).build();

    // Manually added
    public static final Doctor HOON = new DoctorBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Doctor IDA = new DoctorBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    private TypicalMixed() {} // prevents instantiation

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
        return new ArrayList<>(Arrays.asList(ALEX, BERNICE, MAY, CHARLOTTE, DAVID, IRFAN, EVELYN));
    }
}

