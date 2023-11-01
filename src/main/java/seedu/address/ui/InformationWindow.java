package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialisation;
import seedu.address.model.person.Staff;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class InformationWindow extends UiPart<Region> {

    private static final String FXML = "InformationWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(InformationWindow.class);


    @FXML
    private VBox fullWindow;
    @FXML
    private Label name;
    @FXML
    private Label role;
    @FXML
    private Label email;
    @FXML
    private VBox shiftBlock;
    @FXML
    private Label shiftHeader;
    @FXML
    private Label shiftMon;
    @FXML
    private Label shiftTue;
    @FXML
    private Label shiftWed;
    @FXML
    private Label shiftThu;
    @FXML
    private Label shiftFri;
    @FXML
    private Label shiftSat;
    @FXML
    private Label shiftSun;
    private Label[] shiftDays;
    @FXML
    private VBox nokBlock;
    @FXML
    private Label nokHeader;
    @FXML
    private Label nokNotPresent;
    @FXML
    private Label nokName;
    @FXML
    private Label nokPhone;
    @FXML
    private Label nokRelationship;
    @FXML
    private VBox specBlock;
    @FXML
    private Label specListHeader;
    @FXML
    private VBox specListBlock;
    @FXML
    private VBox affnBlock;
    @FXML
    private Label affnCount;
    @FXML
    private VBox affnListBlock;

    /**
     * Initialises the {@code InformationWindow}.
     */
    public InformationWindow() {
        super(FXML);
        shiftDays = new Label[]{shiftMon, shiftTue, shiftWed, shiftThu, shiftFri, shiftSat, shiftSun};

        // The initialisation should not render the information window
        resetWindow();
    }

    /**
     * Displays information of the given {@code Person}.
     *
     * @param person The intended person for display. It is expected that {@code Person} passed is its subclass only.
     */
    @FXML
    public void displayInformation(Person person) {
        requireNonNull(person);

        assert (person instanceof Staff || person instanceof Patient);

        // Display window will not be updated if it is for the same person being displayed
        if (name.getText().equals(person.getName().fullName)) {
            return;
        }

        logger.info("Displaying person: " + person);

        name.setText(person.getName().fullName);
        role.setText(person.getRole().value);
        email.setText(person.getEmail().value);

        if (person instanceof Staff) {
            displayStaffInformation((Staff) person);
        } else if (person instanceof Patient) {
            displayPatientInformation((Patient) person);
        }

        showWindow();
    }

    /**
     * Displays information of the given {@code Staff}.
     *
     * @param staff The intended Staff for display. It is expected that the {@code Staff} passed is its subclass only.
     */
    private void displayStaffInformation(Staff staff) {
        assert (staff instanceof Doctor || staff instanceof Nurse);

        if (staff instanceof Doctor) {
            role.setStyle("-fx-background-color: #89CFF0; -fx-font-weight: bold; -fx-text-fill: #0047AB");
            setSpecialisation((Doctor) staff);
        } else if (staff instanceof Nurse) {
            clearSpecialisation();
            role.setStyle("-fx-background-color: #FFC0CB; -fx-font-weight: bold; -fx-text-fill: #E0115F");
        }
        clearNok();
        setShiftDays(staff);
        setAffiliations(staff);
        affnCount.setText("Tending to:");
        affnBlock.setStyle("-fx-border-color: #BBBBBB; -fx-border-width: 2 0 0 0;");
    }

    /**
     * Displays information of the given {@code Patient}.
     *
     * @param patient The intended Patient for display.
     */
    private void displayPatientInformation(Patient patient) {
        role.setStyle("-fx-background-color: #E97451; -fx-font-weight: bold; -fx-text-fill: #8B4000");
        clearShiftDays();
        clearSpecialisation();
        setNok(patient);
        setAffiliations(patient);
        affnCount.setText("Attended by:");
        affnBlock.setStyle("-fx-border-color: #BBBBBB; -fx-border-width: 2 0 0 0;");
    }

    /**
     * Sets the shift days information of the given {@code Staff}.
     *
     * @param staff The intended Staff for display.
     */
    private void setShiftDays(Staff staff) {
        clearShiftDays();

        shiftBlock.setVisible(true);
        shiftBlock.setManaged(true);
        shiftBlock.setStyle("-fx-border-color: #BBBBBB; -fx-border-width: 2 0 0 0;");
        shiftHeader.setText("Shift days:");

        for (int shiftDay : staff.getShiftDays()) {
            shiftDays[shiftDay - 1]
                    .setStyle("-fx-background-color: #AFE1AF; -fx-font-weight: bold; -fx-text-fill: #008000");
        }
    }

    /**
     * Sets the list of affiliation names into the information window.
     *
     * @param person The intended Person to display the affiliations of.
     */
    private void setAffiliations(Person person) {
        clearAffiliations();
        for (Affiliation affiliation : person.getAffiliations()) {
            String name = affiliation.toString();
            Label label = new Label("- " + name);
            label.getStyleClass().add("information-affn-list");
            affnListBlock.getChildren().add(label);
        }

        if (affnListBlock.getChildren().size() == 0) {
            Label label = new Label("[EMPTY]");
            label.getStyleClass().add("information-affn-list");
            affnListBlock.getChildren().add(label);
        }
    }

    /**
     * Removes all affiliation names from the information window.
     */
    private void clearAffiliations() {
        affnListBlock.getChildren().clear();
    }

    /**
     * Removes the shift days information from the information window.
     */
    private void clearShiftDays() {
        for (Label shiftDay : shiftDays) {
            shiftDay.setStyle("-fx-background-color: transparent; -fx-font-weight: normal; -fx-text-fill: black");;
        }
        shiftBlock.setVisible(false);
        shiftBlock.setManaged(false);
    }

    /**
     * Sets the NOK information of the given {@code Patient}.
     *
     * @param patient The intended patient for display.
     */
    private void setNok(Patient patient) {
        clearNok();
        nokBlock.setVisible(true);
        nokBlock.setManaged(true);
        nokBlock.setStyle("-fx-border-color: #BBBBBB; -fx-border-width: 2 0 0 0;");
        nokHeader.setText("Next of kin information:");

        NextOfKin nok = patient.getNextOfKin();
        if (nok.isPresent()) {
            adjustNokPresence(true);
            nokName.setText("Name: " + nok.getName().fullName);
            nokPhone.setText("Phone no.: " + nok.getPhone().value);
            nokRelationship.setText("Relationship: " + nok.getRelationship().relationship);
        } else {
            adjustNokPresence(false);
            nokNotPresent.setText("MISSING. Please add an NOK for this patient.");
        }
    }

    /**
     * Adjusts the NOK information from the information window depending on whether NOK is present.
     *
     * @param toAdd Boolean on whether NOK is present.
     */
    private void adjustNokPresence(boolean toAdd) {
        nokNotPresent.setVisible(!toAdd);
        nokNotPresent.setManaged(!toAdd);
        nokName.setVisible(toAdd);
        nokName.setManaged(toAdd);
        nokPhone.setVisible(toAdd);
        nokPhone.setManaged(toAdd);
        nokRelationship.setVisible(toAdd);
        nokRelationship.setManaged(toAdd);
    }


    /**
     * Removes the NOK information from the information window.
     */
    private void clearNok() {
        nokHeader.setText("");
        nokName.setText("");
        nokPhone.setText("");
        nokRelationship.setText("");
        nokBlock.setVisible(false);
        nokBlock.setManaged(false);
    }

    /**
     * Sets the list of specialisations of a {@code Doctor} into the information window.
     *
     * @param doctor The intended Doctor to display the specialisations of.
     */
    private void setSpecialisation(Doctor doctor) {
        clearSpecialisation();
        specBlock.setVisible(true);
        specBlock.setManaged(true);
        specBlock.setStyle("-fx-border-color: #BBBBBB; -fx-border-width: 2 0 0 0;");
        specListHeader.setText("Specialisations:");
        for (Specialisation specialisation : doctor.getSpecialisations()) {
            String spec = specialisation.toString();
            Label label = new Label("- " + spec);
            label.getStyleClass().add("information-spec-list");
            specListBlock.getChildren().add(label);
        }

        if (specListBlock.getChildren().size() == 0) {
            Label label = new Label("[EMPTY]");
            label.getStyleClass().add("information-spec-list");
            specListBlock.getChildren().add(label);
        }
    }

    /**
     * Clears the list of specialisations from the information window.
     */
    private void clearSpecialisation() {
        specBlock.setVisible(false);
        specBlock.setManaged(false);
        specListBlock.getChildren().clear();
    }

    /**
     * Displays the whole window.
     */
    public void showWindow() {
        fullWindow.setVisible(true);
        fullWindow.setManaged(true);
        fullWindow.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
    }

    /**
     * Clears the whole window.
     */
    public void resetWindow() {
        name.setText("");
        fullWindow.setVisible(false);
        fullWindow.setManaged(false);
        fullWindow.setMinSize(0, 0);
    }
}
