package primitive.lifecycles;

import com.google.inject.Guice;
import javafx.stage.Stage;
import primitive.BaseApplication;

public class AnyApplication extends BaseApplication {
    public AnyApplication() {
        super();
    }

    @Override
    public void init() throws Exception {
        AnyApplication.setInjector(Guice.createInjector(AnyModule.getInstance()));
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {}

    public static void main(String[] args) {
        launch(args);
    }
}
