package sv.edu.catolica.grupo10dam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorPlatos extends BaseAdapter {
    private Context context;
    private ArrayList<EntidadPlatos> listaplatos;

    public AdaptadorPlatos(Context context, ArrayList<EntidadPlatos> listaplatos) {
        this.context = context;
        this.listaplatos = listaplatos;
    }

    @Override
    public int getCount() {
        return listaplatos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaplatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"InflateParams", "ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntidadPlatos itemPlatos = (EntidadPlatos) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.formato_listview, null);
        ImageView imagen = convertView.findViewById(R.id.platoImagen);

        TextView plato = convertView.findViewById(R.id.txtPlato);
        TextView descripcion_plato = convertView.findViewById(R.id.txtDescrip);
        TextView precio_plato = convertView.findViewById(R.id.txtPreciop);

        int id = itemPlatos.getId_plato();
        String name = itemPlatos.getPlato();
        String des = itemPlatos.getDescripcion();
        Double price = itemPlatos.getPrecio();

        plato.setText(itemPlatos.getPlato());
        descripcion_plato.setText(itemPlatos.getDescripcion());
        precio_plato.setText(("Precio: $"+itemPlatos.getPrecio()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarDatos(id, name, des, price);
            }
        });

        return convertView;
    }

    private void EnviarDatos(int id, String name, String des, double price) {
        Intent intent = new Intent(context, ElementoDetallado.class);
        intent.putExtra("idPlato", id);
        intent.putExtra("plato", name);
        intent.putExtra("descrip", des);
        intent.putExtra("precio", price);
        context.startActivity(intent);
    }
}
