package co.and.carganimagenurlejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;

public class MainActivity extends AppCompatActivity {
    Button btnCargar;
    ImageView imagen;
    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (ImageView)findViewById(R.id.imgImagen);

        request = Volley.newRequestQueue(getApplicationContext());



    }

    public void onClick(View view) {
        if(view.getId()==R.id.btnCargarImagen){
            cargarWebServiceImagen();
        }
    }

    private void cargarWebServiceImagen() {
        String url= "https://images3.memedroid.com/images/UPLOADED473/5d31fddcd2a24.jpeg";
        //Linea para evitar errores con espacios, para que cuando encuentre un espacio lo rellene con %20 y asi evitar error
        url = url.replace(" ","%20");
        //new REsponse.Listener
        final ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                //Asignamos nuestra imagen via url a imagen
                imagen.setImageBitmap(response);

            }
            //inicialmente ponemos las propiedades de ImageView, le ponemos el ttipo de escala, le dejamos por defecto CENTER
            //Creamos el REsponse.ErrorListener
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Mensaje en caso de error
                Toast.makeText(MainActivity.this, "Error al cargar imagen", Toast.LENGTH_SHORT).show();
            }
        });
        //Agregamos a nuestro request ya definido y le enviamos lo que tenemos en imageRequest
        request.add(imageRequest);
    }
}
