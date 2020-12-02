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

public class AdaptadorPedidos extends BaseAdapter {
   private Context context;
   private ArrayList<EntidadPedidos> listapedidos;

    public AdaptadorPedidos(Context context, ArrayList<EntidadPedidos> listapedidos) {
        this.context = context;
        this.listapedidos = listapedidos;
    }

    @Override
    public int getCount() {
        return listapedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return listapedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EntidadPedidos itemPedidos = (EntidadPedidos) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.formato_listview_pedidos, null);
        ImageView imagen = convertView.findViewById(R.id.pedidoImagen);

        TextView idplato = convertView.findViewById(R.id.txtIDplato);
        //TextView cantidad = convertView.findViewById(R.id.txtCantidad);
        TextView fecha_pedido = convertView.findViewById(R.id.txtFecha_pedido);
        TextView total = convertView.findViewById(R.id.txtTotal);
        TextView com_pedido = convertView.findViewById(R.id.txtComPedido);

        int id = itemPedidos.getId_plato();
        String idString = Integer.toString(id);
        //int cant = itemPedidos.getCantidad();
        //String cantString = Integer.toString(cant);
        String date = itemPedidos.getFecha_pedido();
        Double tot = itemPedidos.getTotal();
        String totString = Double.toString(tot);
        String com_ped = itemPedidos.getCom_pedido();

        String idp = "Codigo del plato: "+idString;
        idplato.setText(idp);
        //cantidad.setText(cantString);
        String fecp = "Fecha del pedido: "+date;
        fecha_pedido.setText(fecp);
        String totp = "Total a pagar: "+totString;
        total.setText(totp);
        String comp = "Comentario del usuario: "+com_ped;
        com_pedido.setText(comp);

        return convertView;
    }
}
