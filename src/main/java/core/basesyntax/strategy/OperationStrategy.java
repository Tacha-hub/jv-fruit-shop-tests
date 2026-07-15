package core.basesyntax.strategy;

import core.basesyntax.model.FruitTransaction;

public interface OperationStrategy {
    void addHandler(FruitTransaction.Operation operation, OperationHandler handler);

    OperationHandler get(FruitTransaction.Operation operation);
}
