package sv.edu.catolica.grupo10dam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Entidad> listItems;

    public Adaptador(Context context, ArrayList<Entidad> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad Item = (Entidad) getItem(position);

        convertView= LayoutInflater.from(context).inflate(R.layout.formato_listview, null);
        ImageView imagen = convertView.findViewById(R.id.Imagen);
        TextView nombre = convertView.findViewById(R.id.txtNombre);
        TextView precio = convertView.findViewById(R.id.txtPrecio);

        imagen.setImageResource(Item.getImagen());
        nombre.setText(Item.getNombre());
        precio.setText(Item.getPrecio());
        return convertView;
    }
}
