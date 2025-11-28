# Gyroscope Feature

This document describes the development and testing of the gyroscope feature.

## Development

The gyroscope feature was developed following the Clean Architecture principles, separating the code into three layers:

*   **Data Layer:** This layer is responsible for providing the gyroscope data. It includes the `GyroscopeSensor` interface and its implementation `AndroidGyroscopeSensor`.
*   **Domain Layer:** This layer contains the business logic of the application. It includes the `GetGyroscopeData` use case and the `GyroscopeRepository` interface.
*   **Presentation Layer:** This layer is responsible for displaying the gyroscope data to the user. It includes the `MainActivity`, `GyroscopeViewModel`, and `GyroscopeViewModelFactory`.

## Testing

To test the gyroscope feature on a live phone, follow these steps:

1.  Clone the repository to your local machine.
2.  Open the project in Android Studio.
3.  Connect your Android device to your computer.
4.  Run the application on your device.
5.  The gyroscope data (X, Y, and Z values) will be displayed on the screen.
