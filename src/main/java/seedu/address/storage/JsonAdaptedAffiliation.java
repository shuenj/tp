package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.affiliation.Affiliation;

/**
 * Jackson-friendly version of {@link Affiliation}.
 */
class JsonAdaptedAffiliation {

    private final String affiliationName;

    /**
     * Constructs a {@code JsonAdaptedAffiliation} with the given {@code affiliationName}.
     */
    @JsonCreator
    public JsonAdaptedAffiliation(String affiliationName) {
        this.affiliationName = affiliationName;
    }

    /**
     * Converts a given {@code Affiliation} into this class for Jackson use.
     */
    public JsonAdaptedAffiliation(Affiliation source) {
        affiliationName = source.affiliationName;
    }

    @JsonValue
    public String getAffiliationName() {
        return affiliationName;
    }

    /**
     * Converts this Jackson-friendly adapted affiliation object into the model's {@code Affiliation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted affiliation.
     */
    public Affiliation toModelType() throws IllegalValueException {
        if (!Affiliation.isValidAffiliationName(affiliationName)) {
            throw new IllegalValueException(Affiliation.MESSAGE_CONSTRAINTS);
        }
        return new Affiliation(affiliationName);
    }

}
