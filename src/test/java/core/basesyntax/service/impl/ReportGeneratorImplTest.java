package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.db.Storage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private static final String HEADER = "fruit,quantity";

    private Storage storage;
    private ReportGeneratorImpl reportGenerator;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        reportGenerator = new ReportGeneratorImpl(storage);
    }

    @Test
    void getReport_emptyStorage() {
        String actual = reportGenerator.getReport();

        assertEquals(HEADER, actual);
    }

    @Test
    void getReport_storageWithFruits_returnsAll() {
        storage.getStorage().put("banana", 152);
        storage.getStorage().put("apple", 90);

        List<String> lines = reportGenerator.getReport().lines().toList();

        assertEquals(3, lines.size());
        assertEquals(HEADER, lines.get(0));
        assertTrue(lines.contains("banana,152"));
        assertTrue(lines.contains("apple,90"));
    }
}
