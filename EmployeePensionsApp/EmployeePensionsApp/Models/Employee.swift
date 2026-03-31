import Foundation

struct Employee: Codable {
    let employeeId: Int
    let firstName: String
    let lastName: String
    let yearlySalary: Double
    let employmentDate: Date
    var pensionPlan: PensionPlan?
}
