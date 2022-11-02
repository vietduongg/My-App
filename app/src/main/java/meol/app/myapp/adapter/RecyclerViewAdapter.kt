package meol.app.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meol.app.myapp.R
import meol.app.myapp.model.ListItem
import meol.app.myapp.model.WeatherItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil

class RecyclerViewAdapter(private val weatherApiList: List<ListItem>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
       return weatherApiList.size
    }

    //create new view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_of_weather, parent, false)
        return MyViewHolder(itemView)
    }

    //binds the list items to a view    
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = weatherApiList[position]
        holder.date.text = covertTime(currentItem.dt)
        val avgTempAday = currentItem.temp.min + currentItem.temp.max
        holder.avgTemp.text = ceil(avgTempAday/2).toString()
        holder.desc.text = currentItem.weather?.get(0)?.description
        holder.humidity.text = currentItem.humidity.toString()
        holder.pressure.text = currentItem.pressure.toString()
    }

    private fun covertTime(number: Int): String? {
        val simpleDateFormat = SimpleDateFormat("EE, dd MMMM yyyy", Locale.ENGLISH)
        return simpleDateFormat.format(number*1000L)
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.date_textView_id)
        val avgTemp: TextView = itemView.findViewById(R.id.avgTemp_textView_id)
        val pressure: TextView = itemView.findViewById(R.id.pressure_textView_id)
        val humidity: TextView = itemView.findViewById(R.id.humidity_textView_id)
        val desc: TextView = itemView.findViewById(R.id.description_textView_id)
    }
}