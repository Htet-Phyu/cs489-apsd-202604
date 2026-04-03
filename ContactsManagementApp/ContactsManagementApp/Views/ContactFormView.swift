import SwiftUI

enum FormMode {
    case add
    case edit(Contact)
}

struct ContactFormView: View {
    @EnvironmentObject var viewModel: ContactViewModel
    @Environment(\.dismiss) private var dismiss

    let mode: FormMode

    @State private var firstName = ""
    @State private var lastName = ""
    @State private var company = ""
    @State private var jobTitle = ""
    @State private var phoneNumbers: [PhoneNumber] = []
    @State private var emailAddresses: [EmailAddress] = []

    private var title: String {
        if case .add = mode { return "New Contact" }
        return "Edit Contact"
    }

    var body: some View {
        NavigationStack {
            Form {
                Section("Name") {
                    TextField("First Name", text: $firstName)
                    TextField("Last Name", text: $lastName)
                }

                Section("Work") {
                    TextField("Company", text: $company)
                    TextField("Job Title", text: $jobTitle)
                }

                Section("Phone Numbers") {
                    ForEach($phoneNumbers) { $phone in
                        HStack {
                            TextField("Number", text: $phone.number)
                                .keyboardType(.phonePad)
                            Divider()
                            TextField("Label", text: $phone.label)
                                .foregroundColor(.secondary)
                                .frame(width: 80)
                                .multilineTextAlignment(.trailing)
                        }
                    }
                    .onDelete { phoneNumbers.remove(atOffsets: $0) }
                    Button("Add Phone") {
                        phoneNumbers.append(PhoneNumber(number: "", label: "Mobile"))
                    }
                }

                Section("Email Addresses") {
                    ForEach($emailAddresses) { $email in
                        HStack {
                            TextField("Address", text: $email.address)
                                .keyboardType(.emailAddress)
                                .autocapitalization(.none)
                            Divider()
                            TextField("Label", text: $email.label)
                                .foregroundColor(.secondary)
                                .frame(width: 80)
                                .multilineTextAlignment(.trailing)
                        }
                    }
                    .onDelete { emailAddresses.remove(atOffsets: $0) }
                    Button("Add Email") {
                        emailAddresses.append(EmailAddress(address: "", label: "Home"))
                    }
                }
            }
            .navigationTitle(title)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Cancel") { dismiss() }
                }
                ToolbarItem(placement: .confirmationAction) {
                    Button("Save", action: save)
                        .disabled(firstName.isEmpty || lastName.isEmpty)
                }
            }
        }
        .onAppear(perform: loadValues)
    }

    private func loadValues() {
        guard case .edit(let c) = mode else { return }
        firstName = c.firstName
        lastName = c.lastName
        company = c.company
        jobTitle = c.jobTitle
        phoneNumbers = c.phoneNumbers
        emailAddresses = c.emailAddresses
    }

    private func save() {
        switch mode {
        case .add:
            viewModel.addContact(Contact(
                firstName: firstName, lastName: lastName,
                company: company, jobTitle: jobTitle,
                phoneNumbers: phoneNumbers, emailAddresses: emailAddresses
            ))
        case .edit(let existing):
            viewModel.updateContact(Contact(
                id: existing.id,
                firstName: firstName, lastName: lastName,
                company: company, jobTitle: jobTitle,
                phoneNumbers: phoneNumbers, emailAddresses: emailAddresses
            ))
        }
        dismiss()
    }
}
