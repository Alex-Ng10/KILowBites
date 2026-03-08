# KILowBites

A full-featured recipe and meal management desktop application built in Java. KILowBites lets cooks create structured recipes, compose multi-recipe meals, and drive the full cooking workflow — from ingredient shopping to calorie tracking — entirely offline.

---

## Key Features

- **Recipe Editor** — Create and edit recipes with typed ingredients (mass or volume), required utensils, and ordered cooking steps; ingredients and utensils are auto-sorted alphabetically for consistency.
- **Meal Planner** — Compose meals from saved recipes; duplicate recipes in a single meal are prevented at the model layer.
- **Recipe Scaling** — Adjust serving count and all ingredient quantities scale proportionally via a linear factor calculation.
- **Calorie Calculator** — Look up any ingredient in the built-in 100+ food nutritional database and compute calories for any quantity and unit.
- **Unit Converter** — Convert between 13 measurement units across two categories: 4 mass units (Gram, Dram, Ounce, Pound) and 9 volume units (mL through Gallon), including cross-type mass↔volume conversion using per-food density data.
- **Shopping List Generator** — Aggregates all ingredients from every recipe in a meal, maps each item to its supermarket aisle and price from embedded data files, and produces a consolidated list.
- **Multilingual UI** — UI strings fully externalized via Java `ResourceBundle`; ships with English, French, and Spanish locales.

---

## Tech Stack

| Technology | Role |
|---|---|
| **Java (Swing)** | Cross-platform desktop GUI — no external UI toolkit dependency |
| **Java Serialization** | Persist recipes (`.rcp`) and meals (`.mel`) as typed object graphs |
| **JUnit 5 (Jupiter)** | Unit and integration testing for domain model, converters, and file I/O |
| **Java ResourceBundle** | Externalizes all UI strings for i18n without a separate framework |
| **Text data files (`.dat` / `.ntr`)** | Lightweight store for nutritional data, aisle mappings, and prices — no database required |

---

## Architecture

KILowBites follows a strict **Model-View-Controller** structure across three packages:

```
src/
├── cooking/        # Domain model — Recipe, Meal, Ingredients, Steps, Utensils
├── gui/            # Swing windows — one JFrame per tool (editor, calculator, viewer…)
├── controller/     # Event handlers — bridge user actions to domain operations
├── converter/      # Unit conversion logic — MassConverter, VolumeConverter, MassToVolume
├── utilities/      # Cross-cutting concerns — FileUtilities, Foods DB, Units, ImageUtilities
└── language/       # i18n property files (en_US, fr_FR, es_ES)
```

The **Observer pattern** (`DocumentStateSubject` / `DocumentStateObserver`) propagates unsaved-change state from controllers to the UI, keeping save-prompt logic decoupled from individual windows. The `RecipeElement` interface (implemented by `Ingredients`, `Steps`, and `Utensils`) allows controllers to handle all recipe component types polymorphically.

---

## Getting Started

### Prerequisites

| Requirement | Version |
|---|---|
| Java Development Kit (JDK) | 11 or later |
| IDE (recommended) | IntelliJ IDEA, Eclipse, or VS Code with Java extensions |

> No build tool (Maven/Gradle) is required. The project compiles with standard `javac`.

### Install

```bash
git clone https://github.com/MichaelAho1/KLowBites1.git
cd KLowBites1   # repository directory; the application class is app.KILowBites
```

### Compile

From the project root, compile all sources and include the JUnit 5 JAR on the classpath if you want to run tests:

```bash
# Compile all application sources
javac -d out -sourcepath src $(find src -name "*.java" ! -path "*/testing/*")

# Compile test sources (requires junit-platform-console-standalone-*.jar on classpath)
javac -d out -cp out:path/to/junit-platform-console-standalone.jar \
  $(find src/testing -name "*.java")
```

### Run

```bash
java -cp out app.KILowBites
```

The main window opens with a menu bar. All tools (Recipe Editor, Meal Editor, Calorie Calculator, Unit Converter, Shopping List, Process Viewer) are accessible from the menu.

### Data Files

The following files must remain in the **project root** at runtime (they are already included in the repository):

| File | Contents |
|---|---|
| `Foods.ntr` | Serialized nutritional database (~100 foods, calories, density) |
| `Aisles.dat` | Grocery aisle assignments per ingredient |
| `Prices.dat` | Per-unit price per ingredient |

---

## Screenshots

> _Replace the placeholders below with actual screenshots after launch._

| Recipe Editor | Meal Planner |
|---|---|
| ![Recipe Editor](docs/screenshots/recipe_editor.png) | ![Meal Planner](docs/screenshots/meal_planner.png) |

| Shopping List | Unit Converter |
|---|---|
| ![Shopping List](docs/screenshots/shopping_list.png) | ![Unit Converter](docs/screenshots/unit_converter.png) |

---

## Key Engineering Decision

Ingredient quantities and units are stored as first-class typed values — never as plain strings — enabling safe arithmetic at every layer. Recipe scaling, calorie calculation, and shopping list aggregation all operate on numeric amounts with explicit `UnitType` (mass vs. volume) metadata. When a recipe is added to a meal, the shopping list controller can detect unit mismatches and apply the `MassToVolume` converter (using per-food density) before summing quantities. This design eliminates a whole class of silent unit-mismatch bugs that plague simpler string-based recipe stores.

---

## Testing

Tests live in `src/testing/` and use JUnit 5:

```bash
java -cp out:path/to/junit-platform-console-standalone.jar \
  org.junit.platform.console.ConsoleLauncher --scan-classpath
```

The 15 test classes cover domain model correctness (Recipe, Meal, Ingredients, Steps), unit conversions (MassConverter, VolumeConverter), file I/O round-trips, food database loading, and input validation.

Full manual test script: [Google Docs Testing Script](https://docs.google.com/document/d/1fnNmMABvBMBV95v0BUx9Yydm6JsJ41jms2der8s7BBk/edit?usp=sharing)

---

## Contact

**Team f24team3d**

- **GitHub**: [MichaelAho1](https://github.com/MichaelAho1)
- **LinkedIn**: _Add your LinkedIn URL here_
- **Portfolio**: _Add your portfolio URL here_
