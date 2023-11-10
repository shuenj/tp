---
  layout: default.md
  title: "Ng Shuen Jin's Project Portfolio Page"
---

### Project: MediSync

MediSync is a desktop app specifically used for head nurses to manage staff and patients, optimised for use via a Command Line Interface (CLI). Instead of tracking data of staff and patients using existing applications such as Microsoft Excel, using a CLI-centric application like MediSync will help head nurses track and handle contact management more quickly.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=shuenj&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=shuenj&tabRepo=AY2324S1-CS2103-T16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**:
  * **New feature:** Added `affnh` attribute and command
    * The Affiliation History feature tracks the staff/patients that used to be affiliated or are currently affiliated with the patient/staff.
    * Careful consideration of the design of the `affnh` attribute and command was required to ensure seamless interaction with the existing `Affiliation` related classes.
    * Modifications were necessary in commands such as `AddCommand` and `EditCommand` to ensure the `affnh` is updated correctly such that bidirectional relationship is maintained.
    * Ensuring that the Person loop is closed (i.e. no unregistered names remain in the system) by carefully handling cases when a person is deleted or during name changes.
  * **New feature:** Added `spec` attribute and command
    * This feature allows dynamic editing of the `spec` attribute of a `Doctor`.
    * Since only `Doctor` objects will have this attribute, the `spec` attribute and command was designed to work with the existing `Doctor` class, while ensuring its compatibility with the existing `Staff` class, especially during testing phase.
    * Careful consideration of the parser was also necessary to ensure `spec` is able to handle multiple specialisations that are separated by commas.
  * **Existing feaure:** Updated `shift` feature 
    * Allow clearing of shift days (PR: [#150](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/150))
    * Proper handling of invalid shift days (PR: [#151](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/151), [#152](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/152), [#209](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/209))
    * Wrote tests for `ShiftCommandParser` (PR: [#209](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/209))

* **Contributions to the UG**:
  * Documentation for features `affnh`, `spec`, `shift`, `delete` (PR: [#159](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/159/files), [#203](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/203))
  * Update of command summary table (PR: [#81](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/81))

* **Contributions to the DG**:
  * Model component write-up and class diagram (PR: [#132](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/132))
  * Affiliation History feature implementation write-up and activity diagram, including design considerations for alternative implementations (PR: [#132](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/132), [#138](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/138), [#140](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/140))
  * UC01 to UC05 (PR: [#83](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/83))

* **Contributions to team-based tasks**:
  * Customize app output
    * Ensured app's name, output, instructions, javadocs and error messages are aligned with the use cases of MediSync. (PR: [#95](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/95))

* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments)
    * [#71](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/71), [#98](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/98), [#127](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/127), [#130](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/130), [#147](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/147)

