package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.person.Specialisation;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Doctor objects.
 */
public class DoctorBuilder extends StaffBuilder {

    public static final String DEFAULT_NAME = "Jennifer Chue";
    public static final String DEFAULT_PHONE = "85552222";
    public static final String DEFAULT_EMAIL = "jenchue@facemail.com";

    private Name name;
    private Phone phone;
    private Email email;

    private Set<Affiliation> affiliations;
    private Set<Affiliation> affiliationHistory;
    private ShiftDays shiftDays;
    private HashSet<Specialisation> specialisations;

    /**
     * Creates a {@code DoctorBuilder} with the default details.
     */
    public DoctorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        affiliations = new HashSet<>();
        affiliationHistory = new HashSet<>();
        shiftDays = new ShiftDays();
        specialisations = new HashSet<>();
    }

    /**
     * Initializes the DoctorBuilder with the data of {@code personToCopy}.
     */
    public DoctorBuilder(Doctor doctorToCopy) {
        name = doctorToCopy.getName();
        phone = doctorToCopy.getPhone();
        email = doctorToCopy.getEmail();
        affiliations = new HashSet<>(doctorToCopy.getAffiliations());
        affiliationHistory = new HashSet<>(doctorToCopy.getAffiliationHistory());
        shiftDays = doctorToCopy.getShiftDays();
        specialisations = new HashSet<>(doctorToCopy.getSpecialisations());
    }

    /**
     * Sets the {@code Name} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code affiliations} into a {@code Set<Affiliation>} and set it to the {@code Doctor} that
     * we are building.
     */
    public DoctorBuilder withAffiliations(String ... affiliations) {
        this.affiliations = SampleDataUtil.getAffiliationSet(affiliations);
        return this;
    }

    /**
     * Parses the {@code affiliationHistory} into a {@code Set<Affiliation>} and set it to the {@code Doctor} that
     * we are building.
     */
    public DoctorBuilder withAffiliationHistory(String ... affiliationHistory) {
        this.affiliationHistory = SampleDataUtil.getAffiliationSet(affiliationHistory);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code ShiftDays} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withShiftDays(Set<Integer> shiftDays) {
        this.shiftDays = new ShiftDays(shiftDays);
        return this;
    }

    /**
     * Set the {@code Specialisation} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withSpecialisations(Set<String> specialisations) {
        this.specialisations = SampleDataUtil.getSpecialisationSet(specialisations);
        return this;
    }

    @Override
    public Doctor build() {
        Doctor doctor = new Doctor(name, phone, email, affiliations, affiliationHistory);
        doctor.setShiftDays(shiftDays);
        doctor.setSpecialisations(specialisations);
        return doctor;
    }
}
