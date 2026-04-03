# CS489 APSD Labs

> Swift-based applications developed for CS489: Applied Software Development, showcasing AI-assisted development with Cursor, clean architecture, domain modeling, and DevOps practices using Xcode build automation and GitHub Actions CI/CD. The repository reflects a progression of course labs covering topics such as data persistence, SQL & NoSQL databases, Web API development, application security, testing, containerization, and cloud deployment.

A monorepo containing lab projects for **CS489: Applied Software Development**, built with Swift, Xcode, and Cursor, with automated CI/CD pipelines via GitHub Actions.

---

## 📁 Project Structure

```
APSD-labs/
├── EmployeePensionsApp/       # Lab 2a — Employee Pension Enrollment
├── PAMSApp/                   # Lab 2b — Patient Appointment Management
├── ContactsManagementApp/     # Lab 3  — Contacts Management (CRUD + Merge + JSON)
├── ProductMgmtApp/            # Product Management App
└── .github/
    └── workflows/             # CI/CD workflow definitions
```

---

## 🗂 Projects

### 1. EmployeePensionsApp — Lab 2a

Manages employee pension enrollment with business rule enforcement and JSON report generation.

**Key Features**
- Pension enrollment processing
- Business rules for quarterly enrollees
- JSON report export
- Automated CI/CD via GitHub Actions

**Technologies**
- Swift, Xcode
- GitHub Actions

---

### 2. PAMSApp — Lab 2b

A Patient Appointment Management System (PAMS) that loads, processes, and exports patient data.

**Key Features**
- Loads and parses patient data
- Calculates patient age dynamically
- Sorts patients by age (descending)
- Exports results as a JSON file to the local filesystem
- GitHub Release with executable artifact

**Technologies**
- Swift, Xcode
- GitHub Actions, GitHub Releases

---

### 3. ContactsManagementApp — Lab 3

A SwiftUI Contacts Management app with full CRUD, search, merge, and JSON export — adapted from a Java CLI assignment to a native Swift/Xcode tech stack.

**Key Features**
- Add, view, edit, and delete contacts
- Search by name, company, or job title
- Merge duplicate contacts (combines phone/email, avoids duplicates)
- Export contacts as pretty-printed JSON
- Preloaded sample data sorted by last name
- MVVM architecture with clean folder structure

**Technologies**
- Swift, SwiftUI, Xcode
- MVVM, Codable, FileManager

---

### 4. ProductMgmtApp

A basic product management application for managing product records.

**Technologies**
- Swift, Xcode

---

## ⚙️ CI/CD

This repository uses **GitHub Actions** for continuous integration and deployment. Each project has its own dedicated workflow:

| Project | Workflow |
|---|---|
| EmployeePensionsApp | `.github/workflows/lab2a.yml` |
| PAMSApp | `.github/workflows/lab2b.yml` |

Workflows are triggered on push and pull request events, and run build and test steps automatically.

---

## 🚀 Releases

**Lab 2b (PAMSApp)** includes a compiled executable artifact published to [GitHub Releases](../../releases). Each release is tagged and contains the built binary for download and testing.

---

## 🛠 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/APSD-labs.git
   ```

2. Open the desired project in Xcode:
   ```
   File → Open → Select the .xcodeproj or .xcworkspace file
   ```

3. Select a simulator target and click **Run** (`⌘R`).

---

## 👤 Author

**[Htet Moe Phyu]**
CS489: Applied Software Development
Maharishi International University

---

## 📄 License

This repository is for academic purposes only.
