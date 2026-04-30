package expenses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {

    @BeforeEach
    void resetState() throws Exception {
        // shared static state -> isolate tests
        ExpenseStorage.expenses.clear();
        ExpenseStorage.resetNextId(); // reset ID counter

        // avoid cross-test interference on the persistence file
        Files.deleteIfExists(Path.of("expenses.json"));
    }

    @Test
    void add_validInput_createsExpenseWithIdAndTrimmedDescription() {
        ExpenseService service = new ExpenseService();

        ExpenseData created = service.add("  Coffee  ", 4);

        assertNotNull(created);
        assertTrue(created.getId() > 0, "ID should be assigned (> 0)");
        assertEquals("Coffee", created.getDescription(), "Description should be trimmed");
        assertEquals(4.0, created.getAmount(), 0.0001);
        assertNotNull(created.getDate());

        assertEquals(1, ExpenseStorage.findAll().size());
        assertTrue(Files.exists(Path.of("expenses.json")), "expenses.json should be written");
    }

    @Test
    void add_blankDescription_throwsIllegalArgumentException() {
        ExpenseService service = new ExpenseService();

        assertThrows(IllegalArgumentException.class, () -> service.add("   ", 10));
    }

    @Test
    void add_nonPositiveAmount_throwsIllegalArgumentException() {
        ExpenseService service = new ExpenseService();

        assertThrows(IllegalArgumentException.class, () -> service.add("Coffee", 0));
    }

    @Test
    void remove_validInput_createsExpenseWithIdAndTrimmedDescription() {
        ExpenseService service = new ExpenseService();
        ExpenseData created = service.add("Coffee", 4);
        assertNotNull(created);
        assertTrue(created.getId() > 0, "ID should be assigned (> 0)");
        assertEquals("Coffee", created.getDescription(), "Description should be trimmed");
        assertEquals(4.0, created.getAmount(), 0.0001);
        assertNotNull(created.getDate());
        assertEquals(1, ExpenseStorage.findAll().size());
    }
}

