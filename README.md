# Android Native Features Showcase

A collection of Android apps demonstrating native Android APIs using clean architecture principles.

## Cloud-Synced Field Recorder

An audio recording app that saves recordings locally and uploads them to Cloudinary.

### Features

- **Audio Recording**: Record audio using the device's microphone
- **Local Storage**: Recordings are stored in a local SQLite database using Room
- **Cloud Sync**: Automatic background upload to Cloudinary using WorkManager
- **Clean Architecture**: Follows MVVM pattern with Repository pattern
- **Real-time UI**: Live updates to the recording list using LiveData

### Architecture

```
┌─────────────────┐
│   UI Layer      │
│  (Activities)   │
└─────────┬───────┘
          │
┌─────────▼───────┐
│ ViewModel Layer │
│ (RecorderVM)    │
└─────────┬───────┘
          │
┌─────────▼───────┐
│ Repository      │
│ (RecordingRepo) │
└─────────┬───────┘
          │
┌─────────▼───────┐
│   Data Layer    │
│ (Room Database)│
└─────────────────┘
```

### Data Flow

1. **Recording**: User taps record → `AudioRecordingService` starts recording
2. **Save**: Recording stops → Service saves metadata to Room database
3. **Upload**: WorkManager queues `UploadWorker` → Uploads to Cloudinary
4. **Update**: Success → Worker marks recording as uploaded in database
5. **UI**: LiveData automatically updates the recording list

### Key Components

- **AudioRecordingService**: Handles audio recording in the foreground
- **UploadWorker**: Performs background uploads to Cloudinary
- **RecordingEntity**: Database entity for recording metadata
- **RecordingRepository**: Manages data operations between UI and database
- **RecorderViewModel**: Exposes data to UI using LiveData

### Setup

1. Add your Cloudinary credentials to `local.properties`:
   ```properties
   CLOUDINARY_CLOUD_NAME=your_cloud_name
   CLOUDINARY_API_KEY=your_api_key
   CLOUDINARY_API_SECRET=your_api_secret
   ```

2. Create an upload preset in Cloudinary named "recorded"

### Permissions

The app requires:
- `RECORD_AUDIO`: To record audio
- `INTERNET`: To upload to Cloudinary
- `FOREGROUND_SERVICE`: For background recording
- `POST_NOTIFICATIONS`: For Android 13+ notifications

## Other Features

### Land Mapper
A simple mapping tool (previous implementation)

### Sensor Demos
- Accelerometer data visualization
- Gyroscope data visualization

## Build Requirements

- Android Studio Hedgehog | 2023.1.1 or later
- Android Gradle Plugin 8.0
- Compile SDK: 34
- Min SDK: 24
