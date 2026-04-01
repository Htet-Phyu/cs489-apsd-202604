import Foundation

struct Patient: Codable, Identifiable {
    let patientId: Int
    let firstName: String
    let lastName: String
    let phoneNumber: String?
    let email: String?
    let mailingAddress: String?
    let dateOfBirth: Date
    var age: Int

    var id: Int { patientId }
}
