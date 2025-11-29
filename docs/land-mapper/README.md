# Land Mapper (Solution Day 1)

## Description
A solution-centric feature to map land areas using GPS.

## Features Implemented
- Real-time location tracking
- Recording tracks (start/stop)
- Saving to local database
- Viewing live stats (distance, time, points)

## Architecture
Clean Architecture (Domain, Data, Presentation).

## Tech Stack
- Room Database
- Fused Location Provider
- ViewModel
- LiveData

## How to Test
1. Open the app.
2. Grant location permissions.
3. Click "Start Recording" to begin tracking.
4. Walk around to generate points.
5. Click "Stop Recording" to save the track.
6. Observe stats updating in real-time.

## Note
This is the initial "Day 1" implementation focusing on the core recording engine. Visualization on a map (Google Maps) and export features will follow.
