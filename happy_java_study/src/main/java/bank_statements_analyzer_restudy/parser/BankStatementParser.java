package bank_statements_analyzer_restudy.parser;

import bank_statements_analyzer_restudy.dao.BankTransaction;
import bank_statements_analyzer_restudy.notification.Notification;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(final String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
