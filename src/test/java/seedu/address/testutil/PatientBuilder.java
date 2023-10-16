package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Patricia Tan";
    public static final String DEFAULT_PHONE = "85526662";
    public static final String DEFAULT_EMAIL = "pattan@facemail.com";

    private Name name;
    private Phone phone;
    private Email email;
    private Set<Affiliation> affiliations;
    private Set<Affiliation> affiliationHistory;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        affiliations = new HashSet<>();
        affiliationHistory = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code personToCopy}.
     */
    public PatientBuilder(Patient doctorToCopy) {
        name = doctorToCopy.getName();
        phone = doctorToCopy.getPhone();
        email = doctorToCopy.getEmail();
        affiliations = new HashSet<>(doctorToCopy.getAffiliations());
        affiliationHistory = new HashSet<>(doctorToCopy.getAffiliationHistory());
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code affiliations} into a {@code Set<Affiliation>} and set it to the {@code Patient} that
     * we are building.
     */
    public PatientBuilder withAffiliations(String ... affiliations) {
        this.affiliations = SampleDataUtil.getAffiliationSet(affiliations);
        return this;
    }

    /**
     * Parses the {@code affiliationHistory} into a {@code Set<Affiliation>} and set it to the {@code Patient} that
     * we are building.
     */
    public PatientBuilder withAffiliationHistory(String ... affiliationHistory) {
        this.affiliationHistory = SampleDataUtil.getAffiliationSet(affiliationHistory);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, email, affiliations, affiliationHistory);
    }

}
