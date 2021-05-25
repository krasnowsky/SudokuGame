package JavaFX.i18n;
import java.util.ListResourceBundle;

public class Bundle_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "authors"   , "Authors:" },
            { "author1"   , "Wojciech Krasnowski" },
            { "author2", "Maria Golinska" },
            { "version"   , "Version:" },
            { "versionNumber", "1.0" },
            { "aboutText", "Game of sudoku created for the sake of Component Programming course" },
    };
}
