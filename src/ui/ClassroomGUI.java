package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI implements Initializable {

    private final ObservableList<String> browserList
            = FXCollections.observableArrayList("Chrome", "Opera", "Brave");

    private Classroom classroom;

    @FXML
    private Label userActive;
    @FXML
    private ImageView imageActive;
    @FXML
    private TextField usernameFieldLogin;
    @FXML
    private TextField usernameFieldRegister;
    @FXML
    private PasswordField passwordFieldLogin;
    @FXML
    private PasswordField passwordFieldRegister;
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
    @FXML
    private TableView<UserAccount> tvView;
    @FXML
    private TableColumn<UserAccount, String> tcUsername;
    @FXML
    private TableColumn<UserAccount, String> tcGender;
    @FXML
    private TableColumn<UserAccount, String> tcCareer;
    @FXML
    private TableColumn<UserAccount, String> tcBirthday;
    @FXML
    private TableColumn<UserAccount, String> tcBrowser;

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
        profilePhotoRegister.setEditable(false);
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
            if (validateUserLogin()) {
                closeStage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
                fxmlLoader.setController(this);
                Parent root = fxmlLoader.load();
                newStage(root);
                setUserActive();
                initializeTableView();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Ooops, the username and password given are incorrect!");
                alert.showAndWait();
            }
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
                && ((!(softwareCareerCheck.isSelected() == false)) || !(telematicCareerCheck.isSelected() == false) || !(industrialCareerCheck.isSelected() == false))
                && (birthdayRegister.getValue() != null) && (fvBrowserRegister.getValue() != null)) {

            createUser();
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

    @FXML
    public void browseProfilePhoto() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        fileChooser.setTitle("Open File");
        if (file != null) {
            profilePhotoRegister.setText(file.getAbsolutePath());
        }
    }

    public void closeStage() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    public void newStage(Parent root) {
        Stage newStage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("/css/Classroom.css").toExternalForm());
        newStage.setScene(scene);
        newStage.setTitle("Classroom");
        newStage.setResizable(false);
        newStage.show();
    }

    public void createUser() {
        String username = usernameFieldRegister.getText();
        String password = passwordFieldRegister.getText();
        String profilePath = profilePhotoRegister.getText();
        String gender;
        if (maleGenderRegister.isSelected()) {
            gender = maleGenderRegister.getText();
        } else if (femaleGenderRegister.isSelected()) {
            gender = femaleGenderRegister.getText();
        } else {
            gender = otherGenderRegister.getText();
        }
        String career = "";
        career += (softwareCareerCheck.isSelected()) ? softwareCareerCheck.getText() + ", " : "";
        career += (telematicCareerCheck.isSelected()) ? telematicCareerCheck.getText() + ", " : "";
        career += (industrialCareerCheck.isSelected()) ? industrialCareerCheck.getText() : "";
        String birthday = birthdayRegister.getValue().toString();
        String fvBrowser = fvBrowserRegister.getValue().toString();

        classroom.addUsers(username, password, profilePath, gender, career, birthday, fvBrowser);
    }

    public void initializeTableView() {
        ObservableList<UserAccount> userAccounts;
        userAccounts = FXCollections.observableArrayList(classroom.getAccounts());

        tvView.setItems(userAccounts);
        tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("username"));
        tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("gender"));
        tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("career"));
        tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("birthday"));
        tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("fvBrowser"));
    }

    public boolean validateUserLogin() {
        List<UserAccount> accounts = classroom.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (usernameFieldLogin.getText().equals(accounts.get(i).getUsername()) && passwordFieldLogin.getText().equals(accounts.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }

    public void setUserActive() {
        List<UserAccount> accounts = classroom.getAccounts();
        userActive.setText(usernameFieldLogin.getText());
        for (int i = 0; i < accounts.size(); i++) {
            if (usernameFieldLogin.getText().equals(accounts.get(i).getUsername())) {
                userActive.setText(accounts.get(i).getUsername());
                File file = new File(accounts.get(i).getProfilePath());
                Image image = new Image(file.toURI().toString());
                imageActive.setImage(image);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
