# PAMSApp — Lab 2b

A SwiftUI app implementing the **Patient Administration Management System (PAMS)** for Lab 2b.

## Overview

Loads a set of patients, calculates their current ages, sorts them by age (descending), and writes the result to a pretty-printed JSON file.

## Project Structure

```
PAMSApp/
├── Models/
│   └── Patient.swift          # Codable Patient struct
├── Data/
│   └── PatientDataLoader.swift # Hard-coded patient seed data
├── Services/
│   ├── PatientService.swift    # Age calculation & sorting
│   └── JSONFileWriter.swift    # Encodes & writes patients.json
└── ContentView.swift           # SwiftUI entry point
```

## Features

- Calculates each patient's current age from their date of birth
- Sorts patients by age, oldest first
- Exports `patients.json` with pretty-printed output to the app's Documents/output/ folder
- Displays the patient list and a success/error message in the UI

## Output

The generated `patients.json` is written to:

```
<App Documents Directory>/output/patients.json
```

The full path is shown in the UI after a successful write.

## Running

Open `PAMSApp.xcodeproj` in Xcode, select a simulator or device, and run (⌘R). The list populates and the JSON file is created automatically on launch.
