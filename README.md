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
