# Land Mapper (Solution Day 2)

## Features Added (Day 2):
- Google Maps Integration: Visualizes the tracked path on a map using a Polyline.
- Export Functionality: Converts the recorded track to GeoJSON format and allows sharing via system intent (email, drive, etc.).

## Tech Stack Additions:
- Google Maps SDK for Android
- Intent system

## How to Test (Updated):
1. Open the app.
2. Grant location permissions.
3. The map should appear showing your current location (blue dot).
4. Click "Start Recording" and walk around.
5. A red line (polyline) will be drawn on the map following your path.
6. Click "Stop Recording".
7. Click "Export" to share the track as a GeoJSON text snippet.

## Note:
Day 2 completes the core functional MVP. Future improvements could include background service refinement or UI polish.
