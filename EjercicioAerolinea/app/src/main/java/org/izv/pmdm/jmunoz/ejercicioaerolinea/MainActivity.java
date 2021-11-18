package org.izv.pmdm.jmunoz.ejercicioaerolinea;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

public class MainActivity extends AppCompatActivity{

    //atributos de la clase
    private Context context;
    private ImageView iLogo;
    private ImageButton btPremium;
    private Button btComprar;
    private AlertDialog.Builder alert;
    private String[] factura;
    private Spinner spinOrigen, spinDestino;
    private EditText date, addresse, phone, email,name;
    private CheckBox checkPremium, checkVentana, checkMascota, checkDesayuno, checkComida, checkCena;
    private Switch sMovilidad;
    private RadioButton rSeguro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instanciamos la barra superior
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //asignamos contexto a la actividad
        context = this;

        //método que instancia atributos de la clase
        init();

        //boton que pasa  a la segunda Activity con los datos de la factura premium
        btPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menu alerta
                alert.setTitle("PREMIUM TRAVEL").setMessage("¿Estas seguro de que quieres contratar vuelo premium?").setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(validarFactura(v,factura)) {
                            Intent i = new Intent(MainActivity.this, MainActivity2.class);
                            Bundle b = new Bundle();
                            b.putStringArray("array", fillFactura(factura, true));
                            i.putExtras(b);
                            startActivity(i);
                        }
                    }}).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no hace nada
                    }}).show();
            }});

        //boton que pasa a la segunda Activity con los datos de la factura basica
        btComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarFactura(v,factura)) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    Bundle b = new Bundle();
                    b.putStringArray("array", fillFactura(factura, false));
                    i.putExtras(b);
                    startActivity(i);
                }
            }});

        //al tocar el logo emerge un mensaje de la compañia
        iLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DynamicToast.Config.getInstance().setWarningIcon(getDrawable(R.drawable.info)).setToastBackground(getDrawable(R.color.lightBlue)).apply();
                DynamicToast.make(context, "Aerolineas Qatar", Toast.LENGTH_SHORT).show();

            }});

    }

    //infla el menu de opciones de la barra superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_airline, menu);
        return true;
    }

    //cada uno de los item que forma el menu inflado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.viajes:
                Uri uri = Uri.parse("https://www.viajes.com/");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
                break;
            case R.id.embarque:
                alert.setTitle("EMBARQUE").setPositiveButton("CERRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no hace nada
                    }}).setIcon(getDrawable(R.drawable.embarque)).show();
                break;
            case R.id.InfoVuelos:
                alert.setTitle("VUELOS").setPositiveButton("CERRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no hace nada
                    }}).setIcon(getDrawable(R.drawable.vuelos)).show();
                break;
            case R.id.InfoActual:
                alert.setTitle("Actualidad").setMessage("No hay nada nuevo que mostrar...").setPositiveButton("CERRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no hace nada
                    }}).show();
                break;
            case R.id.vales:
                Toast.makeText(getApplicationContext(), "No dispones de vales descuento", Toast.LENGTH_SHORT).show();
                break;
            case R.id.revista:
                Uri uri2 = Uri.parse("https://www.yumpu.com/es/document/view/64497845/revista-qatar-airways");
                Intent i2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(i2);
                break;
            case R.id.terminos:
                alert.setTitle("Terminos y condiciones").setMessage("Usted asegura que es mayor de edad y que está legalmente capacitado para suscribir el presente acuerdo, así como para utilizar este sitio web en virtud de todas las condiciones contenidas en el presente. Usted acepta responsabilizarse desde el punto de vista financiero, con respecto de todo el uso que usted haga de este sitio web, así como del uso de su cuenta por parte de terceros.").setPositiveButton("CERRAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no hace nada
                    }}).show();
                break;
            case R.id.centro:
                Uri uri3 = Uri.parse("https://www.qatarairways.com/es-es/help.html");
                Intent i3 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(i3);
                break;
            case R.id.Contactar:
                Uri uri4 = Uri.parse("https://www.qatarairways.com/es-es/homepage.html");
                Intent i4 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(i4);
                break;
            case R.id.privacidad:
                Toast.makeText(getApplicationContext(), "Una política de privacidad (privacy policy) es una presentación por escrito de todas las medidas que aplica una empresa u organización para garantizar la seguridad y el uso lícito de los datos de los usuarios o clientes que recoge en el contexto de la relación comercial.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.confPriv:
                Toast.makeText(getApplicationContext(), "Usted no tiene configurada su privacidad...", Toast.LENGTH_SHORT).show();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    //método que instancia los atributos de la clase
    public void init(){

        btPremium = findViewById(R.id.ibtPremium);
        btComprar = findViewById(R.id.btBuy);
        iLogo = findViewById(R.id.iLogo);
        alert = new AlertDialog.Builder(context);

        spinOrigen = findViewById(R.id.spinOrigen);
        spinDestino =findViewById(R.id.spinDestino);
        date = findViewById(R.id.etDate);
        addresse = findViewById(R.id.etAddress);
        phone = findViewById(R.id.etPhone);
        email = findViewById(R.id.etEmail);
        name = findViewById(R.id.etName);

        sMovilidad = findViewById(R.id.switchMov);

        checkPremium = findViewById(R.id.checkPremium);
        checkVentana = findViewById(R.id.checkWindow);
        checkMascota = findViewById(R.id.checkPet);
        checkDesayuno = findViewById(R.id.checkBreak);
        checkComida = findViewById(R.id.checkEat);
        checkCena = findViewById(R.id.checkDin);

        rSeguro = findViewById(R.id.radioYES);

    }

    //método que rellena la factura (manualmente)
    public String[] fillFactura(String[] factura, Boolean premium){
        factura = new String[12];

        factura[0] = spinOrigen.getSelectedItem().toString();
        factura[1] = spinDestino.getSelectedItem().toString();
        factura[2] = date.getText().toString();
        if(sMovilidad.isChecked()) {
            factura[3] = "50";
        }
        else{
            factura[3] = "";
        }
        if(checkPremium.isChecked()){
            factura[4] = "100";
        }
        else{
            factura[4] = "";
        }
        if(checkVentana.isChecked()){
            factura[5] = "20";
        }
        else{
            factura[5] = "";
        }
        if(checkMascota.isChecked()){
            factura[6] = "30";
        }
        else{
            factura[6] = "";
        }
        if(checkDesayuno.isChecked()){
            factura[7] = "10";
        }
        else{
            factura[7] = "";
        }
        if(checkComida.isChecked()){
            factura[8] = "20";
        }
        else{
            factura[8] = "";
        }
        if(checkCena.isChecked()){
            factura[9] = "30";
        }
        else{
            factura[9] = "";
        }
        if(rSeguro.isChecked()){
            factura[10] = "60";
        }
        else{
            factura[10] = "";
        }
        if(premium){
            factura[11] = "200";
        }
        else{
            factura[11] = "";
        }
        return factura;
    }

    public boolean validacionDatos(EditText et){
        if(et.getText().toString().isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean validarFactura(View view, String[] factura){
        if(!validacionDatos(date) || !validacionDatos(addresse) ||
                !validacionDatos(phone) || !validacionDatos(email) ||
                !validacionDatos(name)){
            Snackbar.make(view,"Any field is empty ...",Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

}