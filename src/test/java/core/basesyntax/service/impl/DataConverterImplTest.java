package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private DataConverterImpl dataConverter;

    @BeforeEach
    void setUp() {
        dataConverter = new DataConverterImpl();
    }

    @Test
    void convertToTransaction_invalidColumns_throwsException() {
        List<String> lines = List.of("type,fruit,quantity", "s,banana");
        assertThrows(RuntimeException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void convertToTransaction_invalidOperation_throwsException() {
        List<String> lines = List.of("type,fruit,quantity", "zz,banana,20");
        assertThrows(RuntimeException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void convertToTransaction_invalidQuantity_throwsException() {
        List<String> lines = List.of("type,fruit,quantity", "s,banana,twenty");
        assertThrows(RuntimeException.class,
                () -> dataConverter.convertToTransaction(lines));
    }

    @Test
    void convertToTransaction_validLines_returnsTransactions() {
        List<String> lines = List.of("type,fruit,quantity",
                "b,banana,10", "s,apple,20");
        List<FruitTransaction> actual = dataConverter.convertToTransaction(lines);
        assertEquals(FruitTransaction.Operation.BALANCE,
                actual.get(0).getOperation());
        assertEquals("banana", actual.get(0).getFruit());
        assertEquals(10, actual.get(0).getQuantity());
        assertEquals(FruitTransaction.Operation.SUPPLY,
                actual.get(1).getOperation());
        assertEquals("apple", actual.get(1).getFruit());
        assertEquals(20, actual.get(1).getQuantity());
    }
}
