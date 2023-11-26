package br.edu.ifsp.aluno.bleinermathias.agendaroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.bleinermathias.agendaroom.R
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.Contact
import br.edu.ifsp.aluno.bleinermathias.agendaroom.databinding.FragmentDetailsContactBinding
import br.edu.ifsp.aluno.bleinermathias.agendaroom.viewModel.ContactViewModel
import com.google.android.material.snackbar.Snackbar

class DetailsContactFragment : Fragment() {
    private var _binding: FragmentDetailsContactBinding? = null
    private val binding get() = _binding!!

    lateinit var contact: Contact
    lateinit var nameEditText: EditText
    lateinit var phoneEditText: EditText
    lateinit var emailEditText: EditText

    lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsContactBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText = binding.commonLayout.editTextNome
        phoneEditText = binding.commonLayout.editTextFone
        emailEditText = binding.commonLayout.editTextEmail

        val contactId = requireArguments().getInt("contactId")
        viewModel.getContactById(contactId)
        viewModel.contact.observe(viewLifecycleOwner) { result ->
            result?.let {
                contact = result
                nameEditText.setText(contact.name)
                phoneEditText.setText(contact.phone)
                emailEditText.setText(contact.email)
            }
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.details_menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.actionUpdateContact -> {
                        contact.name= nameEditText.text.toString()
                        contact.phone= phoneEditText.text.toString()
                        contact.email= emailEditText.text.toString()
                        viewModel.update(contact)
                        Snackbar.make(binding.root, "Contato alterado", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    R.id.actionRemoveContact ->{
                        viewModel.delete(contact)
                        Snackbar.make(binding.root, "Contato apagado", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)


    }


}
