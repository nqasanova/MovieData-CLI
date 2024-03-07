public class Movie {
    private String Title;
    private String Genre;
    private String Release_Date;
    private String Overview;
    private String Popularity;
    private String Vote_Count;
    private String Vote_Average;
    private String Original_Language;
    private String Poster_Url;
    private int day;
    private int month;
    private int year;

    public Movie(String Title, String Genre, String Release_Date, String Overview, String Popularity, String Vote_Count,
                 String Vote_Average, String Original_Language, String Poster_Url){

        this.Title = Title;
        this.Genre = Genre;
        this.Release_Date = Release_Date;
        this.Overview = Overview;
        this.Popularity = Popularity;
        this.Vote_Count = Vote_Count;
        this.Vote_Average  = Vote_Average;
        this.Original_Language = Original_Language;
        this.Poster_Url = Poster_Url;

        String[] array = Release_Date.split("/");
        this.day = Integer.valueOf(array[1]);
        this.month = Integer.valueOf(array[0]);
        this.year = Integer.valueOf(array[2]);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getRelease_Date() {
        return Release_Date;
    }

    public void setRelease_Date(String Release_Date) {
        this.Release_Date = Release_Date;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverView(String OverView) {
        this.Overview = OverView;
    }

    public String getPopularity() {
        return Popularity;
    }

    public void setPopularity(String popularity) {
        Popularity = popularity;
    }

    public String getVote_Count() {
        return Vote_Count;
    }

    public void setVote_Count(String vote_Count) {
        Vote_Count = vote_Count;
    }

    public String getVote_Average() {
        return Vote_Average;
    }

    public void setVote_Average(String vote_Average) {
        Vote_Average = vote_Average;
    }

    public String getOriginal_Language() {
        return Original_Language;
    }

    public void setOriginal_Language(String original_Language) {
        Original_Language = original_Language;
    }

    public String getPoster_Url() {
        return Poster_Url;
    }

    public void setPoster_Url(String poster_Url) {
        Poster_Url = poster_Url;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "[Release_Date: " + getRelease_Date() + ", " +
                "Title: " + getTitle() + ", " +
                "Genre: " + getGenre() + ", " +
                "Overview: " + getOverview() + ", " +
                "Popularity: " + getPopularity() + ", " +
                "Vote_Count: " + getVote_Count() + ", " +
                "Vote_Average: " + getVote_Average() + ", " +
                "Original_Language: " + getOriginal_Language() + ", " +
                "Poster_Url: " + getPoster_Url() + "]";
    }
}
