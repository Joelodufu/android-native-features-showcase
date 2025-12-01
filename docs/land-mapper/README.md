# Land Mapper (Solution Day 3)

## Features Added (Day 3):
- Background Tracking: Migrated recording logic to a `ForegroundService` (`LocationTrackingService`).
- Resilience: Tracking continues even if the app is minimized or the screen is turned off.
- Notification: A persistent notification indicates when tracking is active.

## Tech Stack Additions:
- Android Services
- Notification Channels
- Foreground Service permissions

## How to Test (Updated):
1. Open the app.
2. Click "Start Recording".
3. **Minimize the app** (go to home screen).
4. Notice the "Tracking Active" notification in the status bar.
5. Walk around.
6. Open the app again and see the path updated on the map.
7. Click "Stop Recording".

## Note:
This completes the core tracking reliability. The app is now field-ready for basic surveying tasks.
