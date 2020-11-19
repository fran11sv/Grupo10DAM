package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ElementoDetallado extends AppCompatActivity {
    private Entidad Item;
    private TextView nombre, descripcion, precio;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento_detallado);

        Item = (Entidad) getIntent().getSerializableExtra("objData");

        nombre = findViewById(R.id.txtNombre);
        imagen = findViewById(R.id.Imagen);
        descripcion = findViewById(R.id.txtDescripcion);
        precio = findViewById(R.id.txtPrecio);

        nombre.setText(Item.getNombre());
        imagen.setImageResource(Item.getImagen());
        descripcion.setText(Item.getDescripcion());
        precio.setText(Item.getPrecio());
    }
}