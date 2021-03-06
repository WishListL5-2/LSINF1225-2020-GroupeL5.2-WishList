package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.R;

/**
 * Activité permettant la connexion et l'enregistrement des utilisateurs
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }

    public void gotoRegisterView(View view) {
        setContentView(R.layout.register);
    }

    public void gotoLoginView(View view) {
        setContentView(R.layout.login_page);
    }

    /**
     *   Lance l'activity profil si il y l'utilisateur est bien créé.
     *   Et termine l'activity en cours.
     */
    public void registerButton(View view) {
        User user = registerUser(view);
        if (user == null) {
            return;
        }
        Intent intent = new Intent(this, ProfilActivity.class);
        Intent intentParent = new Intent(this, MenuActivity.class);
        startActivity(intentParent);
        startActivity(intent);
    }

    /**
     *   Lance l'activity menu si il y un user, sinon met un message d'erreur.
     *   Et termine l'activity en cours;
     */
    public void loginButton(View view) {
        User user = loginUser(view);
        if (user == null) {
            return;
        }
        Session.getInstance().setU(user);
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     *   Vérifie que l'utilisateur existe et que le mot de passe est exacte.
     *   Return l'utilisateur si les informations sont bonnes sinon null.
     */
    private User loginUser(View view) {
        EditText usernameTxt = (EditText) findViewById(R.id.username_field_loginpage);
        EditText passwordTxt = (EditText) findViewById(R.id.password_field_loginpage);
        String username = usernameTxt.getText().toString();
        String password = passwordTxt.getText().toString();

        User user = DAOFactory.userDAO(this).login(username, password);

        TextView alerteText = findViewById(R.id.alerteTextLogin);
        alerteText.setVisibility(View.VISIBLE);
        if (user == null) {
            alerteText.setText("ERREUR UTILISATEUR INVALIDE");
            return null;
        }
        alerteText.setText("UTILISATEUR LOGIN");
        return user;
    }

    /**
     *   Enregistre le nouvelle utilisateur si tout les paremetres sont exactes
     *   sinon retourne null et affiche une erreur.
     */
    private User registerUser(View view) {
        EditText usernameInput = (EditText) findViewById(R.id.username_field_registerpage);
        EditText password1Input = (EditText) findViewById(R.id.password_field1_registerpage);
        EditText password2Input = (EditText) findViewById(R.id.password_field2_registerpage);
        String username = usernameInput.getText().toString().trim();
        String password1 = password1Input.getText().toString();
        String password2 = password2Input.getText().toString();
        TextView msgAlert = (TextView) findViewById(R.id.alerteTextRegister);
        if (username.length() == 0) {
            msgAlert.setText("Empty Username");
            msgAlert.setVisibility(View.VISIBLE);
            return null;
        }
        if (DAOFactory.userDAO(this).idAlreadyExists(username)) {
            msgAlert.setText("Username already in use");
            msgAlert.setVisibility(View.VISIBLE);
            return null;
        }
        if (!password1.equals(password2)) {
            msgAlert.setText("2 passwords aren't the same");
            msgAlert.setVisibility(View.VISIBLE);
            return null;
        }
        if (password1.length() == 0 || password2.length() == 0) {
            msgAlert.setText("Empty Password");
            return null;
        }
        User user = new User(username);
        user.setPassword(password1);
        Session.getInstance().setU(user);
        return user;
    }
}