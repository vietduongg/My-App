package meol.app.myapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import meol.app.myapp.R
import meol.app.myapp.model.WeatherDay

class ListViewAdapter(private val context: Activity, private val arrayList: ArrayList<WeatherDay>) :

    ArrayAdapter<WeatherDay>(context, R.layout.activity_item_of_weather, arrayList) {

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_item_of_weather, null)

        val date: TextView = view.findViewById(R.id.date_textView_id)
        val avgTemp: TextView = view.findViewById(R.id.avgTemp_textView_id)
        val pressure: TextView = view.findViewById(R.id.pressure_textView_id)
        val humidity: TextView = view.findViewById(R.id.humidity_textView_id)
        val description: TextView = view.findViewById(R.id.description_textView_id)

        date.text = arrayList[position].date
        avgTemp.text = arrayList[position].avgTemp
        pressure.text = arrayList[position].pressure
        humidity.text = arrayList[position].humnidity
        description.text = arrayList[position].desc

        return view
    }
}