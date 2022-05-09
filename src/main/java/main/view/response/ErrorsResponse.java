package main.view.response;
import java.util.Map;

public class ErrorsResponse {
    // искусственный коммит 9.5.22
    private Map<String, String> errors;

    public ErrorsResponse() {
    }

    public ErrorsResponse(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
