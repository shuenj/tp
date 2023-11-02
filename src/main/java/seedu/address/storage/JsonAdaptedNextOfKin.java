package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;

/**
 * Jackson-friendly version of {@link NextOfKin}.
 */
class JsonAdaptedNextOfKin {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String nextOfKinName;
    private final String nextOfKinPhone;
    private final String nextOfKinRelationship;

    /**
     * Constructs a {@code JsonAdaptedNextOfKin} with the given {@code nextOfKinName},
     * {@code nextOfKinPhone} and {@code nextOfKinRelationship}.
     */
    @JsonCreator
    public JsonAdaptedNextOfKin(@JsonProperty("nextOfKinName") String nextOfKinName,
                                @JsonProperty("nextOfKinPhone") String nextOfKinPhone,
                                @JsonProperty("nextOfKinRelationship") String nextOfKinRelationship) {
        this.nextOfKinName = nextOfKinName;
        this.nextOfKinPhone = nextOfKinPhone;
        this.nextOfKinRelationship = nextOfKinRelationship;
    }

    /**
     * Converts a given {@code NextOfKin} into this class for Jackson use.
     */
    public JsonAdaptedNextOfKin(NextOfKin source) {
        if (source.isPresent()) {
            this.nextOfKinName = source.getName().fullName;
            this.nextOfKinPhone = source.getPhone().value;
            this.nextOfKinRelationship = source.getRelationship().relationship;
        } else {
            this.nextOfKinName = null;
            this.nextOfKinPhone = null;
            this.nextOfKinRelationship = null;
        }
    }

    public String getNextOfKinName() {
        return nextOfKinName;
    }

    public String getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    public String getNextOfKinRelationship() {
        return nextOfKinRelationship;
    }

    /**
     * Converts this Jackson-friendly adapted nextOfKin object into the model's {@code nextOfKin} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted nextOfKin.
     */
    public NextOfKin toModelType() throws IllegalValueException {
        if (nextOfKinName == null && nextOfKinPhone == null && nextOfKinRelationship == null) {
            return new NextOfKin();
        } else if (nextOfKinName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Next of Kin Name"));
        } else if (nextOfKinPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Next of Kin Phone"));
        } else if (nextOfKinRelationship == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Next of Kin Relationship"));
        } else {
            if (!Name.isValidName(nextOfKinName)) {
                throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
            }
            if (!Phone.isValidPhone(nextOfKinPhone)) {
                throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
            }
            if (!Relationship.isValidRelationship(nextOfKinRelationship)) {
                throw new IllegalValueException(Relationship.MESSAGE_CONSTRAINTS);
            }
            return new NextOfKin(new Name(nextOfKinName), new Phone(nextOfKinPhone),
                    new Relationship(nextOfKinRelationship));
        }
    }

}
