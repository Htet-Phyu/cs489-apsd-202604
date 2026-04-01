import Foundation

struct JSONFileWriter {

    @discardableResult
    static func write(_ patients: [Patient]) throws -> URL {
        let encoder = JSONEncoder()
        encoder.outputFormatting = .prettyPrinted

        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        encoder.dateEncodingStrategy = .formatted(dateFormatter)

        let data = try encoder.encode(patients)

        let documents = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
        let outputDir = documents.appendingPathComponent("output")
        try FileManager.default.createDirectory(at: outputDir, withIntermediateDirectories: true)

        let fileURL = outputDir.appendingPathComponent("patients.json")
        try data.write(to: fileURL)
        return fileURL
    }
}
