//
//  ContentView.swift
//  PAMSApp
//
//  Created by Htet Moe Phyu on 3/31/26.
//

import SwiftUI

struct ContentView: View {
    @State private var patients: [Patient] = []
    @State private var statusMessage = ""
    @State private var statusIsError = false

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("PAMS — Patient List")
                .font(.title2)
                .bold()
                .padding()

            List(patients) { patient in
                HStack {
                    VStack(alignment: .leading) {
                        Text("\(patient.firstName) \(patient.lastName)")
                            .font(.headline)
                        Text("DOB: \(formattedDate(patient.dateOfBirth))")
                            .font(.caption)
                            .foregroundStyle(.secondary)
                    }
                    Spacer()
                    Text("Age \(patient.age)")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }
                .padding(.vertical, 4)
            }

            if !statusMessage.isEmpty {
                Text(statusMessage)
                    .font(.footnote)
                    .foregroundStyle(statusIsError ? .red : .green)
                    .padding()
            }
        }
        .onAppear(perform: loadAndProcess)
    }

    private func loadAndProcess() {
        var loaded = PatientDataLoader.load()
        loaded = PatientService.calculateAges(for: loaded)
        loaded = PatientService.sortByAgeDescending(loaded)
        patients = loaded

        do {
            let url = try JSONFileWriter.write(loaded)
            statusMessage = "JSON file created successfully\n\(url.path)"
            statusIsError = false
        } catch {
            statusMessage = "Error writing JSON: \(error.localizedDescription)"
            statusIsError = true
        }
    }

    private func formattedDate(_ date: Date) -> String {
        let f = DateFormatter()
        f.dateFormat = "yyyy-MM-dd"
        return f.string(from: date)
    }
}

#Preview {
    ContentView()
}
