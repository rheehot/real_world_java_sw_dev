package bank_statements_analyzer;

import java.util.List;

public interface BankStatementParse {
    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
