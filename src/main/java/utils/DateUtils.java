package utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat VIEW_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat FILENAME_DATE_FORMAT = new SimpleDateFormat("dd_MM_yyyy'.txt'");

    public enum SortOrder {
        ASC, DESC;
    }

    public static String format(Date date) {
        return VIEW_DATE_FORMAT.format(date);
    }

    public static Date parseFromFile(File file) {
        final String fileName = file.getName();
        try {
            return FILENAME_DATE_FORMAT.parse(fileName);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Comparator<File> fileSorter(final SortOrder sortOrder) {
        return new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                final Date dateFile1 = parseFromFile(file1);
                final Date dateFile2 = parseFromFile(file2);
                return sortOrder == SortOrder.ASC ? dateFile1.compareTo(dateFile2) : dateFile2.compareTo(dateFile1);
            }
        };
    }
}
