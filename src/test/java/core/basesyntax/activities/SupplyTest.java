package core.basesyntax.activities;

import core.basesyntax.maps.StorageForFruitsAndQuantity;
import core.basesyntax.transactions.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SupplyTest {
    private StorageForFruitsAndQuantity storage;
    private Supply supply;

    @BeforeEach
    void setUp() {
        storage = new StorageForFruitsAndQuantity();
        supply = new Supply(storage);
    }

    @Test
    void operationHandler_existFruit_increaseQuantity() {
        storage.getStorage().put("banana", 10);
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(5);

        supply.operationHandler(transaction);

        int actual = storage.getStorage().get("banana");
        assertEquals(15, actual);
    }

    @Test
    void operationHandler_newFruit_addToStorage() {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("apple");
        transaction.setQuantity(20);

        supply.operationHandler(transaction);

        int actual = storage.getStorage().get("apple");
        assertEquals(20, actual);
    }
}
