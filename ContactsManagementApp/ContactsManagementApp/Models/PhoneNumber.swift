import Foundation

struct PhoneNumber: Identifiable, Codable, Equatable {
    var id: UUID
    var number: String
    var label: String

    init(id: UUID = UUID(), number: String, label: String) {
        self.id = id
        self.number = number
        self.label = label
    }
}
