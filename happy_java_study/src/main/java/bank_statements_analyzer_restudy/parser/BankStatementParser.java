package bank_statements_analyzer_restudy.parser;

import bank_statements_analyzer_restudy.dao.BankTransaction;

import java.util.List;

public interface BankStatementParser {
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
