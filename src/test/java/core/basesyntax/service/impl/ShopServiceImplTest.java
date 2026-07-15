package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.ShopService;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopServiceImplTest {
    private OperationStrategy operationStrategy;
    private ShopService shopService;

    @BeforeEach
    void setUp() {
        operationStrategy = new OperationStrategyImpl();
        shopService = new ShopServiceImpl(operationStrategy);
    }

    @Test
    void process_missingHandler_checkException() {
        FruitTransaction transaction = createTransaction(
                FruitTransaction.Operation.BALANCE, "banana", 20);
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> shopService.process(List.of(transaction)));
        assertEquals("Handler not found for operation: BALANCE", exception.getMessage());
    }

    private void addHandler(FruitTransaction.Operation operation,
                            List<FruitTransaction.Operation> calledHandlers,
                            List<FruitTransaction> handledTransactions) {
        operationStrategy.addHandler(operation, transaction -> {
            calledHandlers.add(operation);
            handledTransactions.add(transaction); });
    }

    private FruitTransaction createTransaction(FruitTransaction.Operation operation,
                                               String fruit,
                                               int quantity) {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setOperation(operation);
        transaction.setFruit(fruit);
        transaction.setQuantity(quantity);
        return transaction;
    }
}
