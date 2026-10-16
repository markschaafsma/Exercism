public enum TravelMethod {

    WALKING ("by walking"),
    HORSEBACK ("on horseback");

    private final String description;

    TravelMethod(String s) {
        this.description = s;
    }

    public String getDescription() {
        return description;
    }
}
