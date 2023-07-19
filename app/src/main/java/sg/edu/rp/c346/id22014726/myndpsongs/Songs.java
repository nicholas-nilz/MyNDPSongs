package sg.edu.rp.c346.id22014726.myndpsongs;

import androidx.annotation.NonNull;

public class Songs {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Songs(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        String StarST = "";
        if(stars == 1) {
            StarST = "*";
        } else if(stars == 2) {
            StarST = "**";
        } else if(stars == 3) {
            StarST = "***";
        } else if(stars == 4) {
            StarST = "****";
        } else if(stars == 5) {
            StarST = "*****";
        }
        return title + "\n" + singers + " - " + year + "\n" + StarST;
    }
}
