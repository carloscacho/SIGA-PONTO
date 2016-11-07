package me.dm7.barcodescanner.zxing.sample.controller.behavior;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.dm7.barcodescanner.zxing.sample.R;

/**
 * Created by CarlosEmilio on 27/09/2016.
 */
public class ViewHolderVoucher extends RecyclerView.ViewHolder{
    final TextView companyName;
    final TextView companyInfo;
    final ImageView companyCT;

    public ViewHolderVoucher(View view) {
        super(view);
        companyName = (TextView) view.findViewById(R.id.company_name);
        companyInfo = (TextView) view.findViewById(R.id.company_info);
        companyCT = (ImageView) view.findViewById(R.id.count_number);
    }
}
