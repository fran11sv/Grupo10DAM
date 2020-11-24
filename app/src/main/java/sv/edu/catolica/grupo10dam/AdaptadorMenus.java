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

public class AdaptadorMenus extends BaseAdapter {
    private Context context;
    private ArrayList<EntidadMenus> lista;

    public AdaptadorMenus(Context context, ArrayList<EntidadMenus> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntidadMenus itemMenus = (EntidadMenus) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.formato_gridview, null);
        ImageView imagen = convertView.findViewById(R.id.menuImagen);
        //TextView idMenu = convertView.findViewById(R.id.txtIdMenu);
        TextView menu = convertView.findViewById(R.id.txtMenu);
        TextView descripcion = convertView.findViewById(R.id.txtDescripMenu);
        //TextView estado = convertView.findViewById(R.id.txtEstado);

        // idMenu.setText(itemMenus.getId_menu());
        menu.setText(itemMenus.getMenu());
        descripcion.setText(itemMenus.getDescripcion());
        // estado.setText(itemMenus.getEstado());
        return convertView;
    }
}
