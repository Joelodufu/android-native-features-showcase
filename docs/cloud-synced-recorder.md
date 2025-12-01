# Cloud-Synced Field Recorder

## Project Overview
The Cloud-Synced Field Recorder is an Android application feature that allows users to record audio in the field and automatically sync the recordings to the cloud for backup and easy access across devices. This project showcases native Android capabilities integrated with cloud services to provide seamless audio recording and synchronization.

## Architecture
- **Android Native App:** Handles audio recording, local storage, and user interface.
- **Cloud Storage:** Syncs recorded audio files to a cloud service (e.g., Firebase Storage, AWS S3).
- **Sync Manager:** Manages upload/download of audio files between local device and cloud.
- **Database:** Stores metadata about recordings and sync status (e.g., Firebase Firestore, SQLite).

## Features
- High-quality audio recording with pause/resume functionality.
- Automatic background sync of recordings to the cloud.
- Offline support with queued uploads when connectivity is restored.
- Listing and playback of synced recordings within the app.
- Metadata tagging for recordings (e.g., location, timestamp).

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/Joelodufu/android-native-features-showcase.git
   cd android-native-features-showcase
   ```
2. Open the project in Android Studio.
3. Configure cloud service credentials:
   - For Firebase, add `google-services.json` to the `app/` directory.
   - For AWS, configure credentials in the app or use AWS Amplify.
4. Build and run the app on an Android device or emulator.
5. Grant necessary permissions for audio recording and storage.

## API Usage Details
- **Recording API:** Uses Android's `MediaRecorder` for audio capture.
- **Cloud Sync API:** Utilizes cloud SDKs (Firebase Storage/AWS S3) for file upload/download.
- **Database API:** Uses Firestore or SQLite for managing recording metadata and sync status.
- **Sync Manager:** Handles network state monitoring and sync queue management.

For detailed code examples and integration, refer to the source code in the `cloud-synced-recorder` module within the repository.