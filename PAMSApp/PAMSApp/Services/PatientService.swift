import Foundation

struct PatientService {

    static func calculateAges(for patients: [Patient]) -> [Patient] {
        let calendar = Calendar.current
        let today = Date()
        return patients.map { patient in
            var p = patient
            p.age = calendar.dateComponents([.year], from: patient.dateOfBirth, to: today).year ?? 0
            return p
        }
    }

    static func sortByAgeDescending(_ patients: [Patient]) -> [Patient] {
        patients.sorted { $0.age > $1.age }
    }
}
