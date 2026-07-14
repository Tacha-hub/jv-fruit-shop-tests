package core.basesyntax.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.transactions.FruitTransaction;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FromListToTransactionImplTest {
    private FromListToTransactionImpl fromListToTransactionImpl;

    @BeforeEach
    void setUp() {
        fromListToTransactionImpl = new FromListToTransactionImpl();
    }

    @Test
    void fromListToTransaction_checkException_validQue() {
        List<String> lines = List.of("type,fruit,quantity", "s,banana");

        assertThrows(RuntimeException.class,
                () -> fromListToTransactionImpl.finalProcess(lines));
    }

    @Test
    void fromListToTransaction_checkException_validOperation() {
        List<String> lines = List.of("type,fruit,quantity", "zz,banana,20");

        assertThrows(RuntimeException.class,
                () -> fromListToTransactionImpl.finalProcess(lines));
    }

    @Test
    void fromListToTransaction_checkTypeOfNumber() {
        List<String> lines = List.of("type,fruit,quantity",
                "s,banana,twenty");

        assertThrows(
                NumberFormatException.class,
                () -> fromListToTransactionImpl.finalProcess(lines));
    }

    @Test
    void fromListToTransaction_returnTransaction() {
        List<String> lines = List.of("type,fruit,quantity",
                "b,banana,10", "s,apple,20");

        List<FruitTransaction> actual =
                fromListToTransactionImpl.finalProcess(lines);

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
