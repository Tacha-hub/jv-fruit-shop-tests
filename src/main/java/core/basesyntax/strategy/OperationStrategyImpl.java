package core.basesyntax.strategy;

import core.basesyntax.model.FruitTransaction;
import java.util.HashMap;
import java.util.Map;

public class OperationStrategyImpl implements OperationStrategy {
    private final Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();

    @Override
    public void addHandler(FruitTransaction.Operation operation, OperationHandler handler) {
        handlers.put(operation, handler);
    }

    @Override
    public OperationHandler get(FruitTransaction.Operation operation) {
        return handlers.get(operation);
    }
}
