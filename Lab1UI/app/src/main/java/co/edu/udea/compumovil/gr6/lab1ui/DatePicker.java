package co.edu.udea.compumovil.gr6.lab1ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import java.util.Calendar;

/**
 * Created by wondercode on 25/02/16.
 */
public  class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private int anio=-1,mes=-1,dia=-1;
    private final Calendar c= Calendar.getInstance();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        if (anio==-1) {
            anio=c.get(Calendar.YEAR);
        }
        if(mes==-1){
            mes=c.get(Calendar.MONTH);
        }
        if(dia==-1){
            dia=c.get(Calendar.DAY_OF_MONTH);
        }
        return new DatePickerDialog(getActivity(),this,anio,mes,dia);
    }
     @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
         setAnio(year);
         setMes(month);
         setDia(day);
     }
    public int getDia() {
        if(dia==-1){
            setDia(c.get(Calendar.DAY_OF_MONTH));
        }
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getAnio() {
        if(anio==-1){
            setAnio(c.get(Calendar.YEAR));
        }
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public int getMes() {
        if(mes==-1){
            setMes(c.get(Calendar.MONTH));
        }
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
}
