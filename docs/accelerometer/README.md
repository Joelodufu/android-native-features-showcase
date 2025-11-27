# Accelerometer Feature

This document describes the development and testing of the accelerometer feature.

## Development

The accelerometer feature was developed following the Clean Architecture principles, separating the code into three layers:

*   **Data Layer:** This layer is responsible for providing the accelerometer data. It includes the `AccelerometerSensor` interface and its implementation `AndroidAccelerometerSensor`.
*   **Domain Layer:** This layer contains the business logic of the application. It includes the `GetAccelerometerData` use case and the `AccelerometerRepository` interface.
*   **Presentation Layer:** This layer is responsible for displaying the accelerometer data to the user. It includes the `MainActivity`, `AccelerometerViewModel`, and `AccelerometerViewModelFactory`.

## Testing

To test the accelerometer feature on a live phone, follow these steps:

1.  Clone the repository to your local machine.
2.  Open the project in Android Studio.
3.  Connect your Android device to your computer.
4.  Run the application on your device.
5.  The accelerometer data (X, Y, and Z values) will be displayed on the screen.
