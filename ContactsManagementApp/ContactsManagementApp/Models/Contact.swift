import Foundation

struct Contact: Identifiable, Codable, Equatable {
    var id: UUID
    var firstName: String
    var lastName: String
    var company: String
    var jobTitle: String
    var phoneNumbers: [PhoneNumber]
    var emailAddresses: [EmailAddress]

    init(
        id: UUID = UUID(),
        firstName: String,
        lastName: String,
        company: String = "",
        jobTitle: String = "",
        phoneNumbers: [PhoneNumber] = [],
        emailAddresses: [EmailAddress] = []
    ) {
        self.id = id
        self.firstName = firstName
        self.lastName = lastName
        self.company = company
        self.jobTitle = jobTitle
        self.phoneNumbers = phoneNumbers
        self.emailAddresses = emailAddresses
    }

    var fullName: String { "\(firstName) \(lastName)" }
}
