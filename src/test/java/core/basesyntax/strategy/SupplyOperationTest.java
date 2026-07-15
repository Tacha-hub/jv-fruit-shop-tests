package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyOperationTest {
    private Storage storage;
    private SupplyOperation supplyOperation;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        supplyOperation = new SupplyOperation(storage);
    }

    @Test
    void apply_existingFruit_increasesQuantity() {
        storage.getStorage().put("banana", 10);
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(5);
        supplyOperation.apply(transaction);
        int actual = storage.getStorage().get("banana");
        assertEquals(15, actual);
    }

    @Test
    void apply_newFruit_addsToStorage() {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("apple");
        transaction.setQuantity(20);
        supplyOperation.apply(transaction);
        int actual = storage.getStorage().get("apple");
        assertEquals(20, actual);
    }
}
