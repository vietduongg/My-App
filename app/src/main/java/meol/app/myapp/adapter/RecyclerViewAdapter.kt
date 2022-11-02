package meol.app.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meol.app.myapp.R
import meol.app.myapp.data.Person
import meol.app.myapp.data.WeatherDay

class RecyclerViewAdapter(private val peopleList: ArrayList<WeatherDay>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
       return peopleList.size
    }

    //create new view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_of_weather, parent, false)
        return MyViewHolder(itemView)
    }

    //binds the list items to a view    
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = peopleList[position]
        holder.date.text = currentItem.date
        holder.avgTemp.text = currentItem.avgTemp
        holder.desc.text = currentItem.desc
        holder.humidity.text = currentItem.humnidity
        holder.pressure.text = currentItem.pressure
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.date_textView_id)
        val avgTemp: TextView = itemView.findViewById(R.id.avgTemp_textView_id)
        val pressure: TextView = itemView.findViewById(R.id.pressure_textView_id)
        val humidity: TextView = itemView.findViewById(R.id.humidity_textView_id)
        val desc: TextView = itemView.findViewById(R.id.description_textView_id)
    }
}