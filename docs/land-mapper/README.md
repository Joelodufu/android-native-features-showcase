# Land Mapper (Solution)

A practical, field‑ready Android app to trace land parcel boundaries and save waypoints/photos, then export the result for sharing or use in Google My Maps and GIS tools.

## Goals
- Record a path or polygon boundary while walking/inspecting a site.
- Drop waypoints with notes and geo‑tagged photos.
- Export data as GeoJSON/KML/CSV for sharing/importing.
- Work online‑first with basic offline caching (can evolve to offline‑first).

## Key Android APIs Used
- **Location**: Fused Location Provider (Google Play Services) with high‑accuracy updates.
- **Foreground Service + Notifications**: continuous recording and user awareness.
- **Storage**: Room (SQLite) for tracks/points/photos, with scoped storage for media.
- **Camera**: capture photos tied to waypoints (geo‑tagged metadata).
- **Permissions Runtime**: location, camera, notifications, media storage.
- **UI**: Google Maps SDK map display, markers, polylines, polygons.

## Clean Architecture Shape
- **Domain**
  - Entities: `Track`, `TrackSegment`, `TrackPoint` (lat, lon, alt, time, accuracy), `Waypoint` (note, photoUri), `ExportPayload`
  - Repositories (interfaces): `LocationRepository`, `TrackRepository`, `MediaRepository`, `ExportRepository`
  - Use Cases: `StartRecordingTrack`, `StopRecordingTrack`, `AddTrackPoint`, `AddWaypointWithPhoto`, `GetActiveTrack`, `ExportAsGeoJSON`, `LoadTracks`
- **Data**
  - Room: DAOs and entities for Track/Point/Waypoint
  - Location datasource: `FusedLocationProviderClient` wrapper
  - Media datasource: scoped storage + EXIF helper
  - Export serializers: GeoJSON/KML/CSV
- **Presentation**
  - ViewModels: `RecordingViewModel`, `TracksViewModel`
  - UI: `MapActivity`/`Fragment` (Google Map), controls: Start/Stop, Add Waypoint, Export

## MVP Scope (3 daily increments)
- **Day 1 (this branch)**:
  - Map screen with live location dot and camera move to user location.
  - Start/Stop recording controls; foreground notification when recording.
  - Room schema + repository interfaces; stub implementations wired to ViewModel.
  - README + device testing steps (below).
- **Day 2**:
  - Persist points while recording; draw polyline in real time.
  - Waypoint creation with notes; attach camera photo to waypoint.
  - Tracks list screen with basic details.
- **Day 3**:
  - Export to GeoJSON and KML; share via Android Sharesheet.
  - Basic offline map fallback and polish (accuracy filters, battery tips).

## Device Testing (Day 1)
1. **Get a Google Maps API key** (Android SDK Maps) and add it:
   - In your project, create `local.properties` (if not present) and add:
     `MAPS_API_KEY=YOUR_ANDROID_MAPS_API_KEY`
   - The app will read this at build time via manifest placeholders.
2. **Build & run** on a real device (Android 8.0+ recommended).
3. **Grant permissions** when prompted: Location (Precise), Notifications, Camera (later).
4. **On the map screen**:
   - Confirm the blue location dot appears and follows you.
   - Tap **Start** to begin a recording; verify a persistent notification shows.
   - Tap **Stop** to end recording; notification disappears.

## Project Layout (planned additions)
- `app/` (root application module)
- `feature-land-mapper/`
  - `domain/`, `data/`, `presentation/`
  - `ui/MapActivity` or `MapFragment`
  - `di/` (simple provider wiring)
  - `docs/` (feature documentation)

## Compliance & Notes
- Location runs in a foreground service with a visible notification.
- No covert behavior; user is always informed while recording.
- Battery/Accuracy tips documented alongside the export feature.

## Configuration
- Min SDK: 23–24 (finalize after Day 1 wiring)
- Target SDK: current stable
- Build tools: Gradle 8+, AGP latest stable

## Next Steps
- Implement the feature module skeleton and wire Fused Location updates into the foreground service.
- Commit code for map screen and recording controls.
- Begin persisting track points and drawing the polyline in real time.
