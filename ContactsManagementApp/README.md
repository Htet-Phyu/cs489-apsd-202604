# ContactsManagementApp

A SwiftUI-based iOS Contacts Management application built for **CS489: Applied Software Development**.  
Implements full CRUD operations, search, merge logic, and JSON export — adapted from a Java CLI assignment to a native Swift/SwiftUI/Xcode tech stack.

---

## 📁 Project Structure

```
ContactsManagementApp/
├── ContactsManagementApp.xcodeproj
└── ContactsManagementApp/
    ├── ContactsManagementAppApp.swift   # App entry point (@main)
    ├── ContentView.swift                # Root view
    ├── Models/
    │   ├── Contact.swift
    │   ├── PhoneNumber.swift
    │   └── EmailAddress.swift
    ├── ViewModels/
    │   └── ContactViewModel.swift
    ├── Services/
    │   ├── ContactService.swift
    │   └── JSONExportService.swift
    ├── Views/
    │   ├── ContactListView.swift
    │   ├── ContactDetailView.swift
    │   ├── ContactFormView.swift
    │   ├── JSONOutputView.swift
    │   └── MergePickerView.swift
    ├── docs/
    │   ├── requirements.txt
    │   └── contacts.json               # Generated at runtime
    └── screenshots/                    # Place submission screenshots here
```

---

## ✨ Key Features

- Display contacts sorted by last name (ascending)
- Search by name, company, or job title
- Add, edit, and delete contacts
- Merge duplicate contacts (combines phone numbers and emails, avoids duplicates)
- Export all contacts as pretty-printed JSON
- View JSON output in-app and save to device Documents directory
- Preloaded with 3 sample contacts

---

## 🏗 Domain Model

```
Contact
  ├── id: UUID
  ├── firstName: String
  ├── lastName: String
  ├── company: String
  ├── jobTitle: String
  ├── phoneNumbers: [PhoneNumber]    (one-to-many)
  └── emailAddresses: [EmailAddress] (one-to-many)

PhoneNumber
  ├── id: UUID
  ├── number: String
  └── label: String

EmailAddress
  ├── id: UUID
  ├── address: String
  └── label: String
```

---

## 🔄 UML Class Diagram (Text Specification for StarUML)

```
+------------------+       +------------------+
|    Contact       |1    * |   PhoneNumber    |
+------------------+------>+------------------+
| - id: UUID       |       | - id: UUID       |
| - firstName      |       | - number: String |
| - lastName       |       | - label: String  |
| - company        |       +------------------+
| - jobTitle       |
|                  |       +------------------+
|                  |1    * |  EmailAddress    |
+------------------+------>+------------------+
                           | - id: UUID       |
                           | - address: String|
                           | - label: String  |
                           +------------------+
```

---

## 🛠 Technologies

| Layer      | Technology              |
|------------|-------------------------|
| Language   | Swift 5                 |
| UI         | SwiftUI                 |
| IDE        | Xcode                   |
| Editor     | Cursor (AI-assisted)    |
| Architecture | MVVM                 |
| Data format | JSON (Codable)         |

---

## ▶️ How to Run

1. Open `ContactsManagementApp.xcodeproj` in Xcode.
2. **Add all new source files to the Xcode target** (see setup note below).
3. Select an iPhone simulator (e.g. iPhone 16).
4. Press `⌘R` to build and run.

> **Setup note:** Since files were created outside Xcode, you must add them manually:  
> In Xcode, right-click each group folder → **Add Files to "ContactsManagementApp"…** → select the `.swift` files inside `Models/`, `ViewModels/`, `Services/`, and `Views/`. Make sure **"Add to targets: ContactsManagementApp"** is checked.

---

## 📸 Screenshots to Take for Submission

| Screenshot | What to capture |
|---|---|
| `xcode_project_structure.png` | Xcode left panel showing all groups/files |
| `requirements_txt.png` | `docs/requirements.txt` open in Xcode or editor |
| `app_contacts_list.png` | App running in simulator showing the contacts list |
| `app_contact_detail.png` | Contact detail screen with phone/email sections |
| `app_add_contact.png` | Add/Edit contact form |
| `app_merge.png` | Merge picker sheet |
| `json_output.png` | JSON output sheet visible in the app |
| `xcode_console.png` | Xcode console showing the saved JSON file path |
| `git_log.png` | Terminal showing `git log` or GitHub commits |

---

## 🔁 Java CLI → SwiftUI Adaptation Notes

- **Java `main()` → `@main` App struct**: The CLI entry point is replaced by `ContactsManagementAppApp.swift` using SwiftUI's `@main` lifecycle.
- **Java classes → Swift structs**: Domain models use `struct` with `Codable` for type-safe JSON serialization.
- **Java `ArrayList` → Swift `[Contact]`**: Collections are native Swift arrays managed in `ContactViewModel`.
- **Java `System.out.println` → Xcode console**: JSON output is printed via `print()` and also displayed in a SwiftUI sheet.
- **Java Scanner (CLI input) → SwiftUI Form**: User input is handled through native form views instead of command-line prompts.
- **Java file I/O → `FileManager`**: JSON is written to the app's sandboxed Documents directory using Swift's `FileManager`.
- **No test runner needed**: The app is validated visually by running on the iOS Simulator in Xcode.
- **Same core requirements preserved**: CRUD, search, merge, sort, and JSON export are all fully implemented.

---

## 📋 Functional Requirements Summary

See [`docs/requirements.txt`](ContactsManagementApp/docs/requirements.txt) for the full list (FR-01 through FR-12).

---

## 📄 Git & Submission

**Files to commit:**
- All `.swift` source files
- `docs/requirements.txt`
- `docs/contacts.json` (if pre-generated)
- `screenshots/` folder with your images
- `README.md`

**Files to exclude** (already handled by `.gitignore`):
- `*.xcuserstate`
- `xcuserdata/`
- `DerivedData/`
- `.DS_Store`

---

## 👤 Author

**[Htet Moe Phyu]**  
CS489: Applied Software Development  
Maharishi International University
