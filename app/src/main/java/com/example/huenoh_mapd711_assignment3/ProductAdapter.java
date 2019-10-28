package com.example.huenoh_mapd711_assignment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context mContext;
    private List<Product> productsList = new ArrayList<>();

    public ProductAdapter(@NonNull Context context, @LayoutRes ArrayList<Product> list) {
        super(context, 0 , list);
        mContext = context;
        productsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item_product, parent,false);

        Product currentProduct = productsList.get(position);

        //TODO : add product image to DB it's temporary image
        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_product);
        image.setImageResource(R.drawable.iphone_xr);

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentProduct.getProductName());

        TextView price = (TextView) listItem.findViewById(R.id.textView_price);
        price.setText("$" + Double.toString(currentProduct.getPrice()));

        TextView quantity = (TextView) listItem.findViewById(R.id.textView_quantity);
        quantity.setText("Quantity: " + Integer.toString(currentProduct.getQuantity()));

        return listItem;
    }
}