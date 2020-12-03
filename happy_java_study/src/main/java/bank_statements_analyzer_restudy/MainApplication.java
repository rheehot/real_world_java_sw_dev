package bank_statements_analyzer_restudy;


import bank_statements_analyzer_restudy.dao.BankTransaction;
import bank_statements_analyzer_restudy.dao.SummaryStatistics;
import bank_statements_analyzer_restudy.exporter.Exporter;
import bank_statements_analyzer_restudy.exporter.HtmlExporter;
import bank_statements_analyzer_restudy.parser.BankStatementCSVParser;
import bank_statements_analyzer_restudy.parser.BankStatementParser;
import bank_statements_analyzer_restudy.processor.BankStatementProcessor;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MainApplication {

    private static Logger logger;

    static {
        try {
            final InputStream inputStream = MainApplication.class
                    .getClassLoader()
                    .getResourceAsStream("Logger.properties");

            LogManager.getLogManager().readConfiguration(inputStream);
            logger = Logger.getLogger(MainApplication.class.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String... args) throws Exception {

        logger.log(Level.FINE, "hello");

        // 1. read file
        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = Files.readAllLines(path);

        // 2. 파싱.
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        // 3. 계산 및 내역확인, 통계 데이터 생성
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        double amount = bankStatementProcessor.calculateTotalAmount();
        System.out.println(amount);

        List<BankTransaction> result = bankStatementProcessor.findTransactionsGreaterThanEqual(100);

        for (BankTransaction bankTransaction: result) {
            System.out.println(bankTransaction.getDescription());
        }

        // 4. html export
        final Exporter exporter = new HtmlExporter();
        String message = exporter.export(bankStatementProcessor.summaryTransactions());
        System.out.println(message);
    }
}
