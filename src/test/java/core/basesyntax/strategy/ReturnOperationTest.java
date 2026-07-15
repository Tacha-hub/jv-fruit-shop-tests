package core.basesyntax.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReturnOperationTest {
    private Storage storage;
    private ReturnOperation returnOperation;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        returnOperation = new ReturnOperation(storage);
    }

    @Test
    void apply_existingFruit_increasesQuantity() {
        storage.getStorage().put("banana", 1);
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(1);
        returnOperation.apply(transaction);
        int actual = storage.getStorage().get("banana");
        assertEquals(2, actual);
    }
}
