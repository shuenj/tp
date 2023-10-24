package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Nurse objects.
 */
public class NurseBuilder {

    public static final String DEFAULT_NAME = "Jennifer Chue";
    public static final String DEFAULT_PHONE = "85552222";
    public static final String DEFAULT_EMAIL = "jenchue@facemail.com";

    private Name name;
    private Phone phone;
    private Email email;

    private Set<Affiliation> affiliations;
    private Set<Affiliation> affiliationHistory;
    private ShiftDays shiftDays;

    /**
     * Creates a {@code NurseBuilder} with the default details.
     */
    public NurseBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        affiliations = new HashSet<>();
        affiliationHistory = new HashSet<>();
        shiftDays = new ShiftDays();
    }

    /**
     * Initializes the NurseBuilder with the data of {@code personToCopy}.
     */
    public NurseBuilder(Nurse nurseToCopy) {
        name = nurseToCopy.getName();
        phone = nurseToCopy.getPhone();
        email = nurseToCopy.getEmail();
        affiliations = new HashSet<>(nurseToCopy.getAffiliations());
        affiliationHistory = new HashSet<>(nurseToCopy.getAffiliationHistory());
        shiftDays = nurseToCopy.getShiftDays();
    }

    /**
     * Sets the {@code Name} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code affiliations} into a {@code Set<Affiliation>} and set it to the {@code Nurse} that
     * we are building.
     */
    public NurseBuilder withAffiliations(String ... affiliations) {
        this.affiliations = SampleDataUtil.getAffiliationSet(affiliations);
        return this;
    }

    /**
     * Parses the {@code affiliationHistory} into a {@code Set<Affiliation>} and set it to the {@code Nurse} that
     * we are building.
     */
    public NurseBuilder withAffiliationHistory(String ... affiliationHistory) {
        this.affiliationHistory = SampleDataUtil.getAffiliationSet(affiliationHistory);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code ShiftDays} of the {@code Nurse} that we are building.
     */
    public NurseBuilder withShiftDays(Set<Integer> shiftDays) {
        this.shiftDays = new ShiftDays(shiftDays);
        return this;
    }

    public Nurse build() {
        return (Nurse) new Nurse(name, phone, email, affiliations, affiliationHistory).setShiftDays(shiftDays);
    }
}
