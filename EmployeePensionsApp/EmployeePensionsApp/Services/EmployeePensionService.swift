import Foundation

struct EmployeePensionService {

    private let employees: [Employee]
    private let calendar: Calendar

    init(employees: [Employee]) {
        self.employees = employees
        var cal = Calendar(identifier: .gregorian)
        cal.timeZone = TimeZone(identifier: "UTC")!
        self.calendar = cal
    }

    // MARK: - Quarter Helpers

    func currentQuarterStart() -> Date { quarterStart(for: Date()) }
    func currentQuarterEnd()   -> Date { quarterEnd(for: Date()) }

    func nextQuarterStart() -> Date {
        calendar.date(byAdding: .month, value: 3, to: currentQuarterStart())!
    }
    func nextQuarterEnd() -> Date { quarterEnd(for: nextQuarterStart()) }

    private func quarterStart(for date: Date) -> Date {
        let month = calendar.component(.month, from: date)
        let year  = calendar.component(.year,  from: date)
        let startMonth = ((month - 1) / 3) * 3 + 1
        return calendar.date(from: DateComponents(timeZone: TimeZone(identifier: "UTC"),
                                                  year: year, month: startMonth, day: 1))!
    }

    private func quarterEnd(for date: Date) -> Date {
        // Last day = one day before the start of the following quarter
        let nextStart = calendar.date(byAdding: .month, value: 3, to: quarterStart(for: date))!
        return calendar.date(byAdding: .day, value: -1, to: nextStart)!
    }

    // MARK: - Reports

    func allEmployeesSorted() -> [Employee] {
        employees.sorted {
            $0.yearlySalary != $1.yearlySalary
                ? $0.yearlySalary > $1.yearlySalary
                : $0.lastName < $1.lastName
        }
    }

    func currentQuarterlyEnrollees() -> [Employee] {
        enrollees(from: currentQuarterStart(), to: currentQuarterEnd())
            .sorted { $0.employmentDate > $1.employmentDate }
    }

    func nextQuarterlyEnrollees() -> [Employee] {
        enrollees(from: nextQuarterStart(), to: nextQuarterEnd())
            .sorted {
                $0.employmentDate != $1.employmentDate
                    ? $0.employmentDate > $1.employmentDate
                    : $0.yearlySalary < $1.yearlySalary
            }
    }

    private func enrollees(from start: Date, to end: Date) -> [Employee] {
        employees.filter { emp in
            guard emp.pensionPlan == nil         else { return false }
            guard emp.yearlySalary >= 100_000    else { return false }
            let anniversary = calendar.date(byAdding: .year, value: 1, to: emp.employmentDate)!
            return anniversary >= start && anniversary <= end
        }
    }

    // MARK: - JSON Encoder

    static func makeEncoder() -> JSONEncoder {
        let encoder = JSONEncoder()
        encoder.outputFormatting = [.prettyPrinted]
        encoder.dateEncodingStrategy = .formatted(DataLoader.isoFormatter)
        return encoder
    }

    // MARK: - Print Report

    func printReport() {
        let encoder = Self.makeEncoder()

        func jsonString<T: Encodable>(_ value: T) -> String {
            guard let data = try? encoder.encode(value),
                  let str  = String(data: data, encoding: .utf8) else { return "[]" }
            return str
        }

        print("=== All Employees ===")
        print(jsonString(allEmployeesSorted()))

        print("\n=== Current Quarterly Enrollees ===")
        print(jsonString(currentQuarterlyEnrollees()))

        print("\n=== Next Quarterly Upcoming Enrollees ===")
        print(jsonString(nextQuarterlyEnrollees()))
    }
}
