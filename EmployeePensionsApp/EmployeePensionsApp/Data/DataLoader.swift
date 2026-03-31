import Foundation

struct DataLoader {

    static func loadEmployees() -> [Employee] {
        func date(_ string: String) -> Date {
            isoFormatter.date(from: string)!
        }

        return [
            Employee(
                employeeId: 1,
                firstName: "Daniel", lastName: "Agar",
                yearlySalary: 105_945.50,
                employmentDate: date("2025-08-17")
            ),
            Employee(
                employeeId: 2,
                firstName: "Benard", lastName: "Shaw",
                yearlySalary: 197_750.00,
                employmentDate: date("2025-02-03"),
                pensionPlan: PensionPlan(
                    planReferenceNumber: "EX0089",
                    enrollmentDate: date("2026-02-03"),
                    monthlyContribution: 450.00
                )
            ),
            Employee(
                employeeId: 3,
                firstName: "Carly", lastName: "Jones",
                yearlySalary: 842_000.75,
                employmentDate: date("2024-05-16"),
                pensionPlan: PensionPlan(
                    planReferenceNumber: "SM2307",
                    enrollmentDate: date("2025-05-17"),
                    monthlyContribution: 1_555.50
                )
            ),
            Employee(
                employeeId: 4,
                firstName: "Wesley", lastName: "Schneider",
                yearlySalary: 174_500.00,
                employmentDate: date("2025-04-30")
            ),
            Employee(
                employeeId: 5,
                firstName: "Anna", lastName: "Wiltord",
                yearlySalary: 185_750.00,
                employmentDate: date("2025-09-15")
            ),
            Employee(
                employeeId: 6,
                firstName: "Yosef", lastName: "Tesfalem",
                yearlySalary: 100_000.00,
                employmentDate: date("2025-07-31")
            ),
            Employee(
                employeeId: 7,
                firstName: "Johnny", lastName: "Edwards",
                yearlySalary: 95_500.00,
                employmentDate: date("2025-07-09")
            )
        ]
    }

    // Shared yyyy-MM-dd formatter (UTC, POSIX locale)
    static let isoFormatter: DateFormatter = {
        let f = DateFormatter()
        f.dateFormat = "yyyy-MM-dd"
        f.locale = Locale(identifier: "en_US_POSIX")
        f.timeZone = TimeZone(identifier: "UTC")
        return f
    }()
}
