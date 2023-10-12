package seedu.address.model.affiliation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * AffiliationModifier modifies affiliation.
 */
public class AffiliationModifier {

    /**
     * Adds the affiliated person to the affiliations for every
     * person's affiliation in the affiliation set provided.
     *
     * @param affiliationSet The affiliation set that contains all person that needs to add the affiliated person.
     * @param affiliatedPerson The affiliated person to add to every person in the affiliation set.
     * @param model The current model that is running.
     */
    public static void addAffiliations(Set<Affiliation> affiliationSet, Person affiliatedPerson, Model model) {
        requireAllNonNull(affiliationSet, affiliatedPerson, model);
        ReadOnlyAddressBook addressBook = model.getAddressBook();

        for (Affiliation affiliation: affiliationSet) {
            Person otherAffiliatedPerson = AffiliationChecker.findAffiliatedPerson(affiliation, addressBook);
            assert otherAffiliatedPerson != null;
            otherAffiliatedPerson.getAffiliations().add(new Affiliation(affiliatedPerson.getName().fullName));
        }
    }

    /**
     * Removes the affiliated person from the affiliations for every
     * person's affiliation in the affiliation set provided.
     *
     * @param affiliationSet The affiliation set that contains all person that needs to remove the affiliated person.
     * @param affiliatedPerson The affiliated person to remove from every person in the affiliation set.
     * @param model The current model that is running.
     */
    public static void removeAffiliations(Set<Affiliation> affiliationSet, Person affiliatedPerson, Model model) {
        requireAllNonNull(affiliationSet, affiliatedPerson, model);

        ReadOnlyAddressBook addressBook = model.getAddressBook();

        for (Affiliation affiliation: affiliationSet) {
            Person otherAffiliatedPerson = AffiliationChecker.findAffiliatedPerson(affiliation, addressBook);
            assert otherAffiliatedPerson != null;
            otherAffiliatedPerson.getAffiliations().remove(new Affiliation(affiliatedPerson.getName().fullName));
        }
    }

    /**
     * Changes the affiliated person's name to a new name for every
     * person's affiliation in the affiliation set provided.
     *
     * @param affiliationSet The affiliation set that contains all person that needs to change the affiliated
     *                       person from old name to new name.
     * @param oldName The old name that identify the person.
     * @param newName The new name to identify the person.
     * @param model The current model that is running.
     */
    public static void nameChangeAffiliations(Set<Affiliation> affiliationSet,
                                              Name oldName, Name newName, Model model) {
        requireAllNonNull(affiliationSet, oldName, newName, model);

        ReadOnlyAddressBook addressBook = model.getAddressBook();

        for (Affiliation affiliation: affiliationSet) {
            Person otherAffiliatedPerson = AffiliationChecker.findAffiliatedPerson(affiliation, addressBook);
            assert otherAffiliatedPerson != null;
            Set<Affiliation> otherAffiliatedSet = otherAffiliatedPerson.getAffiliations();
            for (Affiliation affiliation1: otherAffiliatedSet) {
                if (affiliation1.affiliationName.equals(oldName.fullName)) {
                    otherAffiliatedSet.remove(affiliation1);
                    otherAffiliatedSet.add(new Affiliation(newName.fullName));
                    break;
                }
            }
        }
    }
}
