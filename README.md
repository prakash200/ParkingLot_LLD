# Parking Lot (LLD) – Multi-Floor, Multi-Spot-Type

This repository contains a **Low Level Design (LLD)** style implementation of a Parking Lot system in **Java**.

The design supports:

* **Multiple floors**
* Each floor having **multiple parking spots** for **different vehicle types**
* **Spot assignment + Ticket generation** at the **Entry gate**
* **Bill generation** at the **Exit gate**
* A simple **Dashboard/Display** that shows **free slots per floor**
* Pluggable **strategies** for:
  * parking allocation (e.g., nearest-first)
  * billing calculation (e.g., flat-rate, vehicle-based)

---

## Requirements (as implemented)

### 1) Parking lot can have multiple floors
* Implemented via `Models.ParkingLot` containing a list of `Models.ParkingFloor`.

### 2) Each floor should have multiple lots of different vehicle types
* Implemented via `Models.ParkingFloor` maintaining a list of `Models.ParkingSpot`.
* Each `ParkingSpot` has a `spotType` (`Enums.VehicleType`).

### 3) Lots should be assigned based on vehicle type
* Implemented via `Strategies.Parking.ParkingStrategy#assignSpot(...)`.
* Current `VehicleType` enum supports:
  * `SMALL`, `COMPACT`, `LARGE`

### 4) Ticket and lot to be assigned at the entry gate
* Implemented in `Services.ParkingService#park(...)`:
  * allocates a spot using the configured `ParkingStrategy`
  * generates a `Models.Ticket` with entry time + assigned spot + entry gate

### 5) Bill will be generated at the exit gate
* Implemented in `Services.ParkingService#unpark(...)`:
  * calculates fee using the configured `FeeStrategy`
  * generates a `Models.Bill` with exit time + exit gate

### 6) Display showing free slots on each floor
* Implemented via `Services.DashboardService#displayAvailableSpots()`.
* Each `ParkingFloor` keeps a per-vehicle-type count of available spots.

---

## High-level design

### Core Models

| Concept | Class |
|---|---|
| Parking Lot (Singleton) | `Models.ParkingLot` |
| Floor | `Models.ParkingFloor` |
| Spot | `Models.ParkingSpot` |
| Vehicle | `Models.Vehicle` |
| Gate (Entry/Exit) | `Models.Gate` + `Enums.GateType` |
| Ticket | `Models.Ticket` |
| Bill | `Models.Bill` |

### Services

* `Services.ParkingService`
  * `park(vehicle)` → assigns `ParkingSpot` + returns `Ticket`
  * `unpark(ticket)` → computes fee + returns `Bill`
* `Services.DashboardService`
  * keeps the “display counters” in sync by incrementing/decrementing available spot counts per floor

### Strategy Pattern (pluggable behaviors)

#### Parking allocation strategies
* `Strategies.Parking.ParkingStrategy`
  * `NearestFirstStrategy` – picks the first available matching spot while iterating floors from start to end
  * `LongestFirstStrategy` – reverses floor order and picks a matching spot starting from the last floor

#### Billing strategies
* `Strategies.Bill.FeeStrategy`
  * `FlatRateStrategy` – flat per-unit-time rate
  * `VehicleBasedStrategy` – rate depends on `VehicleType`

---

## Project structure

```text
src/
  ParkingLotDemo.java
  Enums/
  Exceptions/
  Models/
  Services/
  Strategies/
    Bill/
    Parking/
```

---

## How to run

### Option A: Run in IntelliJ
1. Open the project in IntelliJ
2. Run `src/ParkingLotDemo.java`

### Option B: Compile and run from CLI (Linux/macOS)

From the repository root:

```bash
# Compile all Java files into ./out
javac -d out $(find src -name "*.java")

# Run the demo
java -cp out ParkingLotDemo
```

The demo (`ParkingLotDemo`) sets up:
* a `ParkingLot` singleton
* entry and exit gates
* two floors with a few spots each
* then parks/unparks vehicles and prints tickets, bills, and dashboard availability.

### Sample output (truncated)

```text
Floor wise parking Spots Availability Count

Floor 1 : {LARGE=1, SMALL=1, COMPACT=1}
Floor 2 : {LARGE=1, SMALL=1, COMPACT=1}

Floor 1 compact vehicle spot is assigned to UP 123456

Ticket
<ticket-id>
Entry Gate
<entry-time>
UP 123456
Floor 1 compact vehicle spot

Bill
<bill-id>
UP 123456
Exit Gate
<amount>
```

---

## Example flow (what happens in the demo)

1. Create `ParkingLot`, add `ParkingFloor`s
2. Add `ParkingSpot`s of types `SMALL/COMPACT/LARGE` to each floor
3. Display initial availability
4. Create a `Vehicle`, call `ParkingService.park(vehicle)` → returns a `Ticket`
5. Dashboard decrements availability for the ticket’s floor + spot type
6. Later call `ParkingService.unpark(ticket)` → returns a `Bill`
7. Dashboard increments availability for that spot type again

---

## Extending the system

Common extension points:

* **Add more vehicle/spot types**
  * update `Enums.VehicleType`
  * add corresponding spots to floors
  * update `VehicleBasedStrategy` rate map if required
* **Add a new parking allocation rule**
  * implement `ParkingStrategy` (e.g., “most free floor”, “random”, “closest to elevator”, etc.)
* **Add a new billing rule**
  * implement `FeeStrategy` (e.g., slab-based, weekend pricing, membership discounts)

---

## Notes / assumptions

* This is an LLD-focused project (data structures + design patterns) and intentionally keeps IO/UI minimal.
* Availability display is based on counters maintained by `DashboardService`.
* Time calculation in current fee strategies is demo-oriented (uses `LocalDateTime.now().getSecond()` differences).

