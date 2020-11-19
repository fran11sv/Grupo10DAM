package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MenuGridview extends AppCompatActivity {
    GridView gridView;
    private AdaptadorGrid adaptador;
    private ArrayList<Entidad> arrayentidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gridview);

        gridView = findViewById(R.id.gridView);
        arrayentidad = GetArrayItems();
        adaptador= new AdaptadorGrid(this, arrayentidad);
        gridView.setAdapter(adaptador);

        gridView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent( MenuGridview.this, MenuListview.class);
            intent.putExtra("objData", arrayentidad.get(position));
            startActivity(intent);
        });
    }

    private ArrayList<Entidad> GetArrayItems(){
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.desayuno,"Menu 1","",""));
        listItems.add(new Entidad(R.drawable.almuerzo,"Menu 2","",""));
        listItems.add(new Entidad(R.drawable.cena,"Menu 3","",""));
        listItems.add(new Entidad(R.drawable.varia,"Menu 4","",""));
        listItems.add(new Entidad(R.drawable.rapida,"Menu 5","",""));

        return listItems;
    }
}