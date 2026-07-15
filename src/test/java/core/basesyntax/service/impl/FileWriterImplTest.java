package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileWriterImplTest {
    private static final String EXPECTED_REPORT = "src/test/resources/expectedReport.csv";

    private FileWriterImpl fileWriter;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        fileWriter = new FileWriterImpl();
    }

    @Test
    void write_missingParentDirectory_checkException() {
        Path invalidPath = tempDir.resolve("missing/finalReport.csv");

        assertThrows(RuntimeException.class,
                () -> fileWriter.write("fruit,quantity", invalidPath.toString()));
    }

    @Test
    void write_validPath_writeReport() throws IOException {
        String expected = Files.readString(Path.of(EXPECTED_REPORT));
        Path outputFile = tempDir.resolve("finalReport.csv");

        fileWriter.write(expected, outputFile.toString());
        String actual = Files.readString(outputFile);

        assertEquals(expected, actual);
    }
}
