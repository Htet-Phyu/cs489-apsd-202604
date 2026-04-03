import Foundation

struct JSONExportService {

    static func toJSONString(_ contacts: [Contact]) -> String {
        let encoder = JSONEncoder()
        encoder.outputFormatting = [.prettyPrinted, .sortedKeys]
        guard
            let data = try? encoder.encode(contacts),
            let json = String(data: data, encoding: .utf8)
        else { return "[]" }
        return json
    }

    // Writes JSON to the app's Documents directory and prints the path to the console.
    // SCREENSHOT: After tapping "Export JSON", check Xcode console for the saved file path.
    static func saveToFile(_ json: String, filename: String) {
        guard let docDir = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first else { return }
        let fileURL = docDir.appendingPathComponent(filename)
        do {
            try json.write(to: fileURL, atomically: true, encoding: .utf8)
            print("[JSONExportService] Saved to: \(fileURL.path)")
        } catch {
            print("[JSONExportService] Error: \(error.localizedDescription)")
        }
    }
}
