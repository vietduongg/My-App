package meol.app.myapp.repository

import meol.app.myapp.api.RetrofitInstance
import meol.app.myapp.model.WeatherApiRespon
import retrofit2.Call
import retrofit2.Response

class WeatherRepository {
    suspend fun getWeather(nameOfLocation: String): Response<WeatherApiRespon>{
        return RetrofitInstance().api.getData(nameOfLocation)
    }
}