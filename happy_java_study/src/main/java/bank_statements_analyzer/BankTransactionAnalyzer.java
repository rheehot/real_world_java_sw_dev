package bank_statements_analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzer {
    private static final String RESOURCE = "src/main/resources/";

    //removed
    //private static final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

    public void analyze(final String file_name, final BankStatementParse bankStatementParse) throws IOException {
        final Path path = Paths.get(RESOURCE + file_name);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParse.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("total transaction amount : " + bankStatementProcessor.calculateTotalAmount());

        System.out.println("total transaction in february : " + bankStatementProcessor.calculateTotalMonth(Month.FEBRUARY));

        System.out.println("total trasaction by category : " + bankStatementProcessor.claculateTotalCategory("tesco"));

        System.out.println("find trasaction greater than 300 : " + bankStatementProcessor.findTransactionGreaterThanEqual(300));
    }
}
