package l.chernenkiy.aqua;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ProgressDialog progressDialog = new ProgressDialog (this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Загрузка. Пожалуйста подождите..");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AboutUs.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
