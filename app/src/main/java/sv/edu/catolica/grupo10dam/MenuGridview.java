package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}