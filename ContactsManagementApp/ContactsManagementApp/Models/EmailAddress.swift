import Foundation

struct EmailAddress: Identifiable, Codable, Equatable {
    var id: UUID
    var address: String
    var label: String

    init(id: UUID = UUID(), address: String, label: String) {
        self.id = id
        self.address = address
        self.label = label
    }
}
