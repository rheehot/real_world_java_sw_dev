package bank_statements_analyzer_kiss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String... args) throws IOException {

        // conversion) string path to java path object
        final Path path = Paths.get(RESOURCES + args[0]);

        // read file
        final List<String> lines = Files.readAllLines(path);

        double total = 0d;
        double jan_total = 0d;

        //make formatter for date
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // calculate total and specific month's data
        for(final String line: lines) {
            final String[] columns = line.split(",");

            // conversion) string to double
            final Double amount = Double.parseDouble(columns[1]);
            total += amount;

            // conversion) string date to java date object
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if (date.getMonth() == Month.JANUARY) {
                jan_total += amount;
            }

        }
        System.out.println("total is " + total + " and january total is " + jan_total);
    }

}
