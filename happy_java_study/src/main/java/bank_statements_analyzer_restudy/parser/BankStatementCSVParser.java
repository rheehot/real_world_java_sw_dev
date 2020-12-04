package bank_statements_analyzer_restudy.parser;

import bank_statements_analyzer_restudy.dao.BankTransaction;
import bank_statements_analyzer_restudy.processor.BankStatementProcessor;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BankStatementCSVParser implements BankStatementParser{

    //private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static Logger logger = Logger.getLogger(BankStatementCSVParser.class.getName());

    public BankTransaction parseFrom(final String line) {
//        final String[] columns = line.split(",");
//        // to date
//        final LocalDate date = LocalDate.parse(columns[0], DATE_TIME_FORMATTER);
//        // to double
//        final double amount = Double.parseDouble(columns[1]);
//
//        return new BankTransaction(date, amount, columns[2]);
//         =====> 아래와 같은 에러 데이터 핸들링 기능 추가
        String[] columns = null;

        try {
            columns = line.split(",");

            if (columns.length != 3) {
                logger.log(Level.WARNING, "illegal csv file syntax");
                throw new RuntimeException();
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("illegal csv file syntax.");
        }

        return validateLine(columns[0], columns[1], columns[2]);
    }

    public List<BankTransaction> parseLinesFrom(final List<String> lines) {
//        List<BankTransaction> bankTransactions = new ArrayList<BankTransaction>();
//
//        for(String line: lines) {
//            bankTransactions.add(parseFrom(line));
//        }
        logger.log(Level.FINE, "processing {0} entries in loop", lines.size());

        return lines.stream().map(this::parseFrom).collect(Collectors.toList());
    }


}
