import Foundation

struct ContactService {
    func loadSampleContacts() -> [Contact] {
        [
            Contact(
                firstName: "David",
                lastName: "Sanger",
                company: "Argos LLC",
                jobTitle: "Sales Manager",
                phoneNumbers: [
                    PhoneNumber(number: "240-133-0011", label: "Home"),
                    PhoneNumber(number: "240-112-0123", label: "Mobile")
                ],
                emailAddresses: [
                    EmailAddress(address: "dave.sang@gmail.com", label: "Home"),
                    EmailAddress(address: "dsanger@argos.com", label: "Work")
                ]
            ),
            Contact(
                firstName: "Carlos",
                lastName: "Jimenez",
                company: "Zappos",
                jobTitle: "Director"
            ),
            Contact(
                firstName: "Ali",
                lastName: "Gafar",
                company: "BMI Services",
                jobTitle: "HR Manager",
                phoneNumbers: [
                    PhoneNumber(number: "412-116-9988", label: "Work")
                ],
                emailAddresses: [
                    EmailAddress(address: "ali@bmi.com", label: "Work")
                ]
            )
        ]
    }
}
