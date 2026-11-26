Extend your Android application from previous homeworks by implementing MVVM architecture, persistent local storage, and more advanced application functionality.
All features from previous homeworks must remain functional.
Display a collection of custom objects using `LazyColumn` or `LazyGrid` with stable item keys.
Implement list and detail screens with Navigation Compose.
Provide forms for creating and editing items with appropriate input validation.
Allow users to delete items with a confirmation dialog.
Implement at least one additional feature involving multiple user interactions, state changes, and persistent data.
Examples:
    Multi-step workflows with status transitions and conditions.
    Task dependencies or prerequisite systems.
    Progress tracking with statistics and history.
    Inventory or resource management with transactions.
    Undo/restore functionality or activity history.
    User-defined collections with relationships between items.
    The additional feature must be fully functional and integrated into the application's UI. Basic search, filtering, and sorting will not count
    Handle empty states and provide clear feedback for user actions.
Use `ViewModel` and `StateFlow` to manage and expose UI state.
Collect state in Compose using `collectAsStateWithLifecycle()`.
Separate UI components, ViewModels, Repositories, and data sources.
Use state hoisting and reusable Composables where appropriate.
Handle user actions through ViewModel methods rather than directly modifying application data in Composables.
Integrate Room for persistent local storage. Create at least one `Entity` and `DAO`.
Implement complete CRUD operations: create, read, update, and delete.
Implement a Repository that provides access to the database.
Use Room `Flow` to observe stored data and automatically update the Compose UI when data changes.
Ensure created and modified data persists after restarting the application.
Connect all CRUD operations and advanced features to actual UI interactions.
Ensure changes made on one screen are reflected on other screens without manual refreshing.
Keep application data consistent across navigation and configuration changes.
Database operations must not exist only as unused functions or hardcoded demonstrations.