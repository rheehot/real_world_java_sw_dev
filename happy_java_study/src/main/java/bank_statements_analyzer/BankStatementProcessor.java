package bank_statements_analyzer;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    // calculate total transaction
    public double calculateTotalAmount() {
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }


    // calculate transaction by month
    public double calculateTotalMonth(final Month month) {
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions) {
            if(bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }


    // calculate transaction by category
    public double claculateTotalCategory(final String category) {
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions) {
            if(bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public List<BankTransaction> findTransaction(final BankTransactionFilter bankTransactionFilter) {

        final List<BankTransaction> result = new ArrayList<BankTransaction>();

        for (final BankTransaction bankTransaction : bankTransactions) {
            if(bankTransactionFilter.condition(bankTransaction)) {
                result.add(bankTransaction);
            }
        }

        return result;
    }

    public List<BankTransaction> findTransactionGreaterThanEqual(final double amount) {

        return findTransaction(bankTransaction -> bankTransaction.getAmount() >= amount);
    }

    public List<BankTransaction> findTransactionInMonth(final Month month) {

        return findTransaction(bankTransaction -> bankTransaction.getDate().getMonth() == month);
    }

    public List<BankTransaction> findTransactionInMonthAndGreater(final Month month, final int amount) {

        return findTransaction(bankTransaction -> bankTransaction.getAmount() >= amount && bankTransaction.getDate().getMonth() == month);
    }

//    public List<BankTransaction> findTransactionGreaterThanEqual(final double amount) {
//
//        final List<BankTransaction> result = new ArrayList<BankTransaction>();
//
//        for(final BankTransaction bankTransaction: bankTransactions) {
//            if(bankTransaction.getAmount() >= amount) {
//                result.add(bankTransaction);
//            }
//        }
//
//        return result;
//    }
//
//    public List<BankTransaction> findTransactionInMonth(final Month month) {
//        final List<BankTransaction> result = new ArrayList<BankTransaction>();
//
//        for(final BankTransaction bankTransaction: bankTransactions) {
//            if(bankTransaction.getDate().getMonth() == month) {
//                result.add(bankTransaction);
//            }
//        }
//
//        return result;
//    }
//
//    public List<BankTransaction> findTransactionInMonthAndGreater(final Month month, final int amount) {
//        final List<BankTransaction> result = new ArrayList<BankTransaction>();
//
//        for(final BankTransaction bankTransaction: bankTransactions) {
//            if(bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount) {
//                result.add(bankTransaction);
//            }
//        }
//        return result;
//    }

}
