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

public class Login extends AppCompatActivity {

    private EditText etUsuario, etContra;
    public String usuario, contra, url, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario=findViewById(R.id.txtUsuario);
        etContra=findViewById(R.id.txtContra);
    }

    public void verificarDatos(View view){

        usuario=etUsuario.getText().toString();
        contra=etContra.getText().toString();

        url="https://fairylike-drill.000webhostapp.com/login.php";

        RequestParams parametros= new RequestParams();
        parametros.put("usu",usuario);
        parametros.put("pas",contra);

        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    String respuesta=new String(responseBody);

                    try {
                        JSONObject obj= new JSONObject(respuesta);

                        if (obj.names().get(0).equals("exito")){
                            resultado=obj.getString("exito")+" ";
                            resultado+=obj.getString("usuario");
                        }else{
                            resultado=obj.getString("error");
                        }


                        Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();

                        Intent intent= new Intent(getApplicationContext(), MenuPrincipal.class);
                        startActivity(intent);
                    } catch (JSONException e) {
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

    public void registrarse(View view){
        Intent intent= new Intent(getApplicationContext(), Registrarse.class);
        startActivity(intent);

    }
}