package sdk;

public interface Observer {
    void update(boolean verify);

    boolean verify();
}
