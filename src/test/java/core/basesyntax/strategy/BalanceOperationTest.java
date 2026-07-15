package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BalanceOperationTest {
    private BalanceOperation balanceOperation;
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        balanceOperation = new BalanceOperation(storage);
    }

    @Test
    void apply_newFruit_setQuantity() {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setQuantity(20);
        transaction.setFruit("banana");
        balanceOperation.apply(transaction);
        int actual = storage.getStorage().get("banana");
        assertEquals(20, actual);
    }
}
