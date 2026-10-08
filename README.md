# DesignPatternGameProject
//# 🎮 Design Pattern Game Project

A Java-based brick-breaker style game built to demonstrate key Object-Oriented Programming (OOP) principles and Software Design Patterns.

---

## 📌 Features

- **Classic Brick-Breaker Gameplay:** Control the paddle, bounce the ball, and destroy all bricks on the board.
- **Multiple Brick Types:** Includes normal bricks, strong/multi-hit bricks, and customized brick behaviors.
- **Level Design & Generation:** Dynamic grid and map layout generation for flexible level setup.
- **Design Pattern Architecture:** Built with expandable structural and creational patterns for clean code maintenance.

---

## 🛠️ Design Patterns Applied

This project incorporates design patterns to ensure scalability and clean architecture:

* **Factory Method Pattern:** Used for instantiating different types of bricks (`BrickCreator`, `NormalBrickCreator`, `StrongBrickCreator`).
* **Builder / Director Pattern:** Facilitates level setup and structured map creation (`LevelBuilder`, `LevelDirector`).
* **Observer / Listener Pattern:** Handles user keyboard inputs (`keyPressed` events) and game render updates.

---

## 📁 Project Structure

```text
DesignProject/
├── src/
│   ├── Main.java                   # Application Entry Point
│   ├── TTT.java                    # Game Frame & Main Window Controller
│   ├── Gameplay.java               # Core Game Loop, Graphics & Physics Engine
│   ├── Menu.java                   # Main Menu UI
│   ├── MapGenerator.java           # Grid Layout & Render Manager
│   ├── Level.java                  # Level Properties & State
│   ├── LevelBuilder.java           # Level Construction Logic
│   ├── LevelDirector.java          # Manages Level Building Sequence
│   ├── Brick.java                  # Abstract / Base Class for Bricks
│   ├── BrickCreator.java           # Factory Interface / Base Class for Bricks
│   ├── NormalBrick.java            # Standard Brick Implementation
│   ├── NormalBrickCreator.java     # Factory for Standard Bricks
│   ├── StrongBrick.java            # Reinforced Brick Implementation
│   └── StrongBrickCreator.java     # Factory for Reinforced Bricks
├── build.xml                       # Ant Build Script
└── README.md                       # Project Documentation
