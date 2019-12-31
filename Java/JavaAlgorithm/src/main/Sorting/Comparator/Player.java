package main.Sorting.Comparator;

public class Player {
    private String name;
    private int Score;

    Player(String name, int score) {
        this.name = name;
        Score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
