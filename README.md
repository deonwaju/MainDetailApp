MainDetail App with MVVM clean architecture

# Product List App

A Jetpack Compose Android app for browsing and searching products. This project demonstrates a modern Android app 
architecture with MVVM, Hilt dependency injection, Kotlin coroutines, Room Database, Datastore pref, Unit Tests and Jetpack Compose 
for UI with Compose Ui tests. The app fetches product data from a repository and displays it in a list or grid view, 
with search functionality for filtering products by name or color.

Just a brief description below:

## What should the UI look like? 🎨

- Screen Recording
  
https://github.com/user-attachments/assets/98042613-c73b-49d9-a111-0130cac6eebb

- Search

https://github.com/user-attachments/assets/b7062d5e-a264-440b-826a-216be6d29c12

- Screenshots
  
![Screenshot 2024-11-07 at 11 49 57](https://github.com/user-attachments/assets/267b33ab-3a6b-4213-92c2-4f47ec92dfd8) ![Screenshot 2024-11-07 at 11 50 04](https://github.com/user-attachments/assets/f9e8eadb-b31a-467a-83df-ec918551d028) ![Screenshot 2024-11-07 at 11 50 14](https://github.com/user-attachments/assets/8b11a8d6-09b1-4c52-a759-b4b56bbf9bd8) ![Screenshot 2024-11-07 at 11 50 20](https://github.com/user-attachments/assets/a0014e7d-334d-494a-bd28-174c8bec8282) ![Screenshot 2024-11-07 at 11 49 35](https://github.com/user-attachments/assets/e1df159f-e9af-4ae3-a43f-9f86da3361f0) ![Screenshot 2024-11-07 at 11 49 44](https://github.com/user-attachments/assets/675e7f67-898a-408b-bfa5-854010e538e8) <img width="306" alt="Screenshot 2024-11-07 at 21 04 55" src="https://github.com/user-attachments/assets/95543038-905f-4ff2-86a9-c8f6db1d4ce2"> <img width="311" alt="Screenshot 2024-11-07 at 21 05 04" src="https://github.com/user-attachments/assets/2536eb8d-17d8-42d1-adc5-700f679dc570">

## Features

- **Product List Display**: Shows products in a grid or list view with toggling.
- **Search Functionality**: Search products by name or color with instant filtering.
- **Error and Loading States**: Consistent error and loading screens for smooth user experience.
- **Hilt Dependency Injection**: Simplifies injection of dependencies like ViewModel and UseCases.

## Screens

- **Product List Screen**: Shows a list or grid of products.
- **Product Details Screen**: Display detailed product information (navigated from the product list).
- **Settings Screen**: For changing UI theme.
- **Loading and Error Screens**: Reusable screens to handle loading and error states gracefully.

## Architecture

The app follows **MVVM architecture** with:
- **ViewModel** for handling business logic and exposing UI states.
- **StateFlow** for UI state management and updates.
- **UseCases** for encapsulating business logic.

## Tech Stack

- **Kotlin**: The primary programming language for the app.
- **Jetpack Compose**: For building native UIs declaratively with Kotlin.
- **MVVM Architecture**: Model-View-ViewModel architecture for better separation of concerns.
- **Clean Architecture**: Separation of concerns with layers like domain, data, and presentation.
- **Modular Design**: Feature-based modular organization for better scalability and maintainability.
- **Repository Pattern**: For abstracting data sources and providing a clean API to interact with data.
- **Single Source of Truth**: A single source of truth for data to ensure consistency and reliability.
- **Hilt**: A modern dependency injection framework for Android apps.
- **Coroutines**: For asynchronous programming and managing background tasks.
- **StateFlow**: A reactive API for managing UI state in ViewModels.
- **Retrofit**: For networking and API calls (if applicable).
- **Material Design 3**: For modern, consistent UI elements and design patterns.
- **Room**: For local database storage (if applicable).
- **Datastore**: For storing key-value pairs in a data store (if applicable).
- **Unit Tests**: For testing individual components and logic.
- **Compose UI Tests**: For testing UI components and interactions.

## Project Structure

├── ui/ │ ├── ProductListScreen.kt # Main screen showing list or grid of products │ ├── ProductDetailsScreen.kt # Product details screen (navigated from list) │ ├── LoadingScreen.kt # Loading screen with centered indicator │ ├── ErrorScreen.kt # Error screen with message and retry option ├── viewmodel/ │ ├── ProductsListViewModel.kt # ViewModel for managing product list state and search ├── di/ │ ├── AppModule.kt # Hilt module for dependency injection setup ├── data/ │ ├── Product.kt # Product data model │ ├── ProductRepository.kt # Repository for fetching product data ├── usecase/ │ ├── GetAllProductsListUseCase.kt # Use case for fetching product list

## 📦 Module Structure

The application is organized into feature-based modules, where each module is dedicated to a specific feature or functionality. This modular approach keeps related components together, enhancing code organization and maintaining separation of concerns.

### Feature-Based Module Organization

#### Feature Modules:
Each feature module is self-contained and includes its own domain and data layers, ensuring that all necessary logic for the feature resides within its dedicated module. This approach:

- Promotes **scalability** by allowing features to evolve independently.
- Enhances **maintainability** since each module can be developed, tested, and modified without directly impacting other features.
- Facilitates **reusability**, as individual modules can be reused across different parts of the application or even in other projects.

#### Shared Modules:
In addition to feature modules, the application includes shared modules that handle common responsibilities across multiple features. These shared modules include:

- **Local Room Database**: A centralized configuration and setup for the Room database, used for managing persistent data across different features.
- **Test Utilities**: A set of shared utilities and configurations that standardize and streamline the testing process across the application.
- **Networking**: A unified setup for handling network operations, ensuring consistency in how data is fetched, sent, and processed across the application.
- **Design System**: A shared module that defines common design patterns, components, and styles, ensuring a cohesive and consistent user interface across the app.

This modular design approach allows for a more structured, maintainable, and scalable codebase, where components are logically grouped based on their functionality. It enables faster development cycles, simplifies testing, and ensures the application is easily extendable in the future.

## TO-DO
If save to favourite feature was part of the exercise, I would have implemented it as follows:
- Add a new screen for displaying favourite products.
- Implement a local database using Room to store favourite products.
- Add a button on the product details screen to save the product to favourites.
- Implement the logic to save and retrieve favourite products from the local database.
- Update the UI to display favourite products on the favourites screen.
- Add the ability to remove products from the favourites list.
- Implement the necessary logic to handle removing products from the favourites list.
- Add analytics tracking.

## Getting Started

To run this project on your local machine:

1. Clone the repository: `git clone https://github.com/deonwaju/MainDetailApp`
2. Open the project in Android Studio.
3. Sync the project with Gradle to resolve dependencies.
4. Run the app on an emulator or Android device.

---

Thank you for checking out the **Product List App**! We hope you find this a useful example of a modern Android architecture built with MVVM, Clean Architecture, and Jetpack Compose.






