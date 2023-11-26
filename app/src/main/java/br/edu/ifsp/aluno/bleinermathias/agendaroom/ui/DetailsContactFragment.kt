package br.edu.ifsp.aluno.bleinermathias.agendaroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.aluno.bleinermathias.agendaroom.data.Contact
import br.edu.ifsp.aluno.bleinermathias.agendaroom.databinding.FragmentDetailsContactBinding
import br.edu.ifsp.aluno.bleinermathias.agendaroom.viewModel.ContactViewModel

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
        33
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
    }
}
