import Foundation

struct PensionPlan: Codable {
    let planReferenceNumber: String
    let enrollmentDate: Date
    let monthlyContribution: Double
}
