package utils;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class CatchAction extends Action.Simple {

    private final ExceptionMailer exceptionMailer;

    @Inject
    public CatchAction(ExceptionMailer exceptionMailer) {
        this.exceptionMailer = exceptionMailer;
    }

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        try {
            return delegate.call(ctx);
        } catch(Throwable e) {
            exceptionMailer.send(e);
            throw new RuntimeException(e);
        }
    }
}