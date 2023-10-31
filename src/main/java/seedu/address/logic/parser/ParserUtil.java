package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.Specialisation;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String affiliation} into a {@code Affiliation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code affiliation} is invalid.
     */
    public static Affiliation parseAffiliation(String affiliation) throws ParseException {
        requireNonNull(affiliation);
        String trimmedAffiliation = affiliation.trim();
        if (!Affiliation.isValidAffiliationName(trimmedAffiliation)) {
            throw new ParseException(Affiliation.MESSAGE_CONSTRAINTS);
        }
        return new Affiliation(trimmedAffiliation);
    }

    /**
     * Parses {@code Collection<String> affiliations} into a {@code Set<Affiliation>}.
     */
    public static Set<Affiliation> parseAffiliations(Collection<String> affiliations) throws ParseException {
        requireNonNull(affiliations);
        final Set<Affiliation> affiliationSet = new HashSet<>();
        for (String affiliationName : affiliations) {
            affiliationSet.add(parseAffiliation(affiliationName));
        }
        return affiliationSet;
    }

    /**
     * Parses a {@code String shiftDayString} into a {@code ShiftDays}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code shiftDayString} is invalid.
     */
    public static Set<Integer> parseShiftDays(String shiftDayString) throws ParseException {
        requireNonNull(shiftDayString);
        // check if valid
        if (!shiftDayString.trim().chars().allMatch(x -> x >= '1' && x <= '7')) {
            throw new ParseException("Indication of shift days should be integers "
                    + "between 1 to 7 inclusive.");
        }
        return shiftDayString.trim().chars().map(x -> x - '0') // converts string into CharStream, then into IntStream
                .boxed().collect(Collectors.toSet());
    }

    /**
     * Parses {@code String specialisations} into a {@code Set<Specialisation>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code specialisations} is invalid.
     */
    public static Set<Specialisation> parseSpecialisations(String specialisations)
            throws ParseException {
        requireNonNull(specialisations);
        String trimmedSpecialisations = specialisations.trim();
        if (trimmedSpecialisations.isEmpty()) {
            return new HashSet<>();
        }
        String[] specialisationNames = trimmedSpecialisations.split(",");
        Set<Specialisation> specialisationSet = new HashSet<>();
        for (String specialisationName : specialisationNames) {
            String specialisationNameTrimmed = specialisationName.trim();
            if (!Specialisation.isValidSpecialisationName(specialisationNameTrimmed)) {
                throw new ParseException(Specialisation.MESSAGE_CONSTRAINTS);
            }
            specialisationSet.add(new Specialisation(specialisationNameTrimmed));
        }
        return specialisationSet;
    }


}
