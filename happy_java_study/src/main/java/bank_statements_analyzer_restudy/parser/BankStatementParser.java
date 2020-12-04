package bank_statements_analyzer_restudy.parser;

import bank_statements_analyzer_restudy.dao.BankTransaction;
import bank_statements_analyzer_restudy.notification.Notification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public interface BankStatementParser {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    BankTransaction parseFrom(final String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);

    default BankTransaction validateLine(String dateStr, String amountStr, String descriptionStr) {

        final Notification notification = new Notification();
        LocalDate date = null;
        double amount = 0;
        String description = null;

        try {
            date = LocalDate.parse(dateStr, DATE_TIME_FORMATTER);

            if(date.isAfter(LocalDate.now())) {
                notification.addError("date cannot be in the future.");
            }

        } catch (DateTimeParseException e) {
            notification.addError("invalid date time parsing.");
        }

        try {
            amount = Double.parseDouble(amountStr);
        } catch (
                NullPointerException  e) {
            notification.addError("string is null.");
        } catch (NumberFormatException e) {
            notification.addError("there is no parsable double data.");
        }

        description = descriptionStr;
        if (description.length() > 100) {
            notification.addError("description is to long");
        }

        if (notification.hadErrors()) {
            throw new RuntimeException(notification.errorMessage());
        }

        return new BankTransaction(date, amount, description);
    }
}
