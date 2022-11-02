package meol.app.myapp.api

import meol.app.myapp.model.Product
import meol.app.myapp.model.WeatherApiRespon
import retrofit2.http.*

public interface ApiInterface {

    @GET("daily")
    fun getData(
        @Query("q") nameOfLocation: String,
        @Query("cnt") cnt: Int = 7,
        @Query("appid") appid: String = "60c6fbeb4b93ac653c492ba806fc32146d",
    ):
            retrofit2.Call<WeatherApiRespon>

    @POST("product")
    fun postProduct(@Body product: Product): retrofit2.Call<Product>

    @PUT("product/{id}")
    fun putProduct(@Path("id") id : Int, @Body product: Product): retrofit2.Call<Product>

    @DELETE("product/{id}")
    fun deleteProduct(@Path("id") id: Int): retrofit2.Call<Product>

}