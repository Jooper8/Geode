package com.facens.geotest;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener{
    Context context;
    //Declaração de um variável que utiliza uma Application c como termo.
    public GPStracker(Application c ){ context = c;}
    //Método utilizado para pegar a localização atual.
    public Location getLocation(){
        //Checa se a permissão para pegar a localização foi permitida.
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            //Mostra uma mensagem caso a permissão não seja permitida.
            Toast.makeText(context, "Permission not granted.", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //Checa se o GPS está habilitado, caso sim, retorna a localização para uso no MainActivity.
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {
            //Mostra mensagem caso o GPS não esteja habilitado.
            Toast.makeText(context, "Please, turn GPS on.", Toast.LENGTH_LONG).show();
        }
        //Retorna nulo caso GPS não esteja habilitado.
        return null;
    }
    //Método chamado quando o API do provedor de GPS é desabilitado.
    @Override
    public void onProviderDisabled(@NonNull String provider) {}
    //Método chamado quando o local calculado pelo GPS muda.
    @Override
    public void onLocationChanged(@NonNull Location location) {}
    //Método chamado quando o status do GPS é mudado, seja desabilitado ou por perda de sinal.
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}

