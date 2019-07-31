package SnakeGUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


/**
 *
 *       -- PACMAN-REPLICA --
 *
 * Dette program er skrevet i forårssemesteret 2019 på RUC til kurset 'Essentiel Computing II' eksamen.
 * Programmet er skrevet ud fra en skabelon (JakeGame) - og er efterfølgende bygget op med
 * hjælp fra kursusmateriale samt undervisning.
 *
 * Selve pathfinding algoritmerne har jeg arbejdet med i et tidligere semester-projekt som
 * delvist var inspireret af Sebastian Lauges implementering i C# Unity: https://www.youtube.com/user/Cercopithecan
 *
 * Derudover har jeg oprettet en 'MathVector' klasse som er tilsvarende til PVector klassen i processing med inspiration fra
 * 'Shiffmann D., The Nature Of Code, 2012'. Dette er tydeligvis overkill, men var en ekstra udfordring jeg stillede mig selv.
 *
 *
 * @version 1.3
 * @author alextao
 *
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        //loader.load(getClass().getResource("SnakeGUI.fxml".ope));
        Parent root = loader.load();
        primaryStage.setTitle("JakeGame");

        Controller controller = (Controller) loader.getController();

        Scene scene = new Scene(root, 600,400);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed(event.getCode());
            }
        });
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("SnakeGUI/stylesheet.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
