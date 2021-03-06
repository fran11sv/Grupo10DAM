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
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MenuGridview extends AppCompatActivity {
    private GridView gridView;
    private AsyncHttpClient cliente;
    private AdaptadorMenus adaptadorMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gridview);

        gridView = findViewById(R.id.gridView);
        cliente = new AsyncHttpClient();
        obtenerMenu();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
    private void obtenerMenu() {
        String url = "https://fairylike-drill.000webhostapp.com/verMenu.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    listarMenu(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarMenu(String respuesta) {
        ArrayList <EntidadMenus> lista= new ArrayList<> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);
            for (int i =0; i < jsonArray.length();i++){
                EntidadMenus m = new EntidadMenus();
                m.setId_menu(jsonArray.getJSONObject(i).getInt("id_menu"));
                m.setMenu(jsonArray.getJSONObject(i).getString("menu"));
                m.setDescripcion(jsonArray.getJSONObject(i).getString("descripcion"));
                m.setEstado(jsonArray.getJSONObject(i).getInt("estado"));
                lista.add(m);
            }
            adaptadorMenus = new AdaptadorMenus(this, lista);
            gridView.setAdapter(adaptadorMenus);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   try {
                       EntidadMenus p = lista.get(i);
                       Toast.makeText(getApplicationContext(),"Este es el menu de "+ p.getMenu(),Toast.LENGTH_LONG).show();
                   }catch (Exception e){
                       Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                   }
               }
           });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}