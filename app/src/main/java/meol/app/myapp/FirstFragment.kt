package meol.app.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import meol.app.myapp.adapter.RecyclerViewAdapter
import meol.app.myapp.data.WeatherDay
import meol.app.myapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private lateinit var newRecylerview: androidx.recyclerview.widget.RecyclerView
    private lateinit var weatherArray: ArrayList<WeatherDay>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setDataForWeatherArray()

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        newRecylerview = binding.recycleViewId
        //newRecylerview.layoutManager = LinearLayoutManager(binding.root.context)
        val adapter = RecyclerViewAdapter(weatherArray)
        newRecylerview.adapter = adapter
        newRecylerview.addItemDecoration(
            DividerItemDecoration(
                binding.root.context,
                LinearLayoutManager.HORIZONTAL
            )
        )

        val horizontalLayoutManager =
            GridLayoutManager(binding.root.context, 3)
        newRecylerview.layoutManager = horizontalLayoutManager
        return binding.root

    }

    private fun setDataForWeatherArray() {
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

        weatherArray = ArrayList()

        for (i in dateData.indices) {
            val weather = WeatherDay(
                dateData[i],
                avgTempData[i],
                pressureData[i],
                humidityData[i],
                descriptionData[i]
            )
            weatherArray.add(weather)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}