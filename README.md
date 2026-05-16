# UAV Telemetry & Flight Simulation Control Center

This project is a real-time Unmanned Aerial Vehicle (UAV) Ground Control Station (GCS) telemetry simulator designed to monitor, process, and log flight-critical data asynchronously without freezing the user interface.

## 🛠️ Technologies & Software Architecture
* **Language & Platform:** Java SE (JDK 25)
* **Database:** MySQL (XAMPP local server execution)
* **GUI Engine:** Java Swing Framework
* **Concurrency & Asynchronous Processing:** Managed via `javax.swing.Timer` to process background data streams seamlessly without blocking the Event Dispatch Thread (EDT).
* **Data Persistence (Logging):** Twin-persistence engine using both local I/O Streams (`FileWriter`) and Relational Database management (JDBC) acting as a flight black-box.

## 📌 Advanced Engineering Highlights
* **Asynchronous Telemetry Stream:** Simulates multi-variable dynamic flight data (Altitude, Airspeed, Battery consumption, Engine Temperature) updating deterministically every 1000ms.
* **Time-Series DB Logging:** Automatically maps and stores every single flight tick directly into a MySQL relational structure with custom auto-generated timestamps for post-flight analysis.
* **Proactive Decision Support System (PDSS):** Triggers real-time graphical UI color state mutations (Red Alert status) when the battery capacity drops below a critical threshold (≤20%).
* **Black-Box Flight Logging:** Automatically writes structured telemetry packets with ISO-compliant timestamps into a local `ucus_loglari.txt` file for post-flight crash-accident analysis.
* **Autonomous Safety Failsafe:** Implements software boundaries where a 0% battery status automatically cuts the stream, stops the background hardware simulation thread, and triggers an autonomous landing emergency notification.
