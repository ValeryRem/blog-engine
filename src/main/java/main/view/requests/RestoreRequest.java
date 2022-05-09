package main.view.requests;

import java.io.Serializable;
// искусственный коммит 9.5.22
public class RestoreRequest implements Serializable {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
