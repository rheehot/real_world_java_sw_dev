package bank_statements_analyzer;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
