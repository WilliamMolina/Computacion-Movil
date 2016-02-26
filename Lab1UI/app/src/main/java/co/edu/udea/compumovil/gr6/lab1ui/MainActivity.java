package co.edu.udea.compumovil.gr6.lab1ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DatePicker d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] paises= getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,paises);
        AutoCompleteTextView textView=(AutoCompleteTextView)findViewById(R.id.pais);
        textView.setAdapter(adapter);
        d=new DatePicker();
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterSpinner=ArrayAdapter.createFromResource(
                this,R.array.hobbies_array,android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
    }
    public void showDatePicker(View view){
        if(d!=null){
            d.show(getFragmentManager(),"datePicker");
        }
        else{
            d=new DatePicker();
        }
    }
    public void showInfo(View view){
        TextView out=(TextView)findViewById(R.id.textView);
        out.setText("");
        String ape,pais,telefono,direccion,email,hobbies,favorite,genero,fecha;
        boolean fav=false;
        String name=((EditText)findViewById(R.id.nombre)).getText().toString();
        if("".equals(name)){
            out.append(getResources().getString(R.string.error));
            return;
        }
        ape=((EditText)findViewById(R.id.apellido)).getText().toString();
        if("".equals(ape)){
            out.append(getResources().getString(R.string.error));
            return;
        }
        telefono=((EditText)findViewById(R.id.tel)).getText().toString();
        if("".equals(telefono)){
            out.append(getResources().getString(R.string.error));
            return;
        }
        CheckBox c=(CheckBox)findViewById(R.id.checkBox);
        if(c.isChecked()){
            favorite=getResources().getString(R.string.favorito)+" : "+getResources().getString(R.string.isFav)+"\n";
        }else{
            favorite=getResources().getString(R.string.favorito)+" : "+getResources().getString(R.string.isNFav)+"\n";
        }
        email=((EditText)findViewById(R.id.mail)).getText().toString();
        if("".equals(email)){
            out.append(getResources().getString(R.string.error));
            return;
        }
        direccion=((EditText)findViewById(R.id.address)).getText().toString();
        if("".equals(direccion)){
            out.append(getResources().getString(R.string.error));
            return;
        }
        pais=((AutoCompleteTextView)findViewById(R.id.pais)).getText().toString();
        if("".equals(pais)){
            out.append(getResources().getString(R.string.error));
            return;
        }
        RadioGroup r=(RadioGroup)findViewById(R.id.sexo);
        RadioButton rb=(RadioButton)findViewById(r.getCheckedRadioButtonId());
        if(rb==null) {
            out.append(getResources().getString(R.string.error));
            return;
        }else{
            genero=rb.getText().toString();
        }
        fecha=d.getDia()+"/"+d.getMes()+"/"+d.getAnio();
        hobbies=((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
        if("".equals(hobbies)){
            out.append(getResources().getString(R.string.error));
            return;
        }
        name=getResources().getString(R.string.name)+" : "+name;
        ape=getResources().getString(R.string.ape)+" : "+ape;
        telefono=getResources().getString(R.string.tel)+" : "+telefono;
        pais=getResources().getString(R.string.pais)+" : "+pais;
        direccion=getResources().getString(R.string.address)+" : "+direccion;
        email=getResources().getString(R.string.email)+" : "+email;
        hobbies=getResources().getString(R.string.hobbies)+" : "+hobbies;
        genero=getResources().getString(R.string.sexo)+" : "+genero;
        fecha=getResources().getString(R.string.date)+" : "+fecha;
        String salida = name+"\n"+ape+"\n"+telefono+"\n"+favorite+ pais+"\n"+direccion+"\n"+email+"\n"+hobbies
                +"\n"+genero+"\n"+fecha;
        out.append(salida);

    }
}
