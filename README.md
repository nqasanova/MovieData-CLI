# MovieData-CLI

A console-based Java application for exploring and managing movie datasets. Browse, sort, search, and filter a CSV movie database entirely from the command line.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
  - [1. List Entities](#1-list-entities)
  - [2. Sort Entities](#2-sort-entities)
  - [3. Search Entities](#3-search-entities)
  - [4. List Column Names](#4-list-column-names)
  - [5. Filter Entities](#5-filter-entities)
- [Data Format](#data-format)
- [Known Limitations](#known-limitations)

---

## Overview

MovieData-CLI reads movie records from a local CSV file (`mymoviedb.csv`) and provides an interactive menu-driven interface to explore the data. All interactions happen in the terminal — no GUI or external dependencies required.

---

## Features

- **List** all movie records or a selected subset by row range or chosen fields
- **Sort** records by any field (ascending or descending)
- **Search** records by exact or partial match across string and numeric fields
- **Filter** records using rich comparison operators: `eq`, `gt`, `lt`, `ge`, `le`, `bt` (between), and `null`
- **Export** search and filter results to a CSV file (`selected_entities.csv`)
- Date filtering supports year-only, month-only, day-only, and range queries

---

## Project Structure
MovieData-CLI/
├── Main.java        # Entry point; menu logic and all data operations
├── Movie.java       # Movie model class with getters, setters, and toString
└── mymoviedb.csv    # Movie dataset (must be present in the working directory)

---

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- A `mymoviedb.csv` file in the same directory as the compiled classes

---

## Getting Started

**1. Clone the repository**

```bash
git clone https://github.com/your-username/MovieData-CLI.git
cd MovieData-CLI
```

**2. Compile the source files**

```bash
javac Main.java Movie.java
```

**3. Run the application**

```bash
java Main
```

Make sure `mymoviedb.csv` is present in the directory where you run the command.

---

## Usage

On launch, the main menu is displayed:
1 - List all the entities
2 - Sort the entities
3 - Search the entities
4 - List column names
5 - Filter entities
0 - Exit

Enter the number corresponding to the action you want to perform.

---

### 1. List Entities

Three sub-options are available:

| Option | Description |
|--------|-------------|
| `a` | List the first 100 records with all fields |
| `b` | List the first 100 records showing only specified fields |
| `c` | List records within a custom row range (lower–upper boundary) |

For option `b`, enter field names separated by spaces, e.g.:
Enter the field names to list: Title Genre Popularity

---

### 2. Sort Entities

Sort the full dataset by any of the following fields:

| Key | Field |
|-----|-------|
| `a` | Release_Date |
| `b` | Title |
| `c` | Overview |
| `d` | Popularity |
| `e` | Vote_Count |
| `f` | Vote_Average |
| `g` | Original_Language |
| `h` | Genre |
| `j` | Poster_Url |

After selecting a field, choose sort order: `ASC` or `DESC`.

---

### 3. Search Entities

Enter a field name followed by a search value.

**String fields** (`Title`, `Overview`, `Original_Language`, `Poster_Url`):
- You will be prompted for a substring to match against.

**Numeric fields** (`Release_Date`, `Popularity`, `Vote_Count`, `Vote_Average`):
- Enter the field name and exact value, e.g.: `Popularity 83.0`

**Genre field**:
- Lists all movies matching any valid genre category.

Supported genres: `Action`, `Adventure`, `Animation`, `Comedy`, `Crime`, `Documentary`, `Drama`, `Family`, `Fantasy`, `History`, `Horror`, `Music`, `Mystery`, `Romance`, `Science Fiction`, `Thriller`, `TV Movie`, `War`

If results are found, you will be asked whether to save them to `selected_entities.csv`.

---

### 4. List Column Names

Reads and prints the header row from `mymoviedb.csv`, showing all available column names.

---

### 5. Filter Entities

Multi-field filtering using comparison operators. You will be asked how many fields to filter on, then prompted for each field name and its filter conditions.

**String field operators:**

| Option | Description |
|--------|-------------|
| `i` | Starts with |
| `ii` | Ends with |
| `iii` | Contains |
| `iv` | Null / empty |

**Date field operators** (format: `MM/DD/YYYY`):

| Option | Description |
|--------|-------------|
| `eq` | Equal to |
| `gt` | Greater than |
| `lt` | Less than |
| `ge` | Greater than or equal |
| `le` | Less than or equal |
| `bt` | Between two dates |
| `null` | Missing values |
| `y` | In a specific year |
| `m` | In a specific month |
| `d` | In a specific day |

**Numeric / Double field operators:**

| Option | Description |
|--------|-------------|
| `eq` | Equal to |
| `gt` | Greater than |
| `lt` | Less than |
| `ge` | Greater than or equal |
| `le` | Less than or equal |
| `bt` | Between two values |
| `null` | Missing values |

If results are found, you will be asked whether to export them to `selected_entities.csv`.

---

## Data Format

The application expects a CSV file named `mymoviedb.csv` with the following columns in order:

| # | Column | Type |
|---|--------|------|
| 0 | Title | String |
| 1 | Genre | String |
| 2 | Release_Date | String (`MM/DD/YYYY`) |
| 3 | Overview | String |
| 4 | Popularity | Double |
| 5 | Vote_Count | Integer |
| 6 | Vote_Average | Double |
| 7 | Original_Language | String |
| 8 | Poster_Url | String |

The first row is treated as a header and is skipped during import. Fields containing commas must be enclosed in double quotes.
