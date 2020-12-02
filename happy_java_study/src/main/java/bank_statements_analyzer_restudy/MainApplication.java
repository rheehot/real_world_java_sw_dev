package bank_statements_analyzer_restudy;


import bank_statements_analyzer_restudy.dao.SummaryStatistics;
import bank_statements_analyzer_restudy.parser.BankStatementCSVParser;
import bank_statements_analyzer_restudy.parser.BankStatementParser;
import bank_statements_analyzer_restudy.processor.BankStatementProcessor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

public class MainApplication {
    private static final String RESOURCES = "src/main/resources/";
    public static void main(final String... args) throws Exception {
        // 1. read file
        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = Files.readAllLines(path);

        // 2. 계산 및 내역확인, 통계 데이터 생성
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        System.out.println(bankStatementParser.parseLinesFrom(lines).toString());

        // 3. 계산 및 내역확인, 통계 데이터 생성
        //final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer(bankStatementParser);

        // 4. html export
        //final Exporter exporter = new HtmlExporter();

    }
}
