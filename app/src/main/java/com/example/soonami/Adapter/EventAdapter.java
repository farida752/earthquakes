package com.example.soonami.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soonami.Model.Event;
import com.example.soonami.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    public interface onItemClickListener{
        void onArrowClick(boolean isExpandable,int position);
        void onSeeMoreClick (String url,int position);
    }

    public ArrayList<Event> data;
    onItemClickListener listener;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public EventAdapter(ArrayList<Event> data) {
        this.data = data;
    }



    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        EventViewHolder holder=new EventViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
    Event currentEvent= data.get(position);
        String place=currentEvent.getPlace ();
        int tsunami=currentEvent.getTsunami ();
        long time=currentEvent.getTime ();
        double magnitude= currentEvent.getMagnitude();

        holder.textEventData .setText(place);
        holder.textDateData.setText ( getDateString ( time ) );
        holder.textTsunamiData.setText (getTsunamiString ( tsunami ) );
        holder. textMagnitudeData.setText(String.valueOf(magnitude));
        if(currentEvent.isExpandable()){
            holder.detailsLayout.setVisibility(View.VISIBLE);
            holder.arrow.setImageResource(R.drawable.ic_arrow_drop_up);
        }
        else{
            holder.detailsLayout.setVisibility(View.GONE);
            holder.arrow.setImageResource(R.drawable.ic_arrow_drop_down);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getTsunamiString(int Tsunami){
        switch (Tsunami){
            case 0:
                return "No";
            case 1:
                return "Yes";
            default:
                return "Not_Avaliable";

        }
    }

    public String getDateString (long time){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat ("E, dd MMM YYYY 'At' hh:mm:ss z");
        return simpleDateFormat.format ( time );
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        TextView textEvent;
        TextView textEventData;
        ImageView arrow;
        LinearLayout detailsLayout;
        TextView textDate ;
        TextView textDateData ;
        TextView textTsunami ;
        TextView textTsunamiData ;
        TextView textMagnitude ;
        TextView textMagnitudeData;
        Button buttonSeeMore;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            textEvent= itemView.findViewById(R.id.textView);
            textEventData= itemView.findViewById(R.id.text_view_title);
            arrow=itemView.findViewById(R.id.arrow);
            detailsLayout=itemView.findViewById(R.id.details_layout);
            textDate =itemView.findViewById(R.id.text_date);
            textDateData =itemView.findViewById(R.id.text_view_date);
            textTsunami =itemView.findViewById(R.id.text_tsunami);
            textTsunamiData =itemView.findViewById(R.id.text_view_tsunami_alert);
            textMagnitude =itemView.findViewById(R.id.text_mag);
            textMagnitudeData =itemView.findViewById(R.id.text_view_magnitude);
            buttonSeeMore=itemView.findViewById(R.id.button_see_more);

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                   int  position=getAdapterPosition();
                   if(position!=RecyclerView.NO_POSITION){
                   Event currentEvent=data.get(position);
                    listener.onArrowClick(currentEvent.isExpandable(),position);}
                }}
            });
            buttonSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        int  position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                           Event currentEvent=data.get(position);
                           listener.onSeeMoreClick(currentEvent.getUrl(),position);}}
                }

            });

        }
    }
}
