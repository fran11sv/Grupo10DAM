package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MenulistvPedidos extends AppCompatActivity {
    private ListView lvItemsPedidos;
    private AdaptadorPedidos adaptadorPedidos;
    private int id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulistv_pedidos);

        lvItemsPedidos = findViewById(R.id.listViewPedidos);
        obtenerPedido();
    }

    private void obtenerPedido() {

        String url = "https://fairylike-drill.000webhostapp.com/verPedidos.php";

        SharedPreferences idUsuario = getSharedPreferences(null, MODE_PRIVATE);
        id_usuario = idUsuario.getInt("id_usuario", 0);

        RequestParams parametro= new RequestParams();
        parametro.put("id_usuario", id_usuario);

        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post(url, parametro, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    listarPedidos(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarPedidos(String respuesta) {
        ArrayList<EntidadPedidos> listapedidos= new ArrayList<> ();
        try {
            JSONArray jsonArray = new JSONArray(respuesta);
            for (int i =0; i < jsonArray.length(); i++){
                EntidadPedidos p = new EntidadPedidos();
                p.setId_pedido(jsonArray.getJSONObject(i).getInt("id_plato"));
                p.setId_usuario(jsonArray.getJSONObject(i).getInt("id_usuario"));
                p.setId_plato(jsonArray.getJSONObject(i).getInt("id_plato"));
                p.setCantidad(jsonArray.getJSONObject(i).getInt("cantidad"));
                p.setFecha_pedido(jsonArray.getJSONObject(i).getString("fecha_pedido"));
                p.setTotal(jsonArray.getJSONObject(i).getDouble("total"));
                p.setCom_pedido(jsonArray.getJSONObject(i).getString("com_pedido"));
                p.setEstado(jsonArray.getJSONObject(i).getInt("estado"));
                listapedidos.add(p);
            }
            adaptadorPedidos = new AdaptadorPedidos(this, listapedidos);
            lvItemsPedidos.setAdapter(adaptadorPedidos);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}

