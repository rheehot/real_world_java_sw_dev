package bank_statements_analyzer_restudy.dao;

import java.time.LocalDate;

public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(final LocalDate date, final double amout, final String description) {
        this.date = date;
        this.amount = amout;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}
