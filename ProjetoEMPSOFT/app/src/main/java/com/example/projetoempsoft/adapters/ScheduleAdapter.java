package com.example.projetoempsoft.adapters;

import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetoempsoft.R;
import com.example.projetoempsoft.models.Agendamento;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by nathan on 3/26/17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    List<Agendamento> agendamentoList;

    public ScheduleAdapter(List<Agendamento> list){
        agendamentoList = list;
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.schedule_list_item, parent, false);
        ScheduleAdapter.ViewHolder holder = new ScheduleAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        Agendamento current = agendamentoList.get(position);
        holder.scheduleType.setText(current.getStringTipoAgendamento());
        holder.scheduleStatus.setText(current.getStringStatus());
        holder.scheduleDate.setText(current.getFormatedData());
        holder.scheduleHour.setText("[" + current.getHora().toString() + "]");
    }

    @Override
    public int getItemCount() {
        return agendamentoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView scheduleType;
        TextView scheduleDate;
        TextView scheduleHour;
        TextView scheduleStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            scheduleType = (TextView) itemView.findViewById(R.id.cardScheduleType);
            scheduleDate = (TextView) itemView.findViewById(R.id.cardScheduleDate);
            scheduleHour = (TextView) itemView.findViewById(R.id.cardScheduleHour);
            scheduleStatus = (TextView) itemView.findViewById(R.id.cardScheduleStatus);


        }

    }
}
