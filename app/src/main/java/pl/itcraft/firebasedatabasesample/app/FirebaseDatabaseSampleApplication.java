package pl.itcraft.firebasedatabasesample.app;

import android.app.Application;
import com.google.firebase.FirebaseApp;


public class FirebaseDatabaseSampleApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		this.initializeFirebaseDatabase();
	}

	private void initializeFirebaseDatabase() {
		FirebaseApp.initializeApp(this);
	}
}
