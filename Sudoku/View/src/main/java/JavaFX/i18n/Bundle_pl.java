package JavaFX.i18n;
import java.util.ListResourceBundle;

public class Bundle_pl extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "authors"   , "Autorzy:" },
            { "author1"   , "Wojciech Krasnowski" },
            { "author2", "Maria Golinska" },
            { "version"   , "Wersja:" },
            { "versionNumber", "1.0" },
            { "aboutText", "Gra sudoku stworzona na rzecz kursu Component Programming." },
    };
}