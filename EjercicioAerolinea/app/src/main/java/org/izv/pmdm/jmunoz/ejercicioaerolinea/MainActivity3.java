package org.izv.pmdm.jmunoz.ejercicioaerolinea;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity3 extends AppCompatActivity {

    //atributos de la clase
    private String[]  factura;
    private String total;
    private Button btFinal;
    private TextView tvResultado;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //instanciamos la barra superior
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recibimos la informacon de la factura
        Intent i = getIntent();
        Bundle b = getIntent().getExtras();
        factura = b.getStringArray("array");
        total = b.getString("total");

        //método que instancia los atributos de la clase
        init();

        //visualizamos la informacion de la factura
        tvResultado.setText(facturacion());

        //boton que finaliza la compra visualizando un mensaje y  pasa a la Activity principal
        btFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Compra realizada con exito", Snackbar.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(i);
            }});

    }

    //método que instancia atributos de la clase
    public void init(){

    tvResultado = findViewById(R.id.tvResultado);
    btFinal = findViewById(R.id.btFinal);

    }

    //infla el menu de la barra superior
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

    //método que visualiza la informacion de la factura
    public String facturacion(){
        String viaje = ""; String regimen = ""; String mov =""; String seguro = ""; String premium = "";

        if(factura[3].length() < 1){
            mov = "cliente sin movilidad especial";
        }
        else{
            mov = "cliente con movilidad especial";
        }

        if(factura[4].length() < 1){
            premium = "        sevicio premium no contratado\n";
        }
        else{
            premium = "        servicio premium contratado\n";
        }

        if(factura[5].length() < 1){
            premium += "        asiento pasillo\n";
        }
        else{
            premium +=  "        asiento ventanilla\n";
        }

        if(factura[6].length() < 1){
            premium += "        viaja sin mascota\n";
        }
        else{
            premium += "        viaja con mascota";
        }

        if(factura[7].length() < 1){
            regimen = "        sin desayuno\n";
        }
        else{
            regimen = "        con desayuno\n";
        }

        if(factura[8].length() < 1){
            regimen += "        sin almuerzo\n";
        }
        else{
            regimen += "        con almuerzo\n";
        }

        if(factura[9].length() < 1){
            regimen += "        sin cena";
        }
        else{
            regimen += "        con cena";
        }

        if(factura[10].length() < 1){
            seguro = "no ha contratado seguro de viaje";
        }
        else{
            seguro = "seguro de viaje contratado";
        }

        if(factura[11].length() < 1){
            viaje = "viaje normal contratado";
        }
        else{
            viaje = "viaje premium contratado";
        }
        return "Origen: "+factura[0]+"\n"+
                    "Destino: "+factura[1]+"\n"+
                    "Fecha: "+factura[2]+"\n\n"+
                    "Movilidad: \n"+mov+"\n\n"+
                    "Viaje: \n"+premium+"\n\n"+
                    "Regimen: \n"+regimen+"\n\n"+
                    "Seguro: \n"+seguro+"\n\n"+
                    "Premium: \n"+viaje+"\n\n"+
                    "total billete: "+total+" €";
    }

}