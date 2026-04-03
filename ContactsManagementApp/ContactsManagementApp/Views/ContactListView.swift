import SwiftUI

// SCREENSHOT: Take a screenshot of this list view for "contacts_list.png"
struct ContactListView: View {
    @EnvironmentObject var viewModel: ContactViewModel
    @State private var showingAddForm = false
    @State private var showingJSON = false

    var body: some View {
        NavigationStack {
            List {
                ForEach(viewModel.filteredContacts) { contact in
                    NavigationLink(destination: ContactDetailView(contact: contact)) {
                        VStack(alignment: .leading, spacing: 2) {
                            Text(contact.fullName)
                                .font(.headline)
                            Text("\(contact.jobTitle) · \(contact.company)")
                                .font(.caption)
                                .foregroundColor(.secondary)
                        }
                        .padding(.vertical, 2)
                    }
                }
                .onDelete(perform: viewModel.delete)
            }
            .searchable(text: $viewModel.searchText, prompt: "Name, company, or job title")
            .navigationTitle("Contacts")
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Button("Export JSON") {
                        viewModel.saveJSONToFile()
                        showingJSON = true
                    }
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button { showingAddForm = true } label: {
                        Image(systemName: "plus")
                    }
                }
            }
            .sheet(isPresented: $showingAddForm) {
                ContactFormView(mode: .add)
                    .environmentObject(viewModel)
            }
            // SCREENSHOT: Take a screenshot of this sheet for "json_output.png"
            .sheet(isPresented: $showingJSON) {
                JSONOutputView(json: viewModel.exportJSON())
            }
        }
    }
}

#Preview {
    ContactListView()
        .environmentObject(ContactViewModel())
}
