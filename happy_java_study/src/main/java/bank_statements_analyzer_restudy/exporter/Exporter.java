package bank_statements_analyzer_restudy.exporter;

import bank_statements_analyzer_restudy.dao.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
