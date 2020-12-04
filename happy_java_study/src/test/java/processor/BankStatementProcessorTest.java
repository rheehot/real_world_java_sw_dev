package processor;

import bank_statements_analyzer_restudy.dao.BankTransaction;
import bank_statements_analyzer_restudy.parser.BankStatementCSVParser;
import bank_statements_analyzer_restudy.parser.BankStatementParser;
import bank_statements_analyzer_restudy.processor.BankStatementProcessor;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankStatementProcessorTest {
    private static final String RESOURCES = "src/main/resources/";

    private final BankStatementProcessor bankStatementProcessor;

    @BeforeClass
    public void setup() {
        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = iles.readAllLines(path);
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        List<BankTransaction> bankTransactions =  bankStatementParser.parseFrom(lines);
        bankStatementProcessor = new BankStatementProcessor(bankTransactions);

    }

    @Test
    public void succTest

}
