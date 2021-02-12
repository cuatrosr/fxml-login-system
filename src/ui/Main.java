package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Classroom;

public class Main extends Application {

    private Classroom classroom;
    private ClassroomGUI classroomGUI;

    public Main() {
        classroom = new Classroom();
        classroomGUI = new ClassroomGUI(classroom);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        
        fxmlLoader.setController(classroomGUI);
        
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Classroom");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
