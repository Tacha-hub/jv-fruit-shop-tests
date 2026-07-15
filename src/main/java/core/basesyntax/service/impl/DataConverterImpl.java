package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    private static final int OPERATION_INDEX = 0;
    private static final int FRUIT_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;
    private static final int EXPECTED_PARTS = 3;

    @Override
    public List<FruitTransaction> convertToTransaction(List<String> inputReport) {
        List<FruitTransaction> transactions = new ArrayList<>();
        for (int i = 1; i < inputReport.size(); i++) {
            String line = inputReport.get(i);
            String[] parts = line.split(",", -1);
            if (parts.length != EXPECTED_PARTS) {
                throw new RuntimeException("Invalid CSV line: " + line);
            }
            FruitTransaction transaction = new FruitTransaction();
            transaction.setOperation(findOperation(parts[OPERATION_INDEX].trim()));
            transaction.setFruit(parts[FRUIT_INDEX].trim());
            transaction.setQuantity(parseQuantity(parts[QUANTITY_INDEX].trim(), line));
            transactions.add(transaction);
        }
        return transactions;
    }

    private FruitTransaction.Operation findOperation(String code) {
        for (FruitTransaction.Operation operation : FruitTransaction.Operation.values()) {
            if (operation.getCode().equals(code)) {
                return operation;
            }
        }
        throw new RuntimeException("Unknown operation code: " + code);
    }

    private int parseQuantity(String value, String line) {
        try {
            int quantity = Integer.parseInt(value);
            if (quantity < 0) {
                throw new RuntimeException("Quantity can't be negative: " + line);
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid quantity in line: " + line, e);
        }
    }
}
