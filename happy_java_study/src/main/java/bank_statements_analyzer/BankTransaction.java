package bank_statements_analyzer;

import java.time.LocalDate;

public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(final LocalDate date, final double amount, final String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    //get date
    public LocalDate getDate() {
        return date;
    }

    //get amount
    public double getAmount() {
        return amount;
    }

    //get description
    public String getDescription() {
        return description;
    }

    //make deposit info to string
    @Override
    public String toString() {
        return " date="+date+" amount="+amount+" description="+description;
    }
}
