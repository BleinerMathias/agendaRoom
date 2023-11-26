package br.edu.ifsp.aluno.bleinermathias.agendaroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.aluno.bleinermathias.agendaroom.R
import br.edu.ifsp.aluno.bleinermathias.agendaroom.adapter.ContactAdapter
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.Contact
import br.edu.ifsp.aluno.bleinermathias.agendaroom.databinding.FragmentContactListBinding
import br.edu.ifsp.aluno.bleinermathias.agendaroom.viewModel.ContactViewModel

class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    lateinit var contactAdapter: ContactAdapter
    lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            fab.setOnClickListener {
                findNavController().navigate(R.id.action_contactListFragment_to_registerFragment)
            }
        }
        configureRecyclerView()

        return root
    }

    private fun configureRecyclerView() {
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        viewModel.allContacts.observe(viewLifecycleOwner) { list ->
            list?.let {
                contactAdapter.updateList(list as ArrayList<Contact>)
            }
        }

        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        contactAdapter = ContactAdapter()
        recyclerView.adapter = contactAdapter

        val listener = object : ContactAdapter.ContactListener {
            override fun onItemClick(pos: Int) {
                val c = contactAdapter.contactListFilterable[pos]
                val bundle = Bundle()
                bundle.putInt("contactId", c.id)
                findNavController().navigate(
                    R.id.action_contactListFragment_to_detailsContactFragment,
                    bundle
                )
            }
        }

        contactAdapter.setClickListener(listener)



    }
}