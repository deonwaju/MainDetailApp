MainDetail App with MVVM clean architecture

# Product List App

A Jetpack Compose Android app for browsing and searching products. This project demonstrates a modern Android app 
architecture with MVVM, Hilt dependency injection, Kotlin coroutines, Room Database, Datastore pref, Unit Tests and Jetpack Compose for UI. The app fetches product 
data from a repository and displays it in a list or grid view, with search functionality for filtering products by name or color.

Just a brief description below:

## What should the UI look like? 🎨
[Screen Recording 2024-11-07 at 02.31.59.mov](..%2F..%2FDesktop%2FScreen%20Recording%202024-11-07%20at%2002.31.59.mov)

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
- **Hilt**: A modern dependency injection framework for Android apps.
- **Coroutines**: For asynchronous programming and managing background tasks.
- **StateFlow**: A reactive API for managing UI state in ViewModels.
- **Retrofit**: For networking and API calls (if applicable).
- **Material Design 3**: For modern, consistent UI elements and design patterns.
- **Room**: For local database storage (if applicable).

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

### Also TO-DO
- Complete Unit tests
