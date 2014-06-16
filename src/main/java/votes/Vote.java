package votes;

public class Vote {
    private String email;
    private char mood;

    public Vote(String email, char mood) {
        this.email = email;
        this.mood = mood;
    }

    public String getEmail() {
        return email;
    }

    public char getMood() {
        return mood;
    }
}
