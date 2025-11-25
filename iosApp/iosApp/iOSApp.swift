import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinHelper.shared.initialize()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}