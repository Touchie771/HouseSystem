# HouseSystem

A simple Paper plugin that lets admins set, list, remove, and teleport to player houses.

## Features
- **Teleport to your house**: `/house` teleports a player to their saved location.
- **Set house**: `/house add <player> [x y z world]` sets a house at your position or specified coordinates.
- **Remove house**: `/house remove <player>` removes the saved house.
- **List houses**: `/house list` shows all online players with saved houses.
- **Details**: `/house details` prints your saved house location.
- **Persistent storage**: Saves to `plugins/HouseSystem/houses.yml`.

## Commands and Permissions
- **/house** — Teleport to your house
  - Permission: `housesystem.main`
- **/house add <player> [x y z world]** — Set house
  - Permission: `housesystem.add`
- **/house remove <player>** — Remove house
  - Permission: `housesystem.remove`
- **/house list** — List houses
  - Permission: `housesystem.list`
- **/house details** — Show your saved house
  - Permission: `housesystem.details`

## Requirements
- **Server**: Paper `1.21.x`
- **Java**: JDK 21

## Build
```bash
./gradlew clean build
```
- Artifact: `build/libs/HouseSystem-1.0.0.jar`

## Install
1. Copy the built jar to your server `plugins/` folder.
2. Start the server.
3. Configuration/data file will be created at `plugins/HouseSystem/houses.yml`.

## Configuration
- Default data file: `src/main/resources/houses.yml` (empty by default, used as a template).
- Runtime data file: `plugins/HouseSystem/houses.yml`.

## Credits
- **Author**: Touchie771 (`me.touchie771`)
- **Libraries**:
  - [Paper API](https://papermc.io/) — Server platform.
  - [LiteCommands](https://github.com/Rollczi/LiteCommands) — Annotation-based command framework.