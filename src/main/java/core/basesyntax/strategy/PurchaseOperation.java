package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;

public class PurchaseOperation implements OperationHandler {
    private final Storage storage;

    public PurchaseOperation(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void apply(FruitTransaction transaction) {
        String fruit = transaction.getFruit();
        int currentQuantity = storage.getStorage().getOrDefault(fruit, 0);
        int newQuantity = currentQuantity - transaction.getQuantity();
        if (newQuantity < 0) {
            throw new RuntimeException("Not enough " + fruit
                    + " in storage. Attempted purchase: " + transaction.getQuantity());
        }
        storage.getStorage().put(fruit, newQuantity);
    }
}
