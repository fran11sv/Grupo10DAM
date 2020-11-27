package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ElementoDetallado extends AppCompatActivity {
    //private Entidad Item;
    private TextView nombre, descripcion, precio;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento_detallado);

        Bundle datos = this.getIntent().getExtras();
        int id_plato = datos.getInt("idPlato");
        String namePlato = datos.getString("plato");
        String descrip = datos.getString("descrip");
        Double precioP = datos.getDouble("precio");

        Toast toast = Toast.makeText(this, "Has seleccionado el plato: "+id_plato+" "+namePlato, Toast.LENGTH_SHORT);
        toast.show();

        //Item = (Entidad) getIntent().getSerializableExtra("objData");

        nombre = findViewById(R.id.txtNombrePlato);
        imagen = findViewById(R.id.ImagenPlato);
        descripcion = findViewById(R.id.txtDescripcionPlato);
        precio = findViewById(R.id.txtPrecioPlato);

        nombre.setText(namePlato);
        //imagen.setImageResource();
        descripcion.setText(descrip);
        String precioString = Double.toString(precioP);
        precio.setText(precioString);
    }
}