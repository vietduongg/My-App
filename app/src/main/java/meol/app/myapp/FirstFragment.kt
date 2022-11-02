package meol.app.myapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import meol.app.myapp.adapter.RecyclerViewAdapter
import meol.app.myapp.api.ApiInterface
import meol.app.myapp.databinding.FragmentFirstBinding
import meol.app.myapp.model.Product
import meol.app.myapp.model.WeatherApiRespon
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.math.log


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val BASE_URL = "https://api.openweathermap.org/data/2.5/forecast/"

    private var _binding: FragmentFirstBinding? = null

    private lateinit var newRecylerview: androidx.recyclerview.widget.RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val retrofitInstanceTest = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://63218d0582f8687273b3fa4f.mockapi.io/").build().create(ApiInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        //fetchDataFromAPI().start()
        getMyData("Hanoi")

        binding.button2.setOnClickListener {
            getMyData(binding.editTextLocation.text.toString())
        }

        newRecylerview = binding.recycleViewId
        newRecylerview.layoutManager = LinearLayoutManager(binding.root.context)
        newRecylerview.addItemDecoration(
            DividerItemDecoration(
                binding.root.context, LinearLayoutManager.VERTICAL
            )
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchDataFromAPI(): Thread {
        return Thread {
            val url =
                URL("https://ai.openweathermap.org/data/2.5/forecast/daily?appid=60c6fbeb4b93ac653c492ba806fc346d&q=hanoi&cnt=7&units=metric")
            val connection = url.openConnection() as HttpsURLConnection
            println("API RESPON : $connection")
            if (connection.responseCode == 200) {
                println("API RESPON WIN: $connection")
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, WeatherApiRespon::class.java)
                updateUI(request)
                inputStreamReader.close()
                inputSystem.close()
            } else {
                println("API RESPON LOST: $connection")
                requireActivity().runOnUiThread {
                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateUI(request: WeatherApiRespon) {
        activity?.runOnUiThread() {
            kotlin.run {
                newRecylerview.adapter = request.list?.let { RecyclerViewAdapter(it) }
            }
        }
    }

    private fun getMyData(nameOfLocation: String) {

        val retrofitInstance = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(ApiInterface::class.java)
        val retrofitData = retrofitInstance.getData(nameOfLocation)
        retrofitData.enqueue(object : Callback<WeatherApiRespon> {
            override fun onResponse(
                call: Call<WeatherApiRespon>,
                response: Response<WeatherApiRespon>
            ) {
             
                println("Respon: " + response.body())
                if (response.code() == 200) {
                    response.body()?.let { updateUI(it) }
                } else {
                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherApiRespon>, t: Throwable) {
                Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun postObject(){
        val productsx = Product("999", "52", "PRODUCT DUONG")
        val retrofitData = retrofitInstanceTest.postProduct(productsx)
        retrofitData.enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                println("Respon: " + response.body())
                if (response.code() == 200) {
                    println(response.body())
                } else {
                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun putObject(){
        val productsx = Product("972343274", "22", "PRODUCT HUHUHU")
        val retrofitData = retrofitInstanceTest.putProduct(18, productsx)
        retrofitData.enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                println("Respon: " + response.body())
                if (response.code() == 200) {
                    println(response.body())
                } else {
                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
                }

            }
            override fun onFailure(call: Call<Product>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun deleteObject(){
        val retrofitData = retrofitInstanceTest.deleteProduct(18)
        retrofitData.enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                println("Respon: " + response.body())
                if (response.code() == 200) {
                    println(response.body())
                } else {
                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
                }

            }
            override fun onFailure(call: Call<Product>, t: Throwable) {
                println(t.message)
            }
        })
    }
}