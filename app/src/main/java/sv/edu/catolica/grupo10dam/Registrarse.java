package sv.edu.catolica.grupo10dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Registrarse extends AppCompatActivity {
    private EditText etNombres, etApellidos,etUsuario, etContra, etTelefono;
    public String nombres, apellidos, usuario, contra, telefono, url, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        etNombres=findViewById(R.id.edtNombres);
        etApellidos=findViewById(R.id.edtApellidos);
        etUsuario=findViewById(R.id.edtUsuario);
        etContra=findViewById(R.id.edtContrasenia);
        etTelefono=findViewById(R.id.edtTelefono);
    }

    public void registrarCliente(View view){

        nombres=etNombres.getText().toString();
        apellidos=etApellidos.getText().toString();
        usuario=etUsuario.getText().toString();
        contra=etContra.getText().toString();
        telefono=etTelefono.getText().toString();

        url="https://fairylike-drill.000webhostapp.com/registrarClientes.php";

        RequestParams parametros= new RequestParams();
        parametros.put("nombres",nombres);
        parametros.put("apellidos",apellidos);
        parametros.put("usu",usuario);
        parametros.put("contrasenia",contra);
        parametros.put("telefono",telefono);

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
                        }else{
                            resultado=obj.getString("error");
                        }

                        Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();

                        Intent intent= new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
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