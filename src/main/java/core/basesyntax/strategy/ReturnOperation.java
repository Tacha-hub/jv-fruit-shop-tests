package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class ReturnOperation implements OperationHandler {
    private final Storage storage;

    public ReturnOperation(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void apply(FruitTransaction transaction) {
        String fruit = transaction.getFruit();
        int currentQuantity = storage.getStorage().getOrDefault(fruit, 0);
        storage.getStorage().put(fruit, currentQuantity + transaction.getQuantity());
    }
}
