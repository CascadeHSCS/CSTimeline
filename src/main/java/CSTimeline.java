import java.util.List;

import static java.util.Arrays.asList;

public class CSTimeline {
    public static void main(String[] args) {
        final List<TimelineEntry> timeline =
                asList(
                        new TimelineEntry("Grace Hopper",1959, "COBOL")

                        // ,new TimelineEntry("Dennis Ritchie", 1972, "C")
                        // ,new TimelineEntry("Bill Gates", 1975, "Altair Basic")
                    //    ,new TimelineEntry("Bjarne Stroustrup", 1983, "C++"),
                        //   ,new TimelineEntry("James Gosling",1995,"Java")
//                        ,new TimelineEntry("Rasmus Lerdorf", 1995, "PHP")
//                        ,new TimelineEntry("Anders Hejlsberg", 2000, "C#")
                );
        System.out.println(generateTimelineColumns(timeline).toString());
        System.out.println(generateDateColumns(timeline).toString());
        System.out.println(generateLanguageColumns(timeline).toString());
        System.out.println(generateTimelineInnovators(timeline).toString());
        System.out.println(generateTimelineColumns(timeline).toString());

    }

    private static StringBuilder generateDateColumns(final List<TimelineEntry> timeline) {
        if(timeline.size() == 0) {
            return new StringBuilder();
        }
        if(timeline.size() < 2) {
            final int width = columnWidth(timeline.get(0), null);
            return new StringBuilder("|")
                    .append(String.format("%-" + width + "s",  timeline.get(0).getYear()))
                    .append("|");
        }
        final int width = columnWidth(timeline.get(0), timeline.get(1));
        return new StringBuilder("|")
                .append(String.format("%-" + width + "s", timeline.get(0).getYear()))
                .append("|")
                .append(generateDateColumns(timeline.subList(1, timeline.size())));
    }

    private static StringBuilder generateLanguageColumns(final List<TimelineEntry> timeline) {
        if (timeline.size() == 0) {
            return new StringBuilder();
        }
        if(timeline.size() < 2) {
            final int width = columnWidth(timeline.get(0), null);
            return new StringBuilder("|")
                    .append(String.format("%-" + width + "s", timeline.get(0).getLanguage()))
                    .append("|");
        }
        final int width = columnWidth(timeline.get(0), timeline.get(1));
        return new StringBuilder("|")
                .append(String.format("%-" + width + "s", timeline.get(0).getLanguage()))
                .append("|")
                .append(generateLanguageColumns(timeline.subList(1, timeline.size())));
    }

    private static StringBuilder generateTimelineColumns(final List<TimelineEntry> timeline) {
        if(timeline.size() == 0) {
            return new StringBuilder();
        }

        if(timeline.size() < 2) {
            final int width = columnWidth(timeline.get(0), null);
            return new StringBuilder("|").append(repeatString("-", width, null)).append("|");
        }

        final int width = columnWidth(timeline.get(0), timeline.get(1));
        return new StringBuilder("|")
                .append(repeatString("-", width, null))
                .append("|")
                .append(generateTimelineColumns(timeline.subList(1,timeline.size())));
    }

    private static StringBuilder generateTimelineInnovators(final List<TimelineEntry> timeline) {
        if(timeline.size() == 0) {
            return new StringBuilder();
        }
        if(timeline.size() < 2) {
            final int width = columnWidth(timeline.get(0), null);
            return new StringBuilder("|").append(String.format("%"+ width + "s", timeline.get(0).getInnovator()))
                    .append("|");
        }
        final int width = columnWidth(timeline.get(0), timeline.get(1));
        return new StringBuilder("|")
                .append(String.format("%-" + width + "s", timeline.get(0).getInnovator()))
                .append("|")
                .append(generateTimelineInnovators(timeline.subList(1, timeline.size())));
    }

    private static int columnWidth(final TimelineEntry current, final TimelineEntry next) {
        if(next == null) {
            return max(current.getLanguage().length(), current.getInnovator().length());
        }
        return max(current.getLanguage().length(),
               max(current.getInnovator().length(),
               (int)Math.floor(1.8 * (next.getYear() - current.getYear()))));
    }

    private static int max(int first, int second) {
        if(second > first ) {
            return second;
        }
        return first;
    }

    private static String repeatString(final CharSequence sequence, final int range, final StringBuilder seed) {
        if(range == 0){
            return seed.toString();
        }
        if(seed == null) {
            return repeatString(sequence, range - 1, new StringBuilder(sequence));
        }
        return repeatString(sequence, range - 1, seed.append(sequence));
    }
}
