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

    //private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public BankTransaction parseFrom(final String line) {
//        final String[] columns = line.split(",");
//        // to date
//        final LocalDate date = LocalDate.parse(columns[0], DATE_TIME_FORMATTER);
//        // to double
//        final double amount = Double.parseDouble(columns[1]);
//
//        return new BankTransaction(date, amount, columns[2]);
//         =====> 아래와 같은 에러 데이터 핸들링 기능 추가

        final String[] columns = line.split(",");
        if (columns.length != 3) {
            throw new RuntimeException("illegal csv file systax.");
        }

        return validate(columns[0], columns[1], columns[2]);
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
