package org.d3ifcool4045.jurnal07


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_diary_tracker.*
import org.d3ifcool4045.jurnal07.database.DiaryDatabase


/**
 * A simple [Fragment] subclass.
 */
class diaryTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : diaryTrackerFragment = DataBindingUtil.inflate(inflater,R.layout.fragment_diary_tracker,container,false)
        val application = requireNotNull(this.activity).application

        binding.btn_create.setOnClickListener{
            it.findNavController().navigate(R.id.action_diaryTrackerFragment_to_diaryFormFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao
        when(item.itemId){
            R.id.clear -> DiaryTrackerViewModel(dataSource,application).onClear()
        }

        return super.onOptionsItemSelected(item)
    }

}

