package jr.tokioschool.motioeventandkeyevent;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LosViews();


        lv_personas.setAdapter(new ArrayAdapter<Personas>(
                getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>()));

        tb_apagar.setOnCheckedChangeListener((compoundButton, b) -> b_guardar.setEnabled(b));

        b_guardar.setOnClickListener(this::operativaguardar);

        lv_personas.setOnItemClickListener(this::listapersonas);

        tv_borrar.setOnTouchListener(this::borradolista);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprincipal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.apagarentradas) {
            tb_apagar.setChecked(false);
            b_guardar.setEnabled(false);
            return true;
        }
        if (id == R.id.recarga) {
            Toast.makeText(this, R.string.mensajerecarga, Toast.LENGTH_LONG).show();
            Toast.makeText(this, R.string.mensajerecarga2, Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

        private void operativaguardar (View view){
            String nombre = et_nombre.getText().toString();
            String edad = et_edad.getText().toString();
            String ciudad = sp_ciudades.getSelectedItem().toString();
            if (nombre.isEmpty() || edad.isEmpty()) {
                Snackbar.make(findViewById(R.id.ly_global), getString(R.string.snacktodo), Snackbar.LENGTH_LONG).show();
                return;
            }
            Personas p = new Personas(nombre, edad, ciudad);
            ArrayAdapter<Personas> ad = (ArrayAdapter<Personas>) lv_personas.getAdapter();
            ad.add(p);
            et_nombre.setText("");
            et_edad.setText("");
        }

        float comienzoX;
        private boolean borradolista (View view, MotionEvent motionEvent){

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                comienzoX = motionEvent.getX();

            if (motionEvent.getAction() != MotionEvent.ACTION_MOVE)
                return true;

            if (Math.abs(comienzoX - motionEvent.getX()) < 500)
                return true;

            ArrayAdapter<Personas> borralista = (ArrayAdapter<Personas>) lv_personas.getAdapter();
            borralista.clear();
            return false;
        }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menudatosusuario, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.borrarusuario)
        Toast.makeText(this,"Aquí se debería borrar una linea de la lista",Toast.LENGTH_LONG).show();

        return true;
    }

    private void listapersonas (AdapterView < ? > adapterView, View view,int i, long l){
        Personas seleccionlinea = (Personas) lv_personas.getItemAtPosition(i);
        String linea = String.format("Nombre: " + seleccionlinea.getNombre() + "\n     Edad: " + seleccionlinea.getEdad() + "\n  Ciudad: " + seleccionlinea.getCiudad());
        tv_presentadatos.setText(linea);
    }

    private EditText et_nombre;
    private EditText et_edad;
    private Spinner sp_ciudades;
    private ToggleButton tb_apagar;
    private Button b_guardar;
    private TextView tv_borrar,tv_presentadatos;
    private ListView lv_personas;

        private void LosViews () {
            et_nombre = findViewById(R.id.et_nombre);
            et_edad = findViewById(R.id.et_edad);
            sp_ciudades = findViewById(R.id.sp_ciudades);
            tb_apagar = findViewById(R.id.tb_apagar);
            b_guardar = findViewById(R.id.b_guardar);
            tv_borrar = findViewById(R.id.tv_borrar);
            lv_personas = findViewById(R.id.lv_personas);
            tv_presentadatos = findViewById(R.id.tv_presentadatos);
            registerForContextMenu(tv_presentadatos);  // <- esto no lo vi en el video aunque es para aplicarlo a un textview.


            et_nombre.setOnKeyListener((view, i, keyEvent) -> {
                String nombre = et_nombre.getText().toString();
                if ((nombre.isEmpty()) && (keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER))
                    Snackbar.make(findViewById(R.id.ly_global),getString(R.string.snacknombre),Snackbar.LENGTH_LONG).show();
                return false;
            });
            et_edad.setOnKeyListener((view, i, keyEvent) -> {
                String edad = et_edad.getText().toString();
                if ((edad.isEmpty()) && (keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER))
                    Snackbar.make(findViewById(R.id.ly_global),getString(R.string.snackedad),Snackbar.LENGTH_LONG).show();
                return false;
            });

        }
}
