package ru.a799000.alexander.countweighting.ui.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmList;
import ru.a799000.alexander.countweighting.R;
import ru.a799000.alexander.countweighting.mvp.model.intities.Barcode;
import ru.a799000.alexander.countweighting.mvp.model.intities.Product;

/**
 * Created by Alex on 14.06.2017.
 */

public class AdapretProdact extends RealmBaseAdapter<Product> {


    public AdapretProdact(@Nullable OrderedRealmCollection<Product> data) {
        super(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {


            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product_new, parent, false);



            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            final Product item = adapterData.get(position);
            viewHolder.setProduct(item);
        }
        return convertView;
    }

    private static class ViewHolder  {

        //@BindView(R.id.tvId)
        TextView tvId;
        //@BindView(R.id.tvName)
        TextView tvName;
        //@BindView(R.id.tvWeight)
        TextView tvWeight;
        //@BindView(R.id.tvUnit)
        TextView tvUnit;
        //@BindView(R.id.tvSites)
        TextView tvSites;

        TextView txtItem;

        Product mProduct;
        float mWeight;
        int mSites;




        public ViewHolder(View view) {
            //ButterKnife.bind(this, view);
            //tvId = (TextView) view.findViewById(R.id.tvId);
            //tvName = (TextView) view.findViewById(R.id.tvName) ;
            //tvWeight = (TextView) view.findViewById(R.id.tvWeight) ;
            //tvUnit = (TextView) view.findViewById(R.id.tvUnit) ;
            //tvSites = (TextView) view.findViewById(R.id.tvSites) ;
            txtItem = (TextView) view.findViewById(R.id.txtItem) ;

            //view.setOnClickListener(this);
        }

        private void init(){
            RealmList<Barcode> barcodes = mProduct.getBarcodes();

            for(Barcode barcode: barcodes){
                mSites = mSites + barcode.getPlaces();
                mWeight = mWeight + barcode.getWeight();
            }
        }

        public void setProduct(Product product) {
            mProduct = product;
            init();
            //tvId.setText(Long.toString(product.getId()));
            //tvName.setText(product.getName()==null?"": product.getName());
            //tvUnit.setText(product.getUnit()==null?"": product.getUnit());
            //tvSites.setText(mSites==0?"":Integer.toString(mSites));
            //tvWeight.setText(mWeight==0?"":Float.toString(mWeight));
            txtItem.setText(product.getName()==null?"": product.getName() + " (" +product.getId() +")");
        }



    }


}
