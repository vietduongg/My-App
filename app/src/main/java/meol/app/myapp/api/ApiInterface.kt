package meol.app.myapp.api

import meol.app.myapp.APP_ID
import meol.app.myapp.model.Product
import meol.app.myapp.model.WeatherApiRespon
import retrofit2.Response
import retrofit2.http.*

public interface ApiInterface {

    @GET("daily")
    suspend fun getData(
        @Query("q") nameOfLocation: String,
        @Query("units") units: String = "metric",
        @Query("cnt") cnt: Int = 7,
    ):Response<WeatherApiRespon>

    @POST("product")
    fun postProduct(@Body product: Product): retrofit2.Call<Product>

    @PUT("product/{id}")
    fun putProduct(@Path("id") id : Int, @Body product: Product): retrofit2.Call<Product>

    @DELETE("product/{id}")
    fun deleteProduct(@Path("id") id: Int): retrofit2.Call<Product>

}