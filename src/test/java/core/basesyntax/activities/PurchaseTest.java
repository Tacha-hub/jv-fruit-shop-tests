package core.basesyntax.activities;

import core.basesyntax.maps.StorageForFruitsAndQuantity;
import core.basesyntax.transactions.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    private StorageForFruitsAndQuantity  storage;
    Purchase purchase;

    @BeforeEach
    void setUp() {
        storage = new StorageForFruitsAndQuantity();
        purchase = new Purchase(storage);
    }

    @Test
    void operationHandler_checkException() {
        storage.getStorage().put("banana", 3);

        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setQuantity(5);
        fruitTransaction.setFruit("banana");

        assertThrows(RuntimeException.class,
                () -> purchase.operationHandler(fruitTransaction));

    }

    @Test
    void operationHandler_enoughFruits() {
        storage.getStorage().put("banana", 5);

        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setQuantity(3);
        fruitTransaction.setFruit("banana");

        purchase.operationHandler(fruitTransaction);

        int actual = storage.getStorage().get("banana");
        assertEquals(2, actual);
    }

    @Test
    void operationHandler_makeEmpty() {
        storage.getStorage().put("banana", 5);

        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setQuantity(5);
        fruitTransaction.setFruit("banana");

        purchase.operationHandler(fruitTransaction);

        int actual = storage.getStorage().get("banana");
        assertEquals(0, actual);
    }
}