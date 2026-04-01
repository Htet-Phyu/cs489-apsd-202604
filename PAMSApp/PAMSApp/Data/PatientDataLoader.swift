import Foundation

struct PatientDataLoader {
    static func load() -> [Patient] {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd"

        func date(_ string: String) -> Date {
            formatter.date(from: string)!
        }

        return [
            Patient(patientId: 1, firstName: "Daniel",  lastName: "Agar",       phoneNumber: "(641) 123-0009", email: "dagar@m.as",       mailingAddress: "1 N Street",     dateOfBirth: date("1987-01-19"), age: 0),
            Patient(patientId: 2, firstName: "Ana",     lastName: "Smith",       phoneNumber: nil,              email: "amsith@te.edu",    mailingAddress: nil,              dateOfBirth: date("1948-12-05"), age: 0),
            Patient(patientId: 3, firstName: "Marcus",  lastName: "Garvey",      phoneNumber: "(123) 292-0018", email: nil,                mailingAddress: "4 East Ave",     dateOfBirth: date("2001-09-18"), age: 0),
            Patient(patientId: 4, firstName: "Jeff",    lastName: "Goldbloom",   phoneNumber: "(999) 165-1192", email: "jgold@es.co.za",   mailingAddress: nil,              dateOfBirth: date("1995-02-28"), age: 0),
            Patient(patientId: 5, firstName: "Mary",    lastName: "Washington",  phoneNumber: nil,              email: nil,                mailingAddress: "30 W Burlington", dateOfBirth: date("1932-05-31"), age: 0),
        ]
    }
}
