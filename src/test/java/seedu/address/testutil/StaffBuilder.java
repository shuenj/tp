package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Staff;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Staff objects.
 */
public class StaffBuilder {

    public static final String DEFAULT_NAME = "Jennifer Chue";
    public static final String DEFAULT_PHONE = "85552222";
    public static final String DEFAULT_EMAIL = "jenchue@facemail.com";
    public static final String DEFAULT_ROLE = "Doctor";


    private Name name;
    private Phone phone;
    private Email email;
    private Role role;

    private Set<Affiliation> affiliations;
    private Set<Affiliation> affiliationHistory;
    private ShiftDays shiftDays;
    private HashSet<Specialisation> specialisations;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        role = new Role(DEFAULT_ROLE);
        affiliations = new HashSet<>();
        affiliationHistory = new HashSet<>();
        shiftDays = new ShiftDays();
    }

    /**
     * Initializes the StaffBuilder with the data of {@code personToCopy}.
     */
    public StaffBuilder(Staff staffToCopy) {
        name = staffToCopy.getName();
        phone = staffToCopy.getPhone();
        email = staffToCopy.getEmail();
        role = staffToCopy.getRole();
        affiliations = new HashSet<>(staffToCopy.getAffiliations());
        affiliationHistory = new HashSet<>(staffToCopy.getAffiliationHistory());
        shiftDays = staffToCopy.getShiftDays();
    }

    /**
     * Sets the {@code Name} of the {@code Staff} that we are building.
     */
    public StaffBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code affiliations} into a {@code Set<Affiliation>} and set it to the {@code Staff} that
     * we are building.
     */
    public StaffBuilder withAffiliations(String ... affiliations) {
        this.affiliations = SampleDataUtil.getAffiliationSet(affiliations);
        return this;
    }

    /**
     * Parses the {@code affiliationHistory} into a {@code Set<Affiliation>} and set it to the {@code Staff} that
     * we are building.
     */
    public StaffBuilder withAffiliationHistory(String ... affiliationHistory) {
        this.affiliationHistory = SampleDataUtil.getAffiliationSet(affiliationHistory);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Staff} that we are building.
     */
    public StaffBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Staff} that we are building.
     */
    public StaffBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Staff} that we are building.
     */
    public StaffBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code ShiftDays} of the {@code Staff} that we are building.
     */
    public StaffBuilder withShiftDays(Set<Integer> shiftDays) {
        this.shiftDays = new ShiftDays(shiftDays);
        return this;
    }

    public Staff build() {
        return new Staff(name, phone, email, role, affiliations, affiliationHistory).setShiftDays(shiftDays);
    }
}
