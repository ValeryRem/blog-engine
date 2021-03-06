package main.response;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
// искусственный коммит 9.5.22

public class CalendarResponse {
    List<Integer> years;
    LinkedHashMap<LocalDate, Integer> posts;

    public CalendarResponse(List<Integer> years, LinkedHashMap<LocalDate, Integer> posts) {
        this.years = years;
        this.posts = posts;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public LinkedHashMap<LocalDate, Integer> getPosts() {
        return posts;
    }

    public void setPosts(LinkedHashMap<LocalDate, Integer> posts) {
        this.posts = posts;
    }
}
