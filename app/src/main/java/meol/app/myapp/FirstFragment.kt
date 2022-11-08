package meol.app.myapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import meol.app.myapp.adapter.RecyclerViewAdapter
import meol.app.myapp.api.ApiInterface
import meol.app.myapp.databinding.FragmentFirstBinding
import meol.app.myapp.model.ListItem
import meol.app.myapp.repository.WeatherRepository
import meol.app.myapp.viewmodel.WeatherFragmentViewModel
import meol.app.myapp.viewmodel.WeatherFragmentViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(DelicateCoroutinesApi::class)
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private val binding get() = _binding!!
    private lateinit var weatherViewModel: WeatherFragmentViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
//        getMyData("Hanoi")
        binding.button2.setOnClickListener {
            if(binding.editTextLocation.text.toString().length >= 4){
                weatherViewModel.getWeather(binding.editTextLocation.text.toString())
            }
            else {
                Toast.makeText(activity, "Please enter a city name greater than 3 characters", Toast.LENGTH_SHORT).show()
            }
        }
//
//        newRecylerview = binding.recycleViewId
//        newRecylerview.layoutManager = LinearLayoutManager(binding.root.context)
//        newRecylerview.addItemDecoration(
//            DividerItemDecoration(
//                binding.root.context, LinearLayoutManager.VERTICAL
//            )
//        )
        val viewModelFactory = WeatherFragmentViewModelFactory(WeatherRepository())
        weatherViewModel = ViewModelProvider(this, viewModelFactory)[WeatherFragmentViewModel::class.java]
        val recyclerView = binding.recycleViewId
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        recyclerView.adapter = recyclerViewAdapter
        weatherViewModel.getWeather("Seoul")
        weatherViewModel.liveDataList.observe(viewLifecycleOwner, Observer {
            println("SOS: "+ it)
            if (it != null){
                recyclerView.adapter = RecyclerViewAdapter(it as MutableList<ListItem>)
                recyclerViewAdapter?.setWeatherList(it)
                recyclerViewAdapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(binding.root.context, "Error", Toast.LENGTH_LONG).show()
            }
        })
        weatherViewModel.getWeather("Hanoi")


        return binding.root

    }

//    @SuppressLint("NotifyDataSetChanged")
//    private fun initViewModel() {
//
//       weatherViewModel = ViewModelProvider(this, viewModelFactory)[WeatherFragmentViewModel::class.java]
//        weatherViewModel.liveDataList.observe(viewLifecycleOwner, Observer {
//            if (it != null){
//                recyclerViewAdapter.setWeatherList(it)
//                recyclerViewAdapter.notifyDataSetChanged()
//            } else {
//                Toast.makeText(binding.root.context, "Error", Toast.LENGTH_SHORT).show()
//            }
//        })
//        weatherViewModel.getWeather("Hanoi")
//    }

//    private fun initRecyclerView() {
//
//        recyView.adapter = recyclerViewAdapter
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun updateUI(request: WeatherApiRespon) {
//        newRecylerview.adapter = request.list?.let { RecyclerViewAdapter(it) }
//
//    }
//
//    private fun getMyData(nameOfLocation: String) {
//        GlobalScope.launch(Dispatchers.IO){
//            val retrofitInstance = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build().create(ApiInterface::class.java)
//            val retrofitData = retrofitInstance.getData(nameOfLocation)
//            retrofitData.enqueue(object : Callback<WeatherApiRespon> {
//                override fun onResponse(
//                    call: Call<WeatherApiRespon>,
//                    response: Response<WeatherApiRespon>
//                ) {
//                    println("Respond: " + response.code())
//                    Log.d("duong", "current thread: ${Thread.currentThread().name}")
//                    if (response.code() == 200) {
//                        Toast.makeText(activity, "Update successful", Toast.LENGTH_SHORT).show()
//                        newRecylerview.adapter = response.body()?.list?.let { RecyclerViewAdapter(it) }
//                    } else if(response.code() == 401) {
//                        Toast.makeText(activity, "Please check auth", Toast.LENGTH_SHORT).show()
//                    } else if(response.code() == 408){
//                        Toast.makeText(activity, "Time Out", Toast.LENGTH_SHORT).show()
//                    } else if(response.code() == 404){
//                        Toast.makeText(activity, "Please Check Your City Name", Toast.LENGTH_SHORT).show()
//                    } else if(response.code() == 400){
//                        Toast.makeText(activity, "Server Error or call API fail", Toast.LENGTH_SHORT).show()
//                    }
//                    else {
//                        Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<WeatherApiRespon>, t: Throwable) {
//                    Toast.makeText(activity, "Please check your internet connection", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
//
//    }

    // val retrofitInstanceTest = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://63218d0582f8687273b3fa4f.mockapi.io/").build().create(ApiInterface::class.java)
//    private fun postObject(){
//        val productsx = Product("999", "52", "PRODUCT DUONG")
//        val retrofitData = retrofitInstanceTest.postProduct(productsx)
//        retrofitData.enqueue(object : Callback<Product> {
//            override fun onResponse(
//                call: Call<Product>,
//                response: Response<Product>
//            ) {
//                println("Respon: " + response.body())
//                if (response.code() == 200) {
//                    println(response.body())
//                } else {
//                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//
//            override fun onFailure(call: Call<Product>, t: Throwable) {
//                println(t.message)
//            }
//        })
//    }
//
//    private fun putObject(){
//        val productsx = Product("972343274", "22", "PRODUCT HUHUHU")
//        val retrofitData = retrofitInstanceTest.putProduct(18, productsx)
//        retrofitData.enqueue(object : Callback<Product> {
//            override fun onResponse(
//                call: Call<Product>,
//                response: Response<Product>
//            ) {
//                println("Respon: " + response.body())
//                if (response.code() == 200) {
//                    println(response.body())
//                } else {
//                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//            override fun onFailure(call: Call<Product>, t: Throwable) {
//                println(t.message)
//            }
//        })
//    }
//
//    private fun deleteObject(){
//        val retrofitData = retrofitInstanceTest.deleteProduct(18)
//        retrofitData.enqueue(object : Callback<Product> {
//            override fun onResponse(
//                call: Call<Product>,
//                response: Response<Product>
//            ) {
//                println("Respon: " + response.body())
//                if (response.code() == 200) {
//                    println(response.body())
//                } else {
//                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//            override fun onFailure(call: Call<Product>, t: Throwable) {
//                println(t.message)
//            }
//        })
//    }
//
//    private fun fetchDataFromAPI(): Thread {
//        return Thread {
//            val url =
//                URL("https://ai.openweathermap.org/data/2.5/forecast/daily?appid=60c6fbeb4b93ac653c492ba806fc346d&q=hanoi&cnt=7&units=metric")
//            val connection = url.openConnection() as HttpsURLConnection
//            println("API RESPON : $connection")
//            if (connection.responseCode == 200) {
//                println("API RESPON WIN: $connection")
//                val inputSystem = connection.inputStream
//                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
//                val request = Gson().fromJson(inputStreamReader, WeatherApiRespon::class.java)
//                updateUI(request)
//                inputStreamReader.close()
//                inputSystem.close()
//            } else {
//                println("API RESPON LOST: $connection")
//                requireActivity().runOnUiThread {
//                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
}