package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Be sure to add {@code --add-reads app=ALL-UNNAMED} to VM options
 */
@SpringBootApplication
public class TryJavafxSpringBootApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        applicationContext = SpringApplication.run(TryJavafxSpringBootApplication.class,
            getParameters().getRaw().toArray(new String[0])
        );
    }

    @Override
    public void stop() {
        applicationContext.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                TryJavafxSpringBootApplication.class.getResource("main-view.fxml"),
                null, null,
                this::loadController
        );
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private Object loadController(Class<?> controllerClass) {
        return applicationContext.getBean(controllerClass);
    }
}
