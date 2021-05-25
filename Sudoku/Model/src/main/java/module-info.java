module Model {
    requires commons.lang3;

    opens sdk to commons.lang3;
    exports sdk to View;
}