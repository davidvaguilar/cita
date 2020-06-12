package cl.dyi.citas.Interface;

import java.util.List;

import cl.dyi.citas.Model.Specialty;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("specialties")
    Call<List<Specialty>> getSpecialties();
}
