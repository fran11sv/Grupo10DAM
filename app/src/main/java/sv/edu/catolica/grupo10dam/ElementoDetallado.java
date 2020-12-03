package sv.edu.catolica.grupo10dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class ElementoDetallado extends AppCompatActivity {
    public TextView txtNombreP, txtDescripcionP, txtPrecio, txtCantidad;
    private EditText edtComentarioUsu;
    public String nombre_plato, desc_plato, comentario, url, resultado;
    public Double precio_plato, total;
    public ImageView imagen;
    public int id_plato, id_usuario;
    public int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elemento_detallado);

        Bundle datos = this.getIntent().getExtras();
        id_plato = datos.getInt("idPlato");
        nombre_plato = datos.getString("plato");
        desc_plato = datos.getString("descrip");
        precio_plato = datos.getDouble("precio");

        txtNombreP = findViewById(R.id.txtNombrePlato);
        imagen = findViewById(R.id.ImagenPlato);
        txtDescripcionP = findViewById(R.id.txtDescripcionPlato);
        txtPrecio = findViewById(R.id.txtPrecioPlato);
        txtCantidad = findViewById(R.id.txtCantidad);

        txtNombreP.setText(nombre_plato);
        //imagen.setImageResource();
        txtDescripcionP.setText(desc_plato);

        String precioString = Double.toString(precio_plato);
        precioString = "Precio: $"+precioString;
        txtPrecio.setText(precioString);
        String cantidadString = Integer.toString(count);
        cantidadString = "Cantidad: $"+cantidadString;
        txtCantidad.setText(cantidadString);
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

    public void suma(View view) {
        count++;
        String cantidadString = Integer.toString(count);
        txtCantidad.setText(cantidadString);
    }

    public void resta(View view) {
        count--;
        String cantidadString = Integer.toString(count);
        txtCantidad.setText(cantidadString);
        /*if(count == 1){
            count--;
            cantidad.setText("" + count);
        } else {
            Toast toast = Toast.makeText(this, "No puede realizar un pedido de 0 platos", Toast.LENGTH_SHORT);
            toast.show();
            count = 1;
        }*/
    }

    public void realizarPedido(View view) {
        SharedPreferences idUsuario = getSharedPreferences(null, MODE_PRIVATE);
        id_usuario = idUsuario.getInt("id_usuario", 0);

        Bundle datos = this.getIntent().getExtras();
        id_plato = datos.getInt("idPlato");
        nombre_plato = datos.getString("plato");
        desc_plato = datos.getString("descrip");
        precio_plato = datos.getDouble("precio");
        edtComentarioUsu = findViewById(R.id.edtComentarioUsu);
        comentario = edtComentarioUsu.getText().toString();
        int estado = 0;
        total = precio_plato * count;

        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fecha = df.format(c.getTime());

        Toast toast = Toast.makeText(this, "usu"+id_usuario+"plat"+id_plato+"num"+count+"com"+comentario+"sta"+estado+"date"+fecha+"total"+total, Toast.LENGTH_SHORT);
        toast.show();

        url="https://fairylike-drill.000webhostapp.com/CompraComida.php";

        RequestParams parametros= new RequestParams();
        parametros.put("id_usuario", id_usuario);
        parametros.put("id_plato", id_plato);
        parametros.put("cantidad", count);
        parametros.put("fecha_pedido", fecha);
        parametros.put("total", total);
        parametros.put("com_pedido", comentario);
        parametros.put("estado", estado);

        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    String respuesta=new String(responseBody);
                    try {
                        JSONObject obj= new JSONObject(respuesta);
                        if (obj.names().get(0).equals("exito")){
                            resultado=obj.getString("exito");
                            Intent intent = new Intent(ElementoDetallado.this, MenulistvPedidos.class);
                            startActivity(intent);
                        }else{
                            resultado=obj.getString("error");
                        }
                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"Error en la conexión",Toast.LENGTH_LONG).show();
            }
        });
    }
}