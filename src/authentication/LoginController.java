package authentication;

import java.sql.SQLException;
import application.MainController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	public LoginController() {}
    private final ObjectProperty<User> user = new SimpleObjectProperty<>();

    public final ObjectProperty<User> userProperty() {
        return this.user;
    }

    public final authentication.User getUser() {
        return this.userProperty().get();
    }

    public final void setUser(final authentication.User user) {
        this.userProperty().set(user);
    }
    
    @FXML
    private TextField userNameField ;
    @FXML
    private PasswordField passwordField ;
    
    @FXML
    private Label errorLabel ;
    public MainController main = new MainController();
    @FXML
  	private Button loginBtn;

    @FXML
    private void ok(ActionEvent event){
        String userName = userNameField.getText();
        String password = passwordField.getText();
        if (authenticate(userName, password)) {
            setUser(new User(userName));
            errorLabel.setText("");
            main.loadScreen(loginBtn,"Main_scene.fxml");
        } else {
            errorLabel.setText("Incorrect login details");
        }
        clearFields();
    }

    public void logout() {
    	setUser(null);
    }
    
    @FXML
    private void cancel() {
        setUser(null);
        clearFields();
        errorLabel.setText("");
    }
    
    private boolean authenticate(String userName, String password) {
        // in real life, do real authentication...
        if (userName.isEmpty() || password.isEmpty()) {
            return false ;
        }
        JdbcDao jdbcDao = new JdbcDao();
        boolean flag = false;
		try {
			flag = jdbcDao.validate(userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (!flag) {
           return false;
        }
        return true ;
    }
    
    private void clearFields() {
        userNameField.setText("");
        passwordField.setText("");
    }
}