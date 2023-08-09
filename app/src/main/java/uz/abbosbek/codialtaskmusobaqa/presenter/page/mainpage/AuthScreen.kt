package uz.abbosbek.codialtaskmusobaqa.presenter.page.mainpage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.abbosbek.codialtaskmusobaqa.R
import uz.abbosbek.codialtaskmusobaqa.data.localStorech.LocalStorage
import uz.abbosbek.codialtaskmusobaqa.data.model.request.LoginRequest
import uz.abbosbek.codialtaskmusobaqa.databinding.FragmentAuthScreenBinding
import uz.abbosbek.codialtaskmusobaqa.presenter.page.mainpage.viewmodel.AuthViewModel
import kotlin.math.log

@AndroidEntryPoint
class AuthScreen : Fragment() {
    private val binding by lazy { FragmentAuthScreenBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnSave.setOnClickListener {
            if (binding.userToken.text.isNotEmpty() && binding.username.text.isNotEmpty()) {
                viewModel.login(
                    LoginRequest(
                        binding.userToken.text.trim().toString(),
                        binding.username.text.trim().toString()
                    )
                )
            }
        }

        observer()


        return binding.root
    }

    private fun observer() {
        viewModel.logingLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it.access, Toast.LENGTH_SHORT).show()
            Log.d("TTT", it.access)
            LocalStorage.saveToken(it.access)
            findNavController().navigate(R.id.adminDiscountFragment)
            binding.username.text.clear()
            binding.userToken.text.clear()
        })
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            binding.prgressbar.isVisible = it
        })
        viewModel.errormessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            Log.d("Error", it.toString())
        })
    }
}