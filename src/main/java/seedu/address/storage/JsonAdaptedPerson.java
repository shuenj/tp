package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.person.Staff;

/**
 * Jackson-friendly version of {@link Person}.
 */
@JsonSerialize(using = CustomJsonAdaptedPersonSerializer.class)
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String role;
    private final List<JsonAdaptedAffiliation> affiliations = new ArrayList<>();
    private final List<JsonAdaptedAffiliation> affiliationHistory = new ArrayList<>();
    @JsonIgnore
    private final Set<Integer> shiftDays = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("role") String role,
                             @JsonProperty("affiliations") List<JsonAdaptedAffiliation> affiliations,
                             @JsonProperty("affiliationHistory") List<JsonAdaptedAffiliation> affiliationHistory,
                             @JsonProperty("shiftDays") List<Integer> shiftDays) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        if (affiliations != null) {
            this.affiliations.addAll(affiliations);
        }
        if (affiliationHistory != null) {
            this.affiliationHistory.addAll(affiliationHistory);
        }
        if (shiftDays != null) {
            this.shiftDays.addAll(shiftDays);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        role = source.getRole().value;
        affiliations.addAll(source.getAffiliations().stream()
                .map(JsonAdaptedAffiliation::new)
                .collect(Collectors.toList()));
        affiliationHistory.addAll(source.getAffiliationHistory().stream()
                .map(JsonAdaptedAffiliation::new)
                .collect(Collectors.toList()));
        if (source instanceof Staff) {
            shiftDays.addAll(((Staff) source).getShiftDays().shiftDays);
        }
    }

    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
    public List<JsonAdaptedAffiliation> getAffiliations() {
        return affiliations;
    }
    public List<JsonAdaptedAffiliation> getAffiliationHistory() {
        return affiliationHistory;
    }
    public Set<Integer> getShiftDays() {
        return shiftDays;
    }

    /**
     * Generates a list of affiliations from the stored JSON data.
     *
     * @return A list of {@link Affiliation} objects based on the stored JSON data.
     * @throws IllegalValueException If there were any data constraints violated in the affiliations.
     */
    private List<Affiliation> generateAffiliationList() throws IllegalValueException {
        final List<Affiliation> personAffiliations = new ArrayList<>();
        for (JsonAdaptedAffiliation affiliation : affiliations) {
            personAffiliations.add(affiliation.toModelType());
        }
        return personAffiliations;
    }

    /**
     * Generates a list of affiliation history from the stored JSON data.
     *
     * @return A list of {@link Affiliation} objects based on the stored JSON data.
     * @throws IllegalValueException If there were any data constraints violated in the affiliation history.
     */
    private List<Affiliation> generateAffiliationHistoryList() throws IllegalValueException {
        final List<Affiliation> personAffiliationHistory = new ArrayList<>();
        for (JsonAdaptedAffiliation affiliation : affiliationHistory) {
            personAffiliationHistory.add(affiliation.toModelType());
        }
        return personAffiliationHistory;
    }

    /**
     * Generates a {@link Name} object from the stored JSON data.
     *
     * @param name The person's name.
     * @return A {@link Name} object based on the stored JSON data.
     * @throws IllegalValueException If the stored name is null or does not meet the constraints.
     */
    private Name generateName(String name) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        return new Name(name);
    }

    /**
     * Generates a {@link Phone} object from the stored JSON data.
     *
     * @param phone The person's phone number.
     * @return A {@link Phone} object based on the stored JSON data.
     * @throws IllegalValueException If the stored phone is null or does not meet the constraints.
     */
    private Phone generatePhone(String phone) throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    /**
     * Generates an {@link Email} object from the stored JSON data.
     *
     * @param email The person's email address.
     * @return An {@link Email} object based on the stored JSON data.
     * @throws IllegalValueException If the stored email is null or does not meet the constraints.
     */
    private Email generateEmail(String email) throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

    /**
     * Generates a {@link Role} object from the stored JSON data.
     *
     * @param role The person's role (e.g., Staff).
     * @return A {@link Role} object based on the stored JSON data.
     * @throws IllegalValueException If the stored role is null or does not meet the constraints.
     */
    private Role generateRole(String role) throws IllegalValueException {
        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(role);
    }

    /**
     * Generates a {@link ShiftDays} object from the stored JSON data (only for Staffs).
     *
     * @param shiftDays The person's shift days.
     * @return A {@link ShiftDays} object based on the stored JSON data.
     * @throws IllegalValueException If the role is "Staff" and shiftDays are null or do not meet the constraints.
     */
    private ShiftDays generateShiftDays(Set<Integer> shiftDays) throws IllegalValueException {
        if (role.equals("Staff") && shiftDays == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ShiftDays.class.getSimpleName()));
        }
        if (!ShiftDays.isValidShiftDays(shiftDays)) {
            throw new IllegalValueException(ShiftDays.MESSAGE_CONSTRAINTS);
        }
        return new ShiftDays(shiftDays);
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final Name modelName = generateName(name);
        final Phone modelPhone = generatePhone(phone);
        final Email modelEmail = generateEmail(email);
        final Role modelRole = generateRole(role);
        final ShiftDays modelShiftDays = generateShiftDays(shiftDays);

        final List<Affiliation> personAffiliations = generateAffiliationList();
        final List<Affiliation> personAffiliationHistory = generateAffiliationHistoryList();
        final Set<Affiliation> modelAffiliations = new HashSet<>(personAffiliations);
        final Set<Affiliation> modelAffiliationHistory = new HashSet<>(personAffiliationHistory);

        Person generatedPerson = modelRole.generatePerson(modelName,
                modelPhone, modelEmail, modelAffiliations, modelAffiliationHistory);
        if (generatedPerson instanceof Staff) {
            return ((Staff) generatedPerson).setShiftDays(modelShiftDays);
        }

        return generatedPerson;
    }
}
