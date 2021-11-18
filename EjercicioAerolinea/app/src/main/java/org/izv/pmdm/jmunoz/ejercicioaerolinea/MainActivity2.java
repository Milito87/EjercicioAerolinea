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

public class MainActivity2 extends AppCompatActivity {

    //atributos de la clase
    private TextView tvFac;
    private Button btBack, btConfirmar;
    private String[] factura;
    //constante
    private final double descuento = 0.25;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //instanciamos la barra superior
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recibimos los datos de la factura
        Intent i = getIntent();
        Bundle b = getIntent().getExtras();
        factura = b.getStringArray("array");

        //método que instancia los atributos de la clase
        init();

        //visualizamos la factura
        tvFac.setText(cadFactura(factura));

        //botonque cierra la Activity y regresa a la anterior
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }});

        //boton que pasa a la tercera Activity con la informacion de la factura
        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                Bundle b = new Bundle();
                b.putStringArray("array", factura);
                b.putString("total", totalFactura(factura));
                i.putExtras(b);
                startActivity(i);
            }});

    }

    //método que instancia los atributos de la clase
    public void init(){

        tvFac = findViewById(R.id.tvFac);
        btBack =findViewById(R.id.btBack);
        btConfirmar = findViewById(R.id.btConfirmar);
        tvFac.setText(String.valueOf(cadFactura(factura)));
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

    //método que calcula el valor de la factura (Double)
    public Double valorFactura(String cad){
    double valor = 0;
    for(int i = 0;i < cad.length();i++) {
            valor += (double) cad.charAt(i);
    }
        return  valor;
    }

    //método que visualiza la factura
    public String cadFactura(String[] factura){
        return "ORIGEN: "+ valorFactura(factura[0])*descuento+ " €\n"+
                   "DESTINO: "+valorFactura(factura[1])*descuento+" €\n"+
                  "FECHA: "+valorFactura(factura[2])*descuento/4+" €\n"+
                 "Movilidad: "+valorFactura(factura[3])+" €\n"+
                   "Viajar: \n      Premium: "+valorFactura(factura[4])+" €\n"+
                                "      Ventana: "+valorFactura(factura[5])+" €\n"+
                                "      Mascota"+valorFactura(factura[6])+" €\n"+
                "Regimen: \n     Desayuno: "+valorFactura(factura[7])+" €\n"+
                                "     Comida: "+valorFactura(factura[8])+" €\n"+
                                "     Cena: "+valorFactura(factura[9])+" €\n"+
                "Seguro: "+valorFactura(factura[10])+" €\n"+
                "vuelo premium: "+valorFactura(factura[11])+" €\n\n"+
                "Precio total de la factura:        "+totalFactura(factura);
    }

    //método que calcula el valor de la factura (String)
    public String totalFactura(String[] factura){
        double total = 0;
        for(int i = 0;i< factura.length;i++){
            total += valorFactura(factura[i]);
        }
        return String.valueOf(total);
    }

}