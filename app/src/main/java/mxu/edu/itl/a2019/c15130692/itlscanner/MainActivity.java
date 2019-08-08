package mxu.edu.itl.a2019.c15130692.itlscanner;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.Result;

import java.net.URL;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    private Button btnAcercaDe;
    private Button btnSalir;
    private Button btnok;
    private ZXingScannerView escaner;
    private EditText control;
    String ctrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos las referencias a los imagebutton de la IU
        btnAcercaDe = findViewById( R.id.btnAcercaDe );
        btnSalir = findViewById( R.id.btnSalir);
        btnok = findViewById (R.id.btnOk);
        control = findViewById(R.id.edtxtControl);



        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this
                        , AcercaDe.class);
                startActivity( intent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

    }
    public void btnScanearClick (View view) {
        escaner = new ZXingScannerView(this);
        setContentView(escaner);
        escaner.setResultHandler(this);
        escaner.startCamera();
    }
    public void btnOkClik (View view) {
        String cntrl = ((EditText)findViewById(R.id.edtxtControl)).getText().toString();
        String url = "http://apps.itlalaguna.edu.mx/servicios/escolares/estatus_alumno/estatuscbb.asp?ctr="+ctrl;
        Uri uri = Uri.parse(url);
        Intent intento = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intento);
    }

    protected  void onPause () {
        super.onPause();
        escaner.stopCamera();
    }
//83
    @Override
    public void handleResult(Result result) {
        String cad = result.getText();
        String subtext = cad.substring(83);
        String url = "http://apps.itlalaguna.edu.mx/servicios/escolares/estatus_alumno/estatuscbb.asp?ctr="+subtext;

        Uri uri = Uri.parse(url);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado de escaner");

        builder.setMessage("Resultado "+ uri);
       //builder.setMessage(" Resultado " + result.getText() + "\n" + "Formato " + result.getBarcodeFormat());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Intent intento = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intento);
        escaner.resumeCameraPreview(this);

    }
}
