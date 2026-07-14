package core.basesyntax.activities;

import core.basesyntax.maps.StorageForFruitsAndQuantity;
import core.basesyntax.transactions.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReturnTest {
    private StorageForFruitsAndQuantity storage;
    private Return returnVal;

    @BeforeEach
    void setUp() {
        storage = new StorageForFruitsAndQuantity();
        returnVal = new Return(storage);
    }

    @Test
    void operationHandler_newFruit() {
        storage.getStorage().put("banana", 1);

        FruitTransaction fruitTransaction = new FruitTransaction();
        fruitTransaction.setFruit("banana");
        fruitTransaction.setQuantity(1);

        returnVal.operationHandler(fruitTransaction);

        int actual = storage.getStorage().get("banana");
        assertEquals(2, actual);

    }

}

