import SwiftUI

struct ContactDetailView: View {
    @EnvironmentObject var viewModel: ContactViewModel
    let contact: Contact

    @State private var showingEditForm = false
    @State private var showingMergePicker = false
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        List {
            Section("Info") {
                LabeledContent("Company", value: contact.company.isEmpty ? "—" : contact.company)
                LabeledContent("Job Title", value: contact.jobTitle.isEmpty ? "—" : contact.jobTitle)
            }

            if !contact.phoneNumbers.isEmpty {
                Section("Phone Numbers") {
                    ForEach(contact.phoneNumbers) { phone in
                        LabeledContent(phone.label, value: phone.number)
                    }
                }
            }

            if !contact.emailAddresses.isEmpty {
                Section("Email Addresses") {
                    ForEach(contact.emailAddresses) { email in
                        LabeledContent(email.label, value: email.address)
                    }
                }
            }

            Section {
                Button("Merge with another contact…") {
                    showingMergePicker = true
                }
                .foregroundColor(.orange)

                Button("Delete Contact", role: .destructive) {
                    viewModel.deleteContact(contact)
                    dismiss()
                }
            }
        }
        .navigationTitle(contact.fullName)
        .navigationBarTitleDisplayMode(.large)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button("Edit") { showingEditForm = true }
            }
        }
        .sheet(isPresented: $showingEditForm) {
            ContactFormView(mode: .edit(contact))
                .environmentObject(viewModel)
        }
        .sheet(isPresented: $showingMergePicker) {
            MergePickerView(primaryContact: contact)
                .environmentObject(viewModel)
        }
    }
}
