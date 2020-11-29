package bank_statements_analyzer;

public class HtmlExporter implements Exporter {
    @Override
    public String export(final SummaryStatistics summaryStatistics) {
        String result = "<!doctype html>";
        result += "<html lang='en'>";
        result += "<body>";
        result += "<li>sum is " + summaryStatistics.getSum() +"</li>";
        result += "<li>max is " + summaryStatistics.getMax() +"</li>";
        result += "<li>min is " + summaryStatistics.getMin() +"</li>";
        result += "</body>";

        return result;
    }
}
