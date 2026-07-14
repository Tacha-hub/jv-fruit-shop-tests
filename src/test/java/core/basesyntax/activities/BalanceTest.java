package core.basesyntax.activities;

import core.basesyntax.maps.StorageForFruitsAndQuantity;
import core.basesyntax.transactions.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BalanceTest {
    private Balance balance;
    private StorageForFruitsAndQuantity storage;

    @BeforeEach
    void setUp() {
        storage = new StorageForFruitsAndQuantity();
        balance = new Balance(storage);
    }

    @Test
    void operationHandler_newFruit_setQuantity() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setQuantity(20);
        fruitTransaction.setFruit("banana");

        balance.operationHandler(fruitTransaction);

        int actual = storage.getStorage().get("banana");
        assertEquals(20, actual);
    }

}
