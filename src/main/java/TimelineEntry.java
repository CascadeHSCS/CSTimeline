public class TimelineEntry {
    private final String innovator;
    private final int year;
    private final String language;

    public TimelineEntry(final String innovator,
                         final int year,
                         final String language) {
        this.innovator = innovator;
        this.year = year;
        this.language = language;
    }

    public String getInnovator() {
        return innovator;
    }

    public int getYear() {
        return year;
    }

    public String getLanguage() {
        return language;
    }
}
