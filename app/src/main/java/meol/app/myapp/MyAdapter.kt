package meol.app.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meol.app.myapp.data.Person

class MyAdapter(private val peopleList: ArrayList<Person>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
       return peopleList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_of_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = peopleList[position]
        holder.name.text = currentItem.name
        holder.email.text = currentItem.email
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.name_textview_id)
        val email : TextView = itemView.findViewById(R.id.email_textview_id)

    }
}