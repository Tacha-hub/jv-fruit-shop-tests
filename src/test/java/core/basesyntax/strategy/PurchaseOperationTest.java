package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseOperationTest {
    private Storage storage;
    private PurchaseOperation purchaseOperation;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        purchaseOperation = new PurchaseOperation(storage);
    }

    @Test
    void apply_notEnoughFruits_throwsException() {
        storage.getStorage().put("banana", 3);
        FruitTransaction transaction = createTransaction("banana", 5);
        assertThrows(RuntimeException.class, () -> purchaseOperation.apply(transaction));
        assertEquals(3, storage.getStorage().get("banana"));
    }

    @Test
    void apply_enoughFruits_decreasesQuantity() {
        storage.getStorage().put("banana", 5);
        FruitTransaction transaction = createTransaction("banana", 3);
        purchaseOperation.apply(transaction);
        int actual = storage.getStorage().get("banana");
        assertEquals(2, actual);
    }

    @Test
    void apply_allAvailableFruits_setsZero() {
        storage.getStorage().put("banana", 5);
        FruitTransaction transaction = createTransaction("banana", 5);
        purchaseOperation.apply(transaction);
        int actual = storage.getStorage().get("banana");
        assertEquals(0, actual);
    }

    private FruitTransaction createTransaction(String fruit, int quantity) {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit(fruit);
        transaction.setQuantity(quantity);
        return transaction;
    }
}
