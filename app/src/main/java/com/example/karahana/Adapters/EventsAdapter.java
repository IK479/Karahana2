package com.example.karahana.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.karahana.Interfaces.CallBack_eventProtocol;
import com.example.karahana.managers.Models.PartyCard;
import com.example.karahana.managers.Models.PartyList;
import com.example.karahana.R;

import java.util.Date;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private int imageStore;
    private Context context;
    private long currentTime;
    private PartyList partiesList;
    private final String FAILED_ENTER_THE_PARTY = "The party hasn't started yet!";
    private CallBack_eventProtocol callBack_eventProtocol;


    public EventsAdapter(Context context, PartyList partyLists) {
        this.context = context;
        this.partiesList = partyLists;
        currentTime = new Date().getTime();
    }

    public void setCallBack_eventProtocol(CallBack_eventProtocol callBack_eventProtocol){
        this.callBack_eventProtocol =  callBack_eventProtocol;
    }

    @Override
    public int getItemCount() {
        if (partiesList == null) {
            return 0;
        } else {
            return partiesList.getPartiesList().size();
        }
    }

    private PartyCard getItem(int pos){
        return partiesList.getPartiesList().get(pos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         PartyCard party = partiesList.getPartiesList().get(position);
         holder.partyName.setText(party.getPartyName());
         holder.partyImg.setImageResource(party.getBgImage());
         holder.day.setText(String.valueOf(party.getTime().getDay()));
         holder.month.setText(String.valueOf(party.getTime().getMonth()));
         holder.time.setText(String.valueOf(party.getTime().getFullTime()));
         holder.isPrivate.setText(party.isPrivate());
         holder.calenderImg.setImageResource(party.getCalenderImg());
         holder.enterToEvent.setTag(party);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView partyImg;
        private TextView partyName;
        private TextView day;
        private TextView month;
        private TextView isPrivate;
        private TextView time;
        private ImageView calenderImg;
        private Button enterToEvent;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        partyImg = itemView.findViewById(R.id.partyCard_cardViewImg);
        partyName = itemView.findViewById(R.id.partyCard_partyName);
        day = itemView.findViewById(R.id.partyCard_day);
        month = itemView.findViewById(R.id.partyCard_month);
        time = itemView.findViewById(R.id.partyCard_time);
        isPrivate = itemView.findViewById(R.id.partyCard_type);
        calenderImg = itemView.findViewById(R.id.partyCard_ic_calender);
        enterToEvent = itemView.findViewById(R.id.partyCard_btn_enterToEvent);

        enterToEvent.setOnClickListener(view -> {
            callBack_eventProtocol.partyEnter((PartyCard) view.getTag());
        });

        itemView.setOnClickListener(view -> callBack_eventProtocol.partyPos(partiesList.getPartiesList().get(getAdapterPosition())));

        }
    }
}
