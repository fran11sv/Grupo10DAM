package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MenuListview extends AppCompatActivity {
    private ListView lvItems;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_listview);

        lvItems = findViewById(R.id.listView);
        cliente = new AsyncHttpClient();
        obtenerMenu();

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
    ArrayList <EntidadMenus> lista= new ArrayList <EntidadMenus> ();
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
        ArrayAdapter <EntidadMenus> a =new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lvItems.setAdapter(a);
    }catch (Exception e){
        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
    }
    }
}