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

        int idMenu = itemMenus.getId_menu();
        String nameMenu = itemMenus.getMenu();
        String descrip = itemMenus.getDescripcion();
        int estado = itemMenus.getEstado();

        // idMenu.setText(itemMenus.getId_menu());
        menu.setText(itemMenus.getMenu());
        descripcion.setText(itemMenus.getDescripcion());
        // estado.setText(itemMenus.getEstado());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarDatos(nameMenu, descrip, idMenu, estado);
            }
        });

        return convertView;
    }

    private void EnviarDatos(String menu, String descrip, int idMenu, int estado) {
        Intent intent = new Intent(context, MenuListview.class);
        intent.putExtra("nombre", menu);
        intent.putExtra("descrip", descrip);
        intent.putExtra("idMenu", idMenu);
        intent.putExtra("estado", estado);
        context.startActivity(intent);
    }
}
