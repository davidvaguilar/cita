package cl.dyi.citas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import cl.dyi.citas.Interface.ApiService;
import cl.dyi.citas.Model.Specialty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView jsonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonText = findViewById(R.id.jsonText);
        getSpecialty();
    }

    private void getSpecialty(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cita.dyi.cl/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Specialty>> call = apiService.getSpecialties();
        call.enqueue(new Callback<List<Specialty>>() {
            @Override
            public void onResponse(Call<List<Specialty>> call, Response<List<Specialty>> response) {
                if(!response.isSuccessful()){
                    jsonText.setText("Codigo: "+response.code());
                    return;
                }
                List<Specialty> specialtyList = response.body();
                for(Specialty specialty: specialtyList){
                    String content ="";
                    content += "id"+specialty.getId()+"\n";
                    content += "name"+specialty.getName()+"\n\n";
                    jsonText.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Specialty>> call, Throwable t) {
                jsonText.setText(t.getMessage());
            }
        });

    }
}
