package be.LaPireTeam.wishlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.User;
import be.LaPireTeam.wishlist.R;

/**
 * Activité permettant de voir les listes qu'un amis a partagé avec vous
 */
public class FriendsListsActivity extends AppCompatActivity {

    ListView listView;
    List[] lists;
    ArrayList<List> showedLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_lists);

        listView = (ListView) findViewById(R.id.listViewFriendsLists);
        User user = Session.getInstance().getU();

        User friend = Session.getInstance().getLastClickedFriend();
        TextView title = (TextView) findViewById(R.id.title_friends_lists);
        title.setText("Lists of " + friend.getID());

        lists = DAOFactory.listDAO(this).getLists(friend);

        ArrayList<String> listsNames = new ArrayList<>();
        showedLists = new ArrayList<>();
        if (lists != null) {
            for (List l : lists) {
                if (DAOFactory.listDAO(this).can_see(l, user)) {
                    listsNames.add(l.getName());
                    showedLists.add(l);
                }
            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listsNames);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Session.getInstance().setLastClickedList(showedLists.get(position));
                openSeeWishesActivity();
            }
        });
    }

    /**
     * fontion appelée pour lancer l'activité pour afficher les wish d'une liste sélectionée
     */
    public void openSeeWishesActivity() {
        Intent intent = new Intent(this, SeeWishesActivity.class);
        startActivity(intent);
    }
}
