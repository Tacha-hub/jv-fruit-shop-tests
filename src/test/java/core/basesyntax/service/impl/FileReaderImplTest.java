package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileReaderImplTest {
    private static final String VALID_INPUT = "src/test/resources/validInput.csv";
    private static final String INVALID_INPUT = "src/test/resources/missing.csv";

    private FileReaderImpl fileReader;

    @BeforeEach
    void setUp() {
        fileReader = new FileReaderImpl();
    }

    @Test
    void read_invalidPath_checkException() {
        assertThrows(RuntimeException.class, () -> fileReader.read(INVALID_INPUT));
    }

    @Test
    void read_validFile_returnsAll() {
        List<String> expected = List.of("type,fruit,quantity",
                "b,banana,20",
                "s,banana,100",
                "p,banana,13");

        List<String> actual = fileReader.read(VALID_INPUT);

        assertEquals(expected, actual);
    }
}
