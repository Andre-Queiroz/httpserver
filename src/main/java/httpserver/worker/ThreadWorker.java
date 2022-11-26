package httpserver.worker;

import calcparser.CalcOpData;

public interface ThreadWorker {

    void execute(CalcOpData opData);

}
