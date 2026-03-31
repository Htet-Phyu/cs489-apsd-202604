# EmployeePensionsApp

> **CS489 — Applied Software Design | Lab 2a**

A Swift iOS application that manages and reports on employee pension plan enrollment. The app processes in-memory employee data and produces structured JSON reports to the console, applying real-world business rules around quarterly enrollment eligibility.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Swift 5 | Application language |
| Xcode 26 | IDE & build toolchain |
| SwiftUI | UI framework |
| Git & GitHub | Version control |
| GitHub Actions | CI/CD pipeline |

---

## Features

- **All Employees Report** — Lists every employee in JSON format, sorted by yearly salary (descending), then last name (ascending). Nested pension plan data is included when present.
- **Current Quarterly Enrollees** — Identifies employees who reach their 1-year employment anniversary within the current calendar quarter, have no existing pension plan, and earn ≥ $100,000/year. Sorted by employment date (descending).
- **Next Quarterly Upcoming Enrollees** — Same eligibility rules applied to the next calendar quarter. Sorted by employment date (descending), then yearly salary (ascending).
- **In-memory data** — Employee and pension plan records are loaded from hardcoded Swift data (no database or external files required).
- **Pretty-printed JSON output** — All reports use `JSONEncoder` with `.prettyPrinted` and a custom `yyyy-MM-dd` ISO date encoding strategy.

---

## Project Structure

```
EmployeePensionsApp/
├── EmployeePensionsApp.xcodeproj/
└── EmployeePensionsApp/
    ├── EmployeePensionsAppApp.swift       # App entry point (@main)
    ├── Models/
    │   ├── Employee.swift                 # Employee Codable model
    │   └── PensionPlan.swift              # PensionPlan Codable model
    ├── Data/
    │   └── DataLoader.swift               # Hardcoded data + shared ISO date formatter
    ├── Services/
    │   └── EmployeePensionService.swift   # Business logic, quarter helpers, report printer
    ├── Views/
    │   └── ContentView.swift              # SwiftUI view — triggers report on appear
    └── Resources/
        └── Assets.xcassets
```

---

## Business Rules

An employee qualifies for a quarterly enrollment report if **all** of the following are true:

1. They have **no existing pension plan**.
2. Their `yearlySalary` is **≥ $100,000**.
3. Their **1-year employment anniversary** falls on or between the first and last day of the target quarter.

Quarter boundaries are computed dynamically at runtime based on the current date.

---

## How to Run

### Option 1 — Xcode (recommended)

1. Open `EmployeePensionsApp.xcodeproj` in Xcode.
2. Select a simulator target (iPhone or iPad).
3. Press **⌘R** to build and run.
4. Open the **Debug area / Console** (⌘⇧C) to view the JSON report output.

### Option 2 — Terminal

```bash
xcodebuild -scheme EmployeePensionsApp build
```

---

## Sample Output

```
=== All Employees ===
[
  {
    "employeeId" : 3,
    "firstName" : "Carly",
    "lastName" : "Jones",
    "yearlySalary" : 842000.75,
    "employmentDate" : "2024-05-16",
    "pensionPlan" : {
      "planReferenceNumber" : "SM2307",
      "enrollmentDate" : "2025-05-17",
      "monthlyContribution" : 1555.5
    }
  },
  {
    "employeeId" : 2,
    "firstName" : "Benard",
    "lastName" : "Shaw",
    "yearlySalary" : 197750,
    "employmentDate" : "2025-02-03",
    "pensionPlan" : {
      "planReferenceNumber" : "EX0089",
      "enrollmentDate" : "2026-02-03",
      "monthlyContribution" : 450
    }
  },
  ...
]

=== Current Quarterly Enrollees ===
[]

=== Next Quarterly Upcoming Enrollees ===
[
  {
    "employeeId" : 4,
    "firstName" : "Wesley",
    "lastName" : "Schneider",
    "yearlySalary" : 174500,
    "employmentDate" : "2025-04-30"
  }
]
```

> **Note:** Report output is dynamic. Results change each quarter as employment anniversaries shift in and out of the current and next quarter windows.

---

## CI/CD — GitHub Actions

A GitHub Actions workflow automatically builds the project on every `push` and `pull_request` targeting the `main` branch, ensuring the codebase always compiles cleanly.

**Workflow location:** `.github/workflows/build.yml`

```yaml
name: Build EmployeePensionsApp

on:
  push:
    branches: [main]
  pull_request:
    branches: [main]

jobs:
  build:
    runs-on: macos-latest
    steps:
      - uses: actions/checkout@v4
      - name: Build
        run: |
          xcodebuild -scheme EmployeePensionsApp \
                     -destination 'platform=iOS Simulator,name=iPhone 16' \
                     build
```

---

## Author

**Htet Moe Phyu**
Maharishi International University — CS489 Applied Software Design
