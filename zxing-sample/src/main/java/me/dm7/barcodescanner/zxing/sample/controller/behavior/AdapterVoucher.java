package me.dm7.barcodescanner.zxing.sample.controller.behavior;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.sample.R;
import me.dm7.barcodescanner.zxing.sample.model.ListItemClientCompany;

/**
 * Created by CarlosEmilio on 27/09/2016.
 */
public class AdapterVoucher extends RecyclerView.Adapter {
    private List<ListItemClientCompany> listVoucher;
    private Context context;


    public AdapterVoucher(ArrayList<ListItemClientCompany> arrayVoucherIn, Context context) {
        listVoucher = arrayVoucherIn;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);

        ViewHolderVoucher holder = new ViewHolderVoucher(view);

        return holder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolderVoucher holder = (ViewHolderVoucher) viewHolder;

        ListItemClientCompany item  = listVoucher.get(position) ;

        holder.companyName.setText(item.getNameClientCompany());
        holder.companyInfo.setText("CT: " + item.getNumberCT() +"   Voucher: " + item.getVouchers() + "  Check-in: " + item.getCheckIn());
    }

    @Override
    public int getItemCount() {
        return listVoucher.size();
    }
}
