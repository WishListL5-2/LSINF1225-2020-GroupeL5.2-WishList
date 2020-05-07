package be.LaPireTeam.wishlist.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.LaPireTeam.wishlist.DAO.DAO;
import be.LaPireTeam.wishlist.DAO.DAOFactory;
import be.LaPireTeam.wishlist.DAO.IDUtility;
import be.LaPireTeam.wishlist.Objects.List;
import be.LaPireTeam.wishlist.Objects.Session;
import be.LaPireTeam.wishlist.Objects.Wish;
import be.LaPireTeam.wishlist.R;

public class NewWishActivity extends AppCompatActivity {
    //public static final String EXTRA_ARGUMENT_LIST_ID = "be.LaPireTeam.wishlist.EXTRA_LIST_ID";
    //int list_id;
    List currentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wish);
        /*
        Intent intent = getIntent();
        list_id = intent.getIntExtra(SeeWishesActivity.EXTRA_ARGUMENT_LIST_ID, -1);
         */
        currentList = Session.getInstance().getLastClickedList();

    }

    public void createNewWish(View view) {
        EditText inputName = (EditText) findViewById(R.id.new_wish_name_inputfield);
        String name = inputName.getText().toString();
        EditText inputPriority = (EditText) findViewById(R.id.new_wish_priority_inputfield);
        int priority = Integer.parseInt(inputPriority.getText().toString());
        EditText inputComments = (EditText) findViewById(R.id.new_wish_comments_inputfield);
        String comments = inputComments.getText().toString();
        EditText inputProduct = (EditText) findViewById(R.id.new_wish_product_inputfield);
        String product = inputName.getText().toString();
        Wish w = new Wish(IDUtility.getNewWishID(new DAO(this)), name, priority, comments, product);
        DAOFactory.WishDAO(this).insert_wish(w, currentList.ID);
        Intent intent = new Intent(this, SeeWishesActivity.class);
        //intent.putExtra(EXTRA_ARGUMENT_LIST_ID, list_id);
        startActivity(intent);
    }
}
