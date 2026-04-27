# Expense-Tracker
A simple expense tracking CLI to manage finances.

## Project Structure (Maven)

```
src/
├── main/java/
│   ├── main/
│   │   └── Main.java              (entry point)
│   └── expenses/
│       ├── Commands.java          (CLI flag definitions)
│       ├── ExpenseData.java       (data model)
│       ├── ExpenseService.java    (business logic)
│       └── ExpenseStorage.java    (persistence layer)
└── test/java/
    └── expenses/
        └── ExpenseServiceTest.java (unit tests)
```

## Setup & Run

### Prerequisites
- **Java 17+** installed
- **Maven 3.8+** (or use IntelliJ's bundled Maven)
- **Windows PowerShell** (or any terminal)

### Option 1: Run via IntelliJ (Recommended - No Maven needed)

1. **Open the project** in IntelliJ
2. **Run tests:**
   - Right-click `src/test/java/expenses/ExpenseServiceTest.java` → **Run 'ExpenseServiceTest'**
3. **Run the app:**
   - Right-click `src/main/java/main/Main.java` → **Run 'Main.main()'**
   - To pass command-line args: **Run → Edit Configurations** → set `Program arguments: add --description Coffee --amount 4`

### Option 2: Run via Maven (CLI)

If you **don't have Maven installed**, you can use the included **Maven Wrapper**:

```powershell
./mvnw.cmd -v
./mvnw.cmd test
```

#### Setup Maven on Windows (if not in PATH):

1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract to: `C:\maven` (or similar)
3. Add to System PATH:
   - Open **Settings** → **Edit environment variables**
   - Add `C:\maven\bin` to **PATH**
   - Restart PowerShell

#### Build & Test:

```powershell
# Compile
./mvnw.cmd clean compile

# Run all tests
./mvnw.cmd test

# Run the app
./mvnw.cmd exec:java -Dexec.args="add --description Coffee --amount 4"

# Build JAR
./mvnw.cmd package
```

### Option 3: Run JAR with Dependencies

```powershell
# Build with dependencies included (requires maven-shade-plugin)
mvn clean package

# Run (if fat jar is built)
java -jar target/expense-tracker-1.0.0-SNAPSHOT.jar add --description Coffee --amount 4
```

## Example Usage

```powershell
# Add an expense
mvn exec:java -Dexec.args="add --description Lunch --amount 15"
# Output: Expense added successfully (ID: 1)

# List expenses (loads from expenses.json)
mvn exec:java -Dexec.args="list"

# Remove by ID (not fully implemented yet)
mvn exec:java -Dexec.args="remove --id 1"
```

## Testing

The project includes JUnit 5 tests for the `ExpenseService.add()` method:

**Test cases:**
- ✅ Valid input: adds expense with trimmed description + auto ID
- ✅ Empty description: throws `IllegalArgumentException`
- ✅ Non-positive amount: throws `IllegalArgumentException`

**Run tests:**
```powershell
mvn test
```

## Notes

- Expenses are stored in `expenses.json` (auto-created in working directory)
- Each run loads existing expenses from `expenses.json` before starting
- IDs are managed automatically (start at 1, increment on each add)
- Tests automatically clear storage and reset ID counter for isolation

