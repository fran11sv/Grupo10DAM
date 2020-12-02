package sv.edu.catolica.grupo10dam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                        int id_usu = obj.getInt("id_usuario");
                        EnviarID(id_usu);
                        if (obj.names().get(0).equals("exito")){
                            resultado=obj.getString("exito")+" ";
                            resultado+=obj.getString("usuario");
                            Intent intent= new Intent(getApplicationContext(), MenuGridview.class);
                            startActivity(intent);
                        }else{
                            resultado=obj.getString("error");
                        }
                        Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
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

    private void EnviarID(int id_usu) {
        SharedPreferences idUsuario = getSharedPreferences(null, MODE_PRIVATE);
        SharedPreferences.Editor editor = idUsuario.edit();
        editor.putInt("id_usuario", id_usu);
        editor.commit();
    }

    public void registrarse(View view){
        Intent intent= new Intent(getApplicationContext(), Registrarse.class);
        startActivity(intent);

    }
}