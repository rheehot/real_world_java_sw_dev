package bank_statements_analyzer_restudy.processor;

import bank_statements_analyzer_restudy.dao.BankTransaction;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotal(final BankTransactionFilter BankTransactionFilter) {
        double total = 0;
        for (BankTransaction bankTransaction: bankTransactions) {
            if (BankTransactionFilter.filter(bankTransaction)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    //전체 합산 금액
    public double calculateTotalAmount() {
//        double total = 0;
//        for (BankTransaction bankTransaction: bankTransactions) {
//                total += bankTransaction.getAmount();
//        }
//        return total;
        return calculateTotal(bankTransaction -> true);
    }

    //특정 월의 전체 합산 금액
    public double calculateTotalInMonth(final Month month) {
//        double total = 0;
//        for (BankTransaction bankTransaction: bankTransactions) {
//            if (bankTransaction.getDate().getMonth() == month) {
//                total += bankTransaction.getAmount();
//            }
//        }
//        return total;
        return calculateTotal(bankTransaction -> bankTransaction.getDate().getMonth() == month);
    }

    //특정 description의 합산 금액
    public double calculateTotalInDescription(final String description) {
//        double total = 0;
//        for (BankTransaction bankTransaction: bankTransactions) {
//            if (bankTransaction.getDescription().equals(description)) {
//                total += bankTransaction.getAmount();
//            }
//        }
//        return total;
        return calculateTotal(bankTransaction -> bankTransaction.getDescription().equals(description));
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<BankTransaction>();

        for (BankTransaction bankTransaction: bankTransactions) {
            if(bankTransactionFilter.filter(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    //특정 월별 내역
    public List<BankTransaction> findTransactionsInMonth(final Month month) {
        return findTransactions(bankTransaction -> bankTransaction.getDate().getMonth() == month);
    }

    //특정 금액 이상의 입출금 내역
    public List<BankTransaction> findTransactionsGreaterThanEqual(final double amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }

    //특정 월 및 특정 금액 이상의 이출금ß 내역
    public List<BankTransaction> findTransactionsInMonthAndGreaterThanEqual(final double amount, final Month month) {
        return findTransactions(bankTransaction -> bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount);
    }


}
