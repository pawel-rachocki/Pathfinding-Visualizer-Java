# 🔍 PathFinder Visualization in JavaFX

A simple and interactive visualization tool for **Dijkstra** and **A\*** pathfinding algorithms using JavaFX. Customize the grid, set start/end points, draw obstacles, and watch how the algorithm finds the shortest path!

---

## 🧠 Features

- 🟢 Set **start** and 🔴 **end** nodes
- ⬛ Add/remove **obstacles**
- ⚙️ Choose between **Dijkstra** and **A\*** algorithm
- 🎯 Real-time **visualization** of search and path
- 🔄 **Reset** or **randomly generate** obstacle maps
- 🧩 Change **grid size** dynamically (from 5x5 to 100x100)

---

## 📸 Preview

![Image Alt](https://github.com/pawel-rachocki/Pathfinding-Visualizer-Java/blob/main/PathFindApp.png?raw=true)

---

## 🚀 Getting Started

### Requirements

- Java 11+ (with JavaFX support)
- JavaFX SDK
- IDE like IntelliJ or Eclipse (with JavaFX configured)

🕹️ How to Use

    🟩 Set Start Point – click the button and then click on a grid cell.

    🟥 Set End Point – same as above, but for the end node.

    ⚫ Add/Delete Obstacles – click cells to toggle walls.

    🧠 Run Dijkstra / A* – start the selected algorithm.

    🎲 Generate Grid – fills grid with random obstacles.

    ♻️ Reset Grid – clears everything on the board.

    🧮 Change Grid Size – enter a number and click "Apply Size".

🧱 Project Structure

src/
│
├── PathFindApp.java          # Main application with UI
├── Grid.java                 # Logical grid with nodes
├── Node.java                 # Individual cell with state
├── DijkstraPathfinder.java  # Dijkstra algorithm implementation
├── AStarPathfinder.java     # A* algorithm implementation
└── HeuristicType.java       # Enum for heuristic types (e.g. EUCLIDEAN)

🛠️ To Do

  Add BFS / DFS options
  Export path or grid to file
  Add diagonal movement support
  Add animation speed slider

📄 License

This project is for educational purposes.
