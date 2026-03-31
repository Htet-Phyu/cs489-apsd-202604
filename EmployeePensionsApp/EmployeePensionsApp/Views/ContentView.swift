//
//  ContentView.swift

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack(spacing: 16) {
            Image(systemName: "person.3.fill")
                .imageScale(.large)
                .foregroundStyle(.tint)
            
            Text("Employee Pensions App")
                .font(.headline)
            
            Text("Check the Xcode console for report output.")
                .font(.subheadline)
                .foregroundStyle(.secondary)
                .multilineTextAlignment(.center)
        }
        .padding()
        .onAppear {
            let service = EmployeePensionService(employees: DataLoader.loadEmployees())
            service.printReport()
        }
    }
}

#Preview {
    ContentView()
}
