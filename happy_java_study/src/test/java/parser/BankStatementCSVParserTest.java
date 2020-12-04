package parser;


import bank_statements_analyzer_restudy.parser.BankStatementCSVParser;
import bank_statements_analyzer_restudy.parser.BankStatementParser;
import bank_statements_analyzer_restudy.dao.BankTransaction;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;


public class BankStatementCSVParserTest {
    private final BankStatementParser bankStatementParser = new BankStatementCSVParser();
    private List<String> transactionTest = new ArrayList<>();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void failTestNullList(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("illegal csv file syntax");

        transactionTest.clear();
        transactionTest.add(null);
        List<BankTransaction> bankTransaction = bankStatementParser.parseLinesFrom(transactionTest);
    }

    // no data
    @Test
    public void failTestEmptyStringList(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("illegal csv file syntax");

        transactionTest.clear();
        transactionTest.add("");
        List<BankTransaction> bankTransaction = bankStatementParser.parseLinesFrom(transactionTest);
    }

    // wrong date pattern
    @Test
    public void failTestWrongDatePattern(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("invalid date time parsing.");

        transactionTest.clear();
        transactionTest.add("12-,1004,test descprion");
        List<BankTransaction> bankTransaction = bankStatementParser.parseLinesFrom(transactionTest);
    }

    // wrong date future
    @Test
    public void failTestWrongDateFuture(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("date cannot be in the future.");

        transactionTest.clear();
        transactionTest.add("12-12-2021,1004,test descprion");
        List<BankTransaction> bankTransaction = bankStatementParser.parseLinesFrom(transactionTest);
    }

    //wrong amount (not number)
    @Test
    public void failTestWrongAmount(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("there is no parsable double data.");

        transactionTest.clear();
        transactionTest.add("12-12-2019,wrong $dollar,test descprion");
        List<BankTransaction> bankTransaction = bankStatementParser.parseLinesFrom(transactionTest);
    }

    //wrong empty amount
    @Test
    public void failTestEmptyAmount(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("there is no parsable double data.");

        transactionTest.clear();
        transactionTest.add("12-12-2019,,test descprion");
        List<BankTransaction> bankTransaction = bankStatementParser.parseLinesFrom(transactionTest);
    }

    // wrong description (too long)
    @Test
    public void failTestLongDescription(){
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("description is to long");

        transactionTest.clear();
        transactionTest.add("12-12-2019,800,test descpriontest descpriontest descpriontest descpriontest descpriontest descpriontest descpriontest descpriontest descpriontest descprion");
        List<BankTransaction> bankTransaction = bankStatementParser.parseLinesFrom(transactionTest);
    }
}
