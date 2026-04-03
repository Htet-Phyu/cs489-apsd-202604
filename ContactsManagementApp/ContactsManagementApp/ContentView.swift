//
//  ContentView.swift
//  ContactsManagementApp
//
//  Created by Htet Moe Phyu on 4/3/26.
//

import SwiftUI

// SCREENSHOT: Take a screenshot of this screen running in the simulator for "app_running_contacts_list.png"
struct ContentView: View {
    @StateObject private var viewModel = ContactViewModel()

    var body: some View {
        ContactListView()
            .environmentObject(viewModel)
    }
}

#Preview {
    ContentView()
}
