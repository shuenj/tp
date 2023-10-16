package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class InformationWindow extends UiPart<Region> {

    private static final String FXML = "InformationWindow.fxml";

    @FXML
    private Label name;
    @FXML
    private Label role;
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

    @FXML
    private HBox affnBlock;
    @FXML
    private Label affnCount;


    /**
     * Initialises the {@code InformationWindow}.
     */
    public InformationWindow() {
        super(FXML);
    }

    /**
     * Displays information of the given {@code Person}.
     */
    @FXML
    public void displayInformation(Person person) {
        requireNonNull(person);

        name.setText(person.getName().fullName);
        role.setText(person.getRole().value);

        if (person.getRole().toString().toUpperCase().equals(Role.Type.DOCTOR.name())) {
            displayDoctorInformation(person);
        } else if (person.getRole().toString().toUpperCase().equals(Role.Type.PATIENT.name())) {
            displayPatientInformation(person);
        }
    }

    private void displayDoctorInformation(Person person) {
        role.setStyle("-fx-background-color: #89CFF0; -fx-font-weight: bold; -fx-text-fill: #0047AB");
        setShiftDays(person);
        affnCount.setText("No. of patients in charge: " + person.getAffiliations().size());
        affnBlock.setStyle("-fx-border-color: #BBBBBB; -fx-border-width: 2 0 0 0;");
    }

    private void displayPatientInformation(Person person) {
        role.setStyle("-fx-background-color: #E97451; -fx-font-weight: bold; -fx-text-fill: #8B4000");
        clearShiftDays();
        affnCount.setText("Staff overhead: " + person.getAffiliations().size());
        affnBlock.setStyle("-fx-border-color: #BBBBBB; -fx-border-width: 2 0 0 0;");
    }

    /**
     * Sets the shift days information of the given {@code Person}.
     */
    private void setShiftDays(Person person) {

        shiftBlock.setVisible(true);
        shiftBlock.setManaged(true);
        shiftBlock.setStyle("-fx-border-color: #BBBBBB; -fx-border-width: 2 0 0 0;");
        shiftHeader.setText("Shift days: ");

        // Function to be modified in the future. Currently picks only Mon, Thu and Sat.
        shiftMon.setText("Mon");
        shiftTue.setText("Tue");
        shiftWed.setText("Wed");
        shiftThu.setText("Thu");
        shiftFri.setText("Fri");
        shiftSat.setText("Sat");
        shiftSun.setText("Sun");

        shiftMon.setStyle("-fx-background-color: #AFE1AF; -fx-font-weight: bold; -fx-text-fill: #008000");
        // shiftTue.setStyle("-fx-background-color: #89CFF0; -fx-font-weight: bold; -fx-text-fill: #0047AB");
        // shiftWed.setStyle("-fx-background-color: #89CFF0; -fx-font-weight: bold; -fx-text-fill: #0047AB");
        shiftThu.setStyle("-fx-background-color: #AFE1AF; -fx-font-weight: bold; -fx-text-fill: #008000");
        // shiftFri.setStyle("-fx-background-color: #89CFF0; -fx-font-weight: bold; -fx-text-fill: #0047AB");
        shiftSat.setStyle("-fx-background-color: #AFE1AF; -fx-font-weight: bold; -fx-text-fill: #008000");
        // shiftSun.setStyle("-fx-background-color: #89CFF0; -fx-font-weight: bold; -fx-text-fill: #0047AB");
    }

    private void clearShiftDays() {
        shiftBlock.setVisible(false);
        shiftBlock.setManaged(false);
    }

}
