package parser;

import lombok.Getter;

@Getter
public class Constants {
    public static final String OBSERVER_NAME_ROW_SELECTOR = "#center_column table tbody tr:nth-child(2) td";
    public static final String OBSERVER_CODE_ROW_SELECTOR = "#center_column table tbody tr:nth-child(3) td";
    public static final int COLUMN_COUNT_BEFORE_SUBSTANCE_CONCENTRATION = 2;
    public static final int TIME_ZONE = 4;
    public static final String URL_TO_PARSE = "http://www.gidrometeorologiya.ruln.ru/page224.html";

    public static final String FILE_NAME = "measurement.rss";
}
