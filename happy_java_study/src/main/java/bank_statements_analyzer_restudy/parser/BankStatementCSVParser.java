package bank_statements_analyzer_restudy.parser;

import bank_statements_analyzer_restudy.dao.BankTransaction;
import bank_statements_analyzer_restudy.notification.Notification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class BankStatementCSVParser implements BankStatementParser{

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public BankTransaction parseFrom(final String line) {
//        final String[] columns = line.split(",");
//        // to date
//        final LocalDate date = LocalDate.parse(columns[0], DATE_TIME_FORMATTER);
//        // to double
//        final double amount = Double.parseDouble(columns[1]);
//
//        return new BankTransaction(date, amount, columns[2]);
        final Notification notification = new Notification();

        String[] columns = null;
        LocalDate date = null;
        double amount = 0;
        String description = null;

        try {
            columns = line.split(",");
            if (columns.length != 3) {
                notification.addError("wrong data issue.");
            } else {
                try {
                    date = LocalDate.parse(columns[0], DATE_TIME_FORMATTER);

                    if(date.isAfter(LocalDate.now())) {
                        notification.addError("date cannot be in the future.");
                    }

                } catch (DateTimeParseException e) {
                    notification.addError("invlid date time parsing.");
                }

                try {
                    amount = Double.parseDouble(columns[1]);
                } catch (
                        NullPointerException  e) {
                    notification.addError("string is null.");
                } catch (NumberFormatException e) {
                    notification.addError("there is no parsable double data.");
                }

                description = columns[2];
                if (description.length() > 100) {
                    notification.addError("description is to long");
                }
            }
        } catch(PatternSyntaxException e) {
            notification.addError("invalid syntax error in csv file.");
        }

        if (notification.hadErrors()) {
            throw new RuntimeException(notification.errorMessage());
        }

        return new BankTransaction(date, amount, description);
    } 

    public List<BankTransaction> parseLinesFrom(final List<String> lines) {
//        List<BankTransaction> bankTransactions = new ArrayList<BankTransaction>();
//
//        for(String line: lines) {
//            bankTransactions.add(parseFrom(line));
//        }
        return lines.stream().map(this::parseFrom).collect(Collectors.toList());
    }


}
