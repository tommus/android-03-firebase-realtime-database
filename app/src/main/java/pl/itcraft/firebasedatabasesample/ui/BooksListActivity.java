package pl.itcraft.firebasedatabasesample.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import pl.itcraft.firebasedatabasesample.R;
import pl.itcraft.firebasedatabasesample.model.Book;
import pl.itcraft.firebasedatabasesample.ui.item.BookItem;

@SuppressLint("LogNotTimber")
public class BooksListActivity extends AppCompatActivity {

	private static final String TAG = BooksListActivity.class.getSimpleName();

	private FirebaseDatabase  database  = FirebaseDatabase.getInstance();
	private DatabaseReference reference = database.getReference();

	private RecyclerView recyclerView;
	final ItemAdapter<BookItem> itemAdapter = new ItemAdapter<>();
	final FastAdapter           fastAdapter = FastAdapter.with(itemAdapter);

	private ChildEventListener childEventListener;
	private ValueEventListener valueEventListener;

	private HashMap<Integer, BookItem> books = new HashMap<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books_list);
		setupUI();
	}

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		childEventListener = reference.child("public").child("books").addChildEventListener(new BooksEventListener());
		valueEventListener = reference.child("public").child("books").addValueEventListener(new BookValueListener());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		reference.removeEventListener(childEventListener);
		reference.removeEventListener(valueEventListener);
	}

	private void setupUI() {
		recyclerView = findViewById(R.id.books);
		recyclerView.setAdapter(fastAdapter);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
	}

	class BooksEventListener implements ChildEventListener {

		@Override
		public void onChildAdded(DataSnapshot dataSnapshot, String s) {
			addBookAndUpdate(dataSnapshot);
		}

		@Override
		public void onChildChanged(DataSnapshot dataSnapshot, String s) {
			addBookAndUpdate(dataSnapshot);
		}

		@Override
		public void onChildRemoved(DataSnapshot dataSnapshot) {
			addBookAndUpdate(dataSnapshot);
		}

		@Override
		public void onChildMoved(DataSnapshot dataSnapshot, String s) {
			addBookAndUpdate(dataSnapshot);
		}

		@Override
		public void onCancelled(DatabaseError databaseError) {
			Log.e(TAG, databaseError.getMessage());
		}

		private void addBookAndUpdate(DataSnapshot dataSnapshot) {
			final Book book = dataSnapshot.getValue(Book.class);
			if (book != null) {
				Log.d(TAG, dataSnapshot.getKey());
				final Integer key = Integer.parseInt(dataSnapshot.getKey());
				books.put(key, new BookItem(book));
				itemAdapter.setNewList(new ArrayList<>(books.values()));
				fastAdapter.notifyDataSetChanged();
			}
		}
	}

	class BookValueListener implements ValueEventListener {

		@Override
		public void onDataChange(DataSnapshot dataSnapshot) {
			// No-op.
		}

		@Override
		public void onCancelled(DatabaseError databaseError) {
			Log.e(TAG, databaseError.getMessage());
		}
	}
}
