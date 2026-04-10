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
    └── screenshots/        
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
| Architecture | MVVM                  |
| Data format | JSON (Codable)         |

---

## ▶️ How to Run

1. Open `ContactsManagementApp.xcodeproj` in Xcode.
2. **Add all new source files to the Xcode target** (see setup note below).
3. Select an iPhone simulator (e.g. iPhone 16).
4. Press `⌘R` to build and run.

---


## 📋 Functional Requirements Summary

See [`docs/requirements.txt`](ContactsManagementApp/docs/task1.1_requirements.txt) for the full list of functional requirements.



## 👤 Author

**[Htet Moe Phyu]**  
CS489: Applied Software Development  
Maharishi International University
