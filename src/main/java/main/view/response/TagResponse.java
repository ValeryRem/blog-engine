package main.view.response;
// искусственный коммит 9.5.22
public class TagResponse {
    private final String name;
    private final double weight;

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public TagResponse(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
}