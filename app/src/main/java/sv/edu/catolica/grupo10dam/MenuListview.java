package sv.edu.catolica.grupo10dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MenuListview extends AppCompatActivity {
    private ListView lvItems;
    private AdaptadorPlatos adaptadorPlatos;
    private int idMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_listview);

        lvItems = findViewById(R.id.listView);
        obtenerPlato();

        Bundle datos = this.getIntent().getExtras();
        idMenu = datos.getInt("idMenu");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //String menu = datos.getString("nombre");
        //String descripcion = datos.getString("descrip");
        //int estado = datos.getInt("estado");

        //Toast toast = Toast.makeText(this, "Has seleccionado el menu :"+idMenu+" "+menu, Toast.LENGTH_SHORT);
        //toast.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.carrito:
                Intent intent3= new Intent(getApplicationContext(), MenulistvPedidos.class);
                startActivity(intent3);
                break;
            case R.id.cerrarSesion:
                Intent intent2= new Intent(getApplicationContext(), Login.class);
                startActivity(intent2);
                break;
            case R.id.cuenta:
                Intent intent= new Intent(getApplicationContext(),MenuGridview.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void obtenerPlato() {

        String url = "https://fairylike-drill.000webhostapp.com/verPlatos3.php";
        Bundle datos = this.getIntent().getExtras();
        idMenu = datos.getInt("idMenu");

        RequestParams parametro= new RequestParams();
        parametro.put("id_menu", idMenu);

        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post(url, parametro, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    listarPlatos(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarPlatos(String respuesta) {
    ArrayList <EntidadPlatos> listaplatos= new ArrayList<> ();
    try {
        JSONArray jsonArray = new JSONArray(respuesta);
        for (int i =0; i < jsonArray.length(); i++){
            EntidadPlatos p = new EntidadPlatos();
            p.setId_plato(jsonArray.getJSONObject(i).getInt("id_plato"));
            p.setId_menu(jsonArray.getJSONObject(i).getInt("id_menu"));
            p.setId_categoria(jsonArray.getJSONObject(i).getInt("id_categoria"));
            p.setPlato(jsonArray.getJSONObject(i).getString("plato"));
            p.setPrecio(jsonArray.getJSONObject(i).getDouble("precio"));
            p.setDescripcion(jsonArray.getJSONObject(i).getString("descripcion"));
            //p.setImg_plato(jsonArray.getJSONObject(i).getString("imag_plato"));
            p.setEstado(jsonArray.getJSONObject(i).getInt("estado"));
            listaplatos.add(p);
        }
        adaptadorPlatos = new AdaptadorPlatos(this, listaplatos);
        lvItems.setAdapter(adaptadorPlatos);
    }catch (Exception e){
        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
    }
    }
}