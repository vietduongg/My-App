package meol.app.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meol.app.myapp.data.Person
import meol.app.myapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private lateinit var newRecylerview : RecyclerView
    private lateinit var newArrayList : ArrayList<Person>
    lateinit var nameArray : Array<String>
    lateinit var emailArray : Array<String>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        nameArray = arrayOf(
            "Soledad", "Hershel", "Bria"
        )

        emailArray = arrayOf(
            "Jay_Kilback@hotmail.com", "Destiny10@hotmail.com", "Theresa48@hotmail.com"
        )

        newRecylerview = binding.recycleViewId
        newRecylerview.layoutManager = LinearLayoutManager(binding.root.context)
        newRecylerview.setHasFixedSize(true)
        newArrayList = arrayListOf<Person>()
        getUserData()
        return binding.root

    }

    private fun getUserData() {

        for(i in nameArray.indices){
            val person = Person(nameArray[i], emailArray[i])
            newArrayList.add(person)
            println(newArrayList.toString())
        }

        newRecylerview.adapter = MyAdapter(newArrayList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}