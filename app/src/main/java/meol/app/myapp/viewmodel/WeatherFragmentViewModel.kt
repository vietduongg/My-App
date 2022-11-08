package meol.app.myapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import meol.app.myapp.api.ApiInterface
import meol.app.myapp.api.RetrofitInstance
import meol.app.myapp.model.ListItem
import meol.app.myapp.model.WeatherApiRespon
import meol.app.myapp.repository.WeatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragmentViewModel(private var repository: WeatherRepository ) : ViewModel() {
    var liveDataList: MutableLiveData<List<ListItem>?> = MutableLiveData()
    private lateinit var respon: Response<WeatherApiRespon>

    fun getWeather(location: String){
        viewModelScope.launch {
            respon = repository.getWeather(location)
            if(respon.isSuccessful){
                liveDataList?.postValue(respon.body()?.list)
            } else {
                liveDataList.postValue(null)
            }
        }
    }


//    fun getDataFromCallApi(location: String){
//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(ApiInterface::class.java)
//        val dataFromApi = retrofitInstance.getData(location)
//        dataFromApi.enqueue(object : Callback<WeatherApiRespon> {
//            override fun onResponse(
//                call: Call<WeatherApiRespon>,
//                response: Response<WeatherApiRespon>
//            ) {
//                liveDataList.postValue(response.body())
//                println("Respond: " + response.code())
//                Log.d("duong", "current thread: ${Thread.currentThread().name}")
//                if (response.code() == 200) {
//                    Toast.makeText(activity, "Update successful", Toast.LENGTH_SHORT).show()
//                    newRecylerview.adapter = response.body()?.list?.let { RecyclerViewAdapter(it) }
//                } else if(response.code() == 401) {
//                    Toast.makeText(activity, "Please check auth", Toast.LENGTH_SHORT).show()
//                } else if(response.code() == 408){
//                    Toast.makeText(activity, "Time Out", Toast.LENGTH_SHORT).show()
//                } else if(response.code() == 404){
//                    Toast.makeText(activity, "Please Check Your City Name", Toast.LENGTH_SHORT).show()
//                } else if(response.code() == 400){
//                    Toast.makeText(activity, "Server Error or call API fail", Toast.LENGTH_SHORT).show()
//                }
//                else {
//                    Toast.makeText(activity, "Call API ERROR", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<WeatherApiRespon>, t: Throwable) {
//               liveDataList.postValue(null)
//            }
//        })
//    }
}

