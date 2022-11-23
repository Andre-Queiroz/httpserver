package httpserver.worker;

public interface ThreadWorker {

    void execute(String queryParams);

    String getResult();

}
