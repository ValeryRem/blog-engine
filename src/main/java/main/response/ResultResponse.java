package main.response;
// искусственный коммит 9.5.22

public class ResultResponse {
    private final boolean result;

    public ResultResponse(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}