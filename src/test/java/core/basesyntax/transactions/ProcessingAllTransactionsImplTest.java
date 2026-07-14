package core.basesyntax.transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.maps.StorageForChoosingTypeOperation;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProcessingAllTransactionsImplTest {
    private StorageForChoosingTypeOperation operationStorage;
    private ProcessingAllTransactions processingAllTransactions;

    @BeforeEach
    void setUp() {
        operationStorage = new StorageForChoosingTypeOperation();
        processingAllTransactions =
                new ProcessingAllTransactionsImpl(operationStorage);
    }

    @Test
    void processAllTransactions_callsAllInOrder() {
        List<FruitTransaction.Operation> calledHandlers = new ArrayList<>();
        List<FruitTransaction> handledTransactions = new ArrayList<>();

        operationStorage.addHandler(FruitTransaction.Operation.BALANCE,
                transaction -> {
                    calledHandlers.add(FruitTransaction.Operation.BALANCE);
                    handledTransactions.add(transaction); });

        operationStorage.addHandler(FruitTransaction.Operation.SUPPLY,
                transaction -> {
                    calledHandlers.add(FruitTransaction.Operation.SUPPLY);
                    handledTransactions.add(transaction); });

        operationStorage.addHandler(FruitTransaction.Operation.PURCHASE,
                transaction -> {
                    calledHandlers.add(FruitTransaction.Operation.PURCHASE);
                    handledTransactions.add(transaction); });

        operationStorage.addHandler(FruitTransaction.Operation.RETURN,
                transaction -> {
                    calledHandlers.add(FruitTransaction.Operation.RETURN);
                    handledTransactions.add(transaction); });

        List<FruitTransaction> transactions = List.of(
                createTransaction(
                        FruitTransaction.Operation.BALANCE, "banana", 20),
                createTransaction(
                        FruitTransaction.Operation.SUPPLY, "banana", 10),
                createTransaction(
                        FruitTransaction.Operation.PURCHASE, "banana", 5),
                createTransaction(
                        FruitTransaction.Operation.RETURN, "banana", 2)
        );

        processingAllTransactions.processAllTransactions(transactions);

        assertEquals(
                List.of(
                        FruitTransaction.Operation.BALANCE,
                        FruitTransaction.Operation.SUPPLY,
                        FruitTransaction.Operation.PURCHASE,
                        FruitTransaction.Operation.RETURN), calledHandlers);
        assertEquals(transactions, handledTransactions);
    }

    @Test
    void processAllTransactions_handlerMissing_throwsException() {
        FruitTransaction transaction = createTransaction(
                FruitTransaction.Operation.BALANCE, "banana", 20);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> processingAllTransactions.processAllTransactions(
                        List.of(transaction)));

        assertEquals("Handler not found for operation: BALANCE",
                exception.getMessage());
    }

    private FruitTransaction createTransaction(
            FruitTransaction.Operation operation,
            String fruit,
            int quantity) {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setOperation(operation);
        transaction.setFruit(fruit);
        transaction.setQuantity(quantity);
        return transaction;
    }
}
