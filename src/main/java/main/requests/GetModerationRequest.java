package main.requests;

import java.io.Serializable;
// искусственный коммит 9.5.22
public class GetModerationRequest implements Serializable {
    Integer offset;
    Integer limit;
    String mode;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
