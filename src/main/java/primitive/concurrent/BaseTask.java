package primitive.concurrent;

import javafx.concurrent.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// todo doc
public abstract class BaseTask <V> extends Task<V> {
    // todo doc
    protected volatile static Log log;

    public BaseTask() {
        log = LogFactory.getLog(this.getClass());
        log.info(String.format("Initializing Task<%s>...", this.getClass().getCanonicalName()));
    }

    @Override
    protected void scheduled() {
        super.scheduled();
        log.debug(String.format("Task<%s> has been scheduled.", this.getClass().getCanonicalName()));
    }

    @Override
    protected void running() {
        log.info(String.format("Task<%s> is running...", this.getClass().getCanonicalName()));
        super.running();
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        log.info(String.format("Task<%s> has succeeded.", this.getClass().getCanonicalName()));
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        log.warn(String.format("Task<%s> has been cancelled.", this.getClass().getCanonicalName()));
    }

    @Override
    protected void failed() {
        super.failed();
        log.error(String.format("Task<%s> has failed due to %s. Message:\n%s",
                this.getClass().getCanonicalName(),
                this.getException().getClass().getCanonicalName(),
                this.getException().getMessage()));
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (mayInterruptIfRunning) {
            log.warn(String.format("Task<%s> is being cancelled with interruption.",
                    this.getClass().getCanonicalName()));
        } else {
            log.warn(String.format("Task<%s> is being cancelled.",
                    this.getClass().getCanonicalName()));
        }

        return super.cancel(mayInterruptIfRunning);
    }

    @Override
    protected void updateProgress(long workDone, long max) {
        log.debug(String.format("Task<%s> Progress: %d/%d",
                this.getClass().getCanonicalName(),
                workDone, max));
        super.updateProgress(workDone, max);
    }

    @Override
    protected void updateProgress(double workDone, double max) {
        log.debug(String.format("Task<%s> Progress: %.2f/%.2f",
                this.getClass().getCanonicalName(),
                workDone, max));
        super.updateProgress(workDone, max);
    }

    @Override
    protected void updateMessage(String message) {
        log.debug(String.format("Task<%s> Message: %s",
                this.getClass().getCanonicalName(),
                message));
        super.updateMessage(message);
    }

    @Override
    protected void updateTitle(String title) {
        log.debug(String.format("Task<%s> Title: %s",
                this.getClass().getCanonicalName(),
                title));
        super.updateTitle(title);
    }

    @Override
    protected void updateValue(V value) {
        super.updateValue(value);
        log.debug(String.format("Task<%s> Value: %s",
                this.getClass().getCanonicalName(),
                value.toString()));
    }
}
