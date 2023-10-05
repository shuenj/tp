---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# MediSync User Guide

MediSync is a **desktop app specifically used for head nurses to manage staff and patients, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). Instead of tracking data of staff and patients using existing applications such as Microsoft Excel, using a CLI-centric application like MediSync will help head nurses track and handle contact management more quickly.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `medisync.jar` from [here](https://github.com/AY2324S1-CS2103-T16-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MediSync.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar medisync.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com r/patient af/Dr Mike` : Adds a contact named `John Doe` to MediSync.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [a/AFFN]` can be used as `n/John Doe a/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[a/AFFN]…​` can be used as ` ` (i.e. 0 times), `a/friend`, `a/friend a/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessageT16.png)

Format: `help`


### Adding a person: `add`

Adds a contact to the contact list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROLE [a/AFFN]…​`

<box type="tip" seamless>

**Tip:** A person can have any number of affiliations (including 0)

</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com r/patient`
* `add n/John Doe p/98765432 e/johnd@example.com r/patient a/Dr Mike`

### Listing all persons : `list`

Shows a list of all persons in the contact list.

Format: `list`

### Editing a person : `edit`

Edits an existing contact in the contact list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [r/ROLE] [a/AFFN]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing affiliations, the existing affiliations of the person will be removed i.e adding of affiliations is not cumulative.
* You can remove all the person’s affiliations by typing `a/` without
    specifying any affiliations after it.

Examples:
*  `edit 3 p/81234567 a/` Edits the phone number of the 3rd person to `81234567` and removes the person’s affiliation
*  `edit 1 n/Sally Wing e/sallyw@kmail.com` Edits the name and the email of the 1st person to `Sally Wing` and `sallyw@kmail.com` respectively.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find sally` returns `Sally Wing`<br>
  ![result for 'find alex david'](images/findSallyResult.png)

### Returning affiliations of a doctor/patient: `affn`

Finds doctors/patients who are affiliated with the patient/doctor indicated
by the given index.

* Finds affiliations for the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `affn 2` lists the people affiliated to the 2nd person in the contact list.
* Subsequently, `affn 1` will list the people affiliated with the 1st person displayed after the previous `affn` command.

### Deleting a person : `delete`

Deletes the contact in the contact list.
If the other contacts have affiliations with this contact, the affiliations will be deleted automatically.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 3` deletes the 3rd person in the contact list.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the contact list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MediSync data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MediSync data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless></box>

**Caution:**
If your changes to the data file makes its format invalid, MediSync will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MediSync home folder.

### More FAQ to come

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action          | Format, Examples                                                                                                                     |
|-----------------|--------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `add n/NAME p/PHONE_NUMBER e/EMAIL r/ROLE [a/AFFN]…​` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com r/patient a/Dr Mike` |
| **Clear**       | `clear`                                                                                                                              |
| **Delete**      | `delete INDEX`<br> e.g., `delete 3`                                                                                                  |
| **Edit**        | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [r/ROLE] [a/AFFN]…​`<br> e.g.,`edit 1 p/91234567 e/johndoe@example.com`                     |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                           |
| **Affiliation** | `affn INDEX`                                                                                                                         |
| **List**        | `list`                                                                                                                               |
| **Help**        | `help`                                                                                                                               |
| **Exit**        | `exit`                                                                                                                               |
