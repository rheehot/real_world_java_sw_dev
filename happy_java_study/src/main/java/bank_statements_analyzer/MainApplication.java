package bank_statements_analyzer;

import java.io.IOException;

public class MainApplication {

    public static void main(final String... args) throws IOException {
        final BankTransactionAnalyzer bankTransactionAnalyzer = new BankTransactionAnalyzer();
        final BankStatementParse bankStatementParse = new BankStatementCSVParser();

        bankTransactionAnalyzer.analyze(args[0], bankStatementParse);
    }
}
