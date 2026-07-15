package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class SupplyOperation implements OperationHandler {
    private final Storage storage;

    public SupplyOperation(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void apply(FruitTransaction transaction) {
        String fruit = transaction.getFruit();
        int currentQuantity = storage.getStorage().getOrDefault(fruit, 0);
        storage.getStorage().put(fruit, currentQuantity + transaction.getQuantity());
    }
}
