package be.LaPireTeam.wishlist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int Connexion = 0;
    private static final int Inscription = 1;


    public Button[] listeBoutons = new Button[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        // Pour l'instant, afin de compiler, j'ai alloué des ints à R.id.X dans la main. Il faudra évidemment syncroniser ça avec leur valeur réelle.
        listeBoutons[Connexion] = ((Button) this.findViewById(R.id.Connexion));
        listeBoutons[Inscription] = ((Button) this.findViewById(R.id.Inscription));
        for (int i = 0; i < listeBoutons.length; i++) {
            listeBoutons[i].setOnClickListener(this);
        }

    }


    int activite;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Connexion:
                activite = Connexion;
                break;
            case R.id.Inscription:
                activite = Inscription;
                break;
        }
    }

    public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {

        private static final int ValidInscription = 5;
        public Button[] listeBoutons = new Button[1];

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register);
            listeBoutons[ValidInscription] = ((Button) this.findViewById(R.id.ValidInscription));
            for (int i = 0; i < listeBoutons.length; i++) {
                listeBoutons[i].setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            }
        }
    }

    public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

        private static final int Wishlist = 2;
        private static final int Amis = 3;
        private static final int Profile = 4;
        public Button[] listeBoutons = new Button[50];

        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.menu);
            listeBoutons[Amis] = ((Button) this.findViewById(R.id.Amis));
            listeBoutons[Profile] = ((Button) this.findViewById(R.id.Profile));
            listeBoutons[Wishlist] = ((Button) this.findViewById(R.id.Wishlist));
            for (int i = 0; i < listeBoutons.length; i++) {
                listeBoutons[i].setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Profile:
                    activite = Profile;
                    break;
                case R.id.Amis:
                    activite = Amis;
                    break;
                case R.id.Wishlist:
                    activite = Wishlist;
                    break;
            }
        }
    }

    public class WishlistActivity extends AppCompatActivity implements View.OnClickListener{

        private static final int Liste1 = 6 ;
        private static final int Liste2 = 7 ;
        private static final int Liste3 = 8 ;
        private static final int Liste4 = 9 ;
        private static final int Liste5 = 10 ;

        // mauvaise tactique car infinie.

        @Override
        public void onClick(View v) {

        }

    }
}