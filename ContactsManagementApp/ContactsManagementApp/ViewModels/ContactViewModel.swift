import Foundation
import Combine

class ContactViewModel: ObservableObject {
    @Published var contacts: [Contact] = []
    @Published var searchText: String = ""

    init() {
        contacts = ContactService().loadSampleContacts()
    }

    var filteredContacts: [Contact] {
        let sorted = contacts.sorted { $0.lastName < $1.lastName }
        guard !searchText.isEmpty else { return sorted }
        let query = searchText.lowercased()
        return sorted.filter {
            $0.firstName.lowercased().contains(query) ||
            $0.lastName.lowercased().contains(query) ||
            $0.company.lowercased().contains(query) ||
            $0.jobTitle.lowercased().contains(query)
        }
    }

    func addContact(_ contact: Contact) {
        contacts.append(contact)
    }

    func updateContact(_ contact: Contact) {
        guard let index = contacts.firstIndex(where: { $0.id == contact.id }) else { return }
        contacts[index] = contact
    }

    func deleteContact(_ contact: Contact) {
        contacts.removeAll { $0.id == contact.id }
    }

    func delete(at offsets: IndexSet) {
        let sorted = filteredContacts
        offsets.forEach { deleteContact(sorted[$0]) }
    }

    // Combines phone numbers and email addresses; prefers non-empty company/jobTitle.
    func mergeContacts(_ primary: Contact, with secondary: Contact) {
        var merged = primary

        let knownNumbers = Set(merged.phoneNumbers.map { $0.number })
        merged.phoneNumbers += secondary.phoneNumbers.filter { !knownNumbers.contains($0.number) }

        let knownEmails = Set(merged.emailAddresses.map { $0.address })
        merged.emailAddresses += secondary.emailAddresses.filter { !knownEmails.contains($0.address) }

        if merged.company.isEmpty { merged.company = secondary.company }
        if merged.jobTitle.isEmpty { merged.jobTitle = secondary.jobTitle }

        updateContact(merged)
        deleteContact(secondary)
    }

    func exportJSON() -> String {
        let sorted = contacts.sorted { $0.lastName < $1.lastName }
        return JSONExportService.toJSONString(sorted)
    }

    func saveJSONToFile() {
        JSONExportService.saveToFile(exportJSON(), filename: "contacts.json")
    }
}
