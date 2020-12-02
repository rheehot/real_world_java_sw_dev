package bank_statements_analyzer_restudy.processor;

import bank_statements_analyzer_restudy.dao.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
    public boolean filter(BankTransaction bankTransaction);
}