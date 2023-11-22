package br.edu.ifsp.aluno.bleinermathias.agendaroom.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.edu.ifsp.aluno.bleinermathias.agendaroom.R
import br.edu.ifsp.aluno.bleinermathias.agendaroom.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

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

        return root
    }
}