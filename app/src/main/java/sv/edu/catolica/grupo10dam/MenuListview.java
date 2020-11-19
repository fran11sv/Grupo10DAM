package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuListview extends AppCompatActivity {
    private ListView lvItems;
    private Adaptador adaptador;
    private ArrayList<Entidad> arrayentidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_listview);

         lvItems = findViewById(R.id.listView);
         arrayentidad = GetArrayItems();
         adaptador = new Adaptador(this, arrayentidad);
         lvItems.setAdapter(adaptador);

         lvItems.setOnItemClickListener((parent, view, position, id) -> {
             Intent intent = new Intent(MenuListview.this, ElementoDetallado.class);
             intent.putExtra("objData", arrayentidad.get(position));
             startActivity(intent);
         });
    }

    private ArrayList<Entidad> GetArrayItems(){
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.ensalada,"Plato No 1", " Este es el Plato No. 1", "$20.00"));
        listItems.add(new Entidad(R.drawable.sopa,"Plato No 2", " Este es el Plato No. 2", "$10.00"));
        listItems.add(new Entidad(R.drawable.pastel,"Plato No 3", " Este es el Plato No. 4", "$40.00"));
        listItems.add(new Entidad(R.drawable.flan,"Plato No 4", " Este es el Plato No. 5", "$5.00"));

        return listItems;
    }
}
