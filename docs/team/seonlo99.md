---
layout: default.md
title: "Zheng Chenglong's Project Portfolio Page"
---

### Project: MediSync

MediSync is a desktop app specifically used for head nurses to manage staff and patients, optimised for use via a Command Line Interface (CLI). Instead of tracking data of staff and patients using existing applications such as Microsoft Excel, using a CLI-centric application like MediSync will help head nurses track and handle contact management more quickly.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=seonlo99&breakdown=false)

* **New Feature**: Implemented the `addaffn` command (PR: [#107](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/107))
  * It enables user to add affiliation on top of a person current affiliation rather than to overwrite it.
  * This feature was comfortable to implement as it can rely on the `AffiliationModifier` class created earlier.

* **New Feature**: Implemented the `removeah` command (PR: [#137](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/137))
  * It enables user to remove affiliation history of `Person`.
  * The difficulties of this implementation lies on how existing affiliation affects the removal of the affiliation history and how the affiliated person's affiliation history is changed.
  * Careful consideration of the `removeah` command was required to ensure no logic flaws are introduced to the affiliation and affiliation history of a `Person`.

* **New Feature**: Introduced the `NextOfKin` attribute and implemented `nok` command (PR: [#148](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/148), [#157](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/157))
  * The `NextOfKin` attribute is introduced for `Person` class.
  * The `nok` command enables user to add next-of-kin details of `Patient`.
  * Since only `Patient` objects will have this attribute, the `NextOfKin` attribute and command was designed to work with the existing `Patient` class, while ensuring its compatibility with the existing `Person` class, especially during testing phase.
  * The difficulties of this implementation lies on the design of the attribute and the storing the data to storage as `NextOfKin` attribute contains many subclasses.

* **Technical Additions**: Introduced the `AuthenticateAffiliation` and `AffiliationModifier` classes that are utilised in varying areas of the project (PRs: [#96](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/96))
  * The `AuthenticateAffiliation` is utilised whenever there is an affiliation needs to be added. This class ensures that the affiliations can be added without introducing any logic error. 
  * The `AffiliationModifier` is utilised whenever there is an affiliation needs to be modified. This class ensures that the affiliations are correctly modified without introducing any logic error.
  * The difficulties of this implementation lies on the design and logic of how `affiliation` attribute should behave under `Patient`, `Nurse` and `Doctor` Objects.

* **Enhancements to existing features**:
  * Modify `add` and `edit` command to comply with `AuthenticateAffiliation` and `AffiliationModifier` classes to prevent logic flaws. (PRs: [#96](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/96))
  * Modify `edit` command to restrict modifying of `role` attribute. (PRs: [#135](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/135))
  * Updated the saving, reading and verification of JSON file to include `NextOfKin` attribute and ensure data is thoroughly checked with any sort of violation in the data is flagged as invalid data. (PRs: [#160](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/160))

* **Documentation**:
  * User Guide:
    * Modified documentation for commands `edit`.
    * Added documentation for commands `addaffn`, `removeah`, `nok` (PR: [#161](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/161)).
    * Adjusted the `Command Summary` table. (PR: [#161](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/161)).
  
  * Developer Guide:
    * Updated `Model` component on `AuthenticateAffiliation` and `AffiliationModifier`. (PR: [#143](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/143)).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#132](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/132)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S1-CS2103T-F08-2/tp/issues/135), [2](https://github.com/AY2324S1-CS2103T-F08-2/tp/issues/136), [3](https://github.com/AY2324S1-CS2103T-F08-2/tp/issues/147),  [4](https://github.com/AY2324S1-CS2103T-F08-2/tp/issues/156), [5](https://github.com/AY2324S1-CS2103T-F08-2/tp/issues/157), [6](https://github.com/AY2324S1-CS2103T-F08-2/tp/issues/158), [7](https://github.com/AY2324S1-CS2103T-F08-2/tp/issues/159))
