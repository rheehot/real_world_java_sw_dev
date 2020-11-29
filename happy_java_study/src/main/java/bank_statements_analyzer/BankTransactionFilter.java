package bank_statements_analyzer;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean condition(BankTransaction bankTransaction);
}

