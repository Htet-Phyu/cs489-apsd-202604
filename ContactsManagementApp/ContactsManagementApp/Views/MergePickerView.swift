import SwiftUI

struct MergePickerView: View {
    @EnvironmentObject var viewModel: ContactViewModel
    @Environment(\.dismiss) private var dismiss

    let primaryContact: Contact

    private var candidates: [Contact] {
        viewModel.contacts
            .filter { $0.id != primaryContact.id }
            .sorted { $0.lastName < $1.lastName }
    }

    var body: some View {
        NavigationStack {
            List(candidates) { contact in
                Button {
                    viewModel.mergeContacts(primaryContact, with: contact)
                    dismiss()
                } label: {
                    VStack(alignment: .leading, spacing: 2) {
                        Text(contact.fullName).font(.headline).foregroundColor(.primary)
                        Text("\(contact.jobTitle) · \(contact.company)")
                            .font(.caption)
                            .foregroundColor(.secondary)
                    }
                }
            }
            .navigationTitle("Merge \(primaryContact.firstName) with…")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Cancel") { dismiss() }
                }
            }
        }
    }
}
