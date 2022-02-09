package com.example.primediagnosticcenter.screens.homepage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.primediagnosticcenter.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class DoctorSliderAdapter extends SliderViewAdapter<DoctorSliderAdapter.Holder> {

    int[] images;
    int[] names;
    int[] details;

    public DoctorSliderAdapter(int[] images, int[] names, int[] details) {

        this.images = images;
        this.names = names;
        this.details = details;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_doctor, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {

        viewHolder.imageView.setImageResource(images[position]);
        viewHolder.nameView.setText(names[position]);
        viewHolder.detailView.setText(details[position]);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder {

        ImageView imageView;
        TextView nameView, detailView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            nameView = itemView.findViewById(R.id.doctor_name);
            detailView = itemView.findViewById(R.id.doctor_details);

        }
    }

}