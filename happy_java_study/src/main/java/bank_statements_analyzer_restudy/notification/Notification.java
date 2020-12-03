package bank_statements_analyzer_restudy.notification;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private final List<String> errors = new ArrayList<String>();

    public void addError(final String message) {
        errors.add(message);
    }

    public boolean hadErrors() {
        return !errors.isEmpty();
    }

    public String errorMessage() {
        return errors.toString();
    }

    public List<String> getErrors() {
        return errors;
    }
}
