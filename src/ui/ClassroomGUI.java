package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Classroom;

public class ClassroomGUI implements Initializable {

    private final ObservableList<String> browserList
            = FXCollections.observableArrayList("Chrome", "Opera", "Brave");

    private Classroom classroom;

    @FXML
    private TextField usernameFieldLogin;
    @FXML
    private TextField usernameFieldRegister;
    @FXML
    private TextField passwordFieldLogin;
    @FXML
    private TextField passwordFieldRegister;
    @FXML
    private TextField profilePhotoRegister;
    @FXML
    private RadioButton maleGenderRegister;
    @FXML
    private RadioButton femaleGenderRegister;
    @FXML
    private RadioButton otherGenderRegister;
    @FXML
    private CheckBox softwareCareerCheck;
    @FXML
    private CheckBox telematicCareerCheck;
    @FXML
    private CheckBox industrialCareerCheck;
    @FXML
    private DatePicker birthdayRegister;
    @FXML
    private ChoiceBox fvBrowserRegister;
    @FXML
    private Pane mainPane;

    public ClassroomGUI(Classroom cr) {
        classroom = cr;
    }

    @FXML
    public void registerStage() throws IOException {
        closeStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        fvBrowserRegister.setItems(browserList);
        //profilePhotoRegister.setEditable(false);
        newStage(root);
    }

    @FXML
    public void loginStage() throws IOException {
        closeStage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        newStage(root);
    }

    @FXML
    public void accountListStage() throws IOException {
        if (!(usernameFieldLogin.getText().equals("")) && !(passwordFieldLogin.getText().equals(""))) {
            closeStage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            newStage(root);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ooops, you need to fill all the fields!");
            alert.showAndWait();
        }
    }

    @FXML
    public void createAccountButton() throws IOException {
        if (!(usernameFieldRegister.getText().equals("")) && !(passwordFieldRegister.getText().equals("")) && !(profilePhotoRegister.getText().equals(""))
                && (!(maleGenderRegister.isSelected() == false) || !(femaleGenderRegister.isSelected() == false) || !(otherGenderRegister.isSelected() == false))
                && (!(softwareCareerCheck.isSelected() == false)) || !(telematicCareerCheck.isSelected() == false) || !(industrialCareerCheck.isSelected() == false)
                && !(birthdayRegister.getValue() == null) && !(fvBrowserRegister.getValue() == null)) {
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Doned");
            alert.setHeaderText(null);
            alert.setContentText("The new account has been created!");
            alert.showAndWait();
            loginStage();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ooops, you need to fill all the fields!");
            alert.showAndWait();
        }
    }

    public void closeStage() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void newStage(Parent root) {
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Classroom");
        newStage.setResizable(false);
        newStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
