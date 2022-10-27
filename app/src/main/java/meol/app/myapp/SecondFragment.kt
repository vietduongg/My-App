package meol.app.myapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import meol.app.myapp.adapter.ListViewAdapter
import meol.app.myapp.data.WeatherDay
import meol.app.myapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var weatherArrayList: ArrayList<WeatherDay>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val dateData = arrayOf(
            "Tue, 10 Mar 2020",
            "Tue, 11 Mar 2020",
            "Tue, 12 Mar 2020",
            "Tue, 12 Mar 2020",
            "Tue, 12 Mar 2020",
            "Tue, 12 Mar 2022",
            "Tue, 10 Mar 2020",
            "Tue, 11 Mar 2020",
            "Tue, 12 Mar 2020",
            "Tue, 12 Mar 2020",
            "Tue, 12 Mar 2020",
            "Tue, 12 Mar 2022",
        )
        val avgTempData = arrayOf(
            "20℃", "21℃", "22℃", "22℃", "22℃", "22℃", "22℃", "20℃", "21℃", "22℃", "22℃", "22℃", "22℃", "22℃"
        )
        val pressureData = arrayOf(
            "1027", "1027", "1027", "1027", "1027", "1027", "1027", "1027", "1027", "1027", "1027", "1027", "1027", "1027"
        )
        val humidityData = arrayOf(
            "72%", "73%", "74%", "74%", "74%", "74%", "74%", "72%", "73%", "74%", "74%", "74%", "74%", "74%"
        )
        val descriptionData = arrayOf(
            "light rain",
            "light rain",
            "light rain",
            "light rain",
            "light rain",
            "light rain",
            "light rain","light rain",
            "light rain",
            "light rain",
            "light rain",
            "light rain",
            "light rain",
            "light rain"
        )

        weatherArrayList = ArrayList()

        for (i in dateData.indices) {
            val weather = WeatherDay(
                dateData[i],
                avgTempData[i],
                pressureData[i],
                humidityData[i],
                descriptionData[i]
            )
            weatherArrayList.add(weather)
        }

        binding.weatherListviewId.isClickable = true
        binding.weatherListviewId.adapter =
            ListViewAdapter(binding.root.context as Activity, weatherArrayList)
        binding.weatherListviewId.setOnItemClickListener { parent, view, position, id ->
            val weatherDayData = weatherArrayList[position]

            activity.let{
                val info = Intent(it, Detail_Screen::class.java)
                info.putExtra("date", weatherDayData.date)
                info.putExtra("avgTemp", weatherDayData.avgTemp)
                info.putExtra("pressure", weatherDayData.pressure)
                info.putExtra("humidity", weatherDayData.pressure)
                info.putExtra("desc", weatherDayData.desc)
                startActivity(info)
            }


        }




        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}