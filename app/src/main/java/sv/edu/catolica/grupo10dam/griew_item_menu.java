package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class griew_item_menu extends AppCompatActivity {

    TextView name;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_griew_item_menu);

        name = findViewById(R.id.nombreDesayuno);
        imagen = findViewById((R.id.Imagen));

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        imagen.setImageResource(intent.getIntExtra("imagen", 0));
    }
}