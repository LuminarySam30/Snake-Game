# Snake Game

A classic Snake game implementation in Java using Swing.

## Features
- **Classic Gameplay**: Navigate the snake to eat food and grow longer.
- **High Scores**: Tracks and saves your highest score locally.
- **Game States**: Includes Menu, Playing, Pause, and Game Over screens.
- **Smooth Controls**: Responsive keyboard controls for a seamless experience.

## Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven 3.6+

## How to Run

### Using Maven
1. Open your terminal in the project directory.
2. clean and install the project:
   ```bash
   mvn clean install
   ```
3. Run the game:
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.App"
   ```

### Running the JAR
After building with `mvn package`, you can run the generated JAR file from the `target` directory:
```bash
java -jar target/SnakeGameRecitation-1.0-SNAPSHOT.jar
```
*(Note: You might need to configure the maven-jar-plugin to make the JAR executable, or use the maven-shade-plugin/assembly-plugin. If running the JAR directly doesn't work, stick to the `mvn exec:java` command).*

## Controls

### Menu
- **UP / DOWN Arrows**: Navigate menu options.
- **ENTER**: Select an option (Start Game / Quit).

### In-Game
- **Arrow Keys**: Move the snake (Up, Down, Left, Right).
- **P**: Pause / Unpause the game.

## Project Structure
- `src/main/java/org/example`
  - `App.java`: Main entry point.
  - `controller`: Handles game states and input.
  - `model`: Game logic (Snake, Food, HighScore).
  - `view`: Swing components and rendering.
