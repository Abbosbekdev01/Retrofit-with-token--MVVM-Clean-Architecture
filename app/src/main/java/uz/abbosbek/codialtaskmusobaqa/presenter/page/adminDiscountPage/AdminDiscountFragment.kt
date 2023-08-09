package uz.abbosbek.codialtaskmusobaqa.presenter.page.adminDiscountPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import uz.abbosbek.codialtaskmusobaqa.R
import uz.abbosbek.codialtaskmusobaqa.data.localStorech.LocalStorage
import uz.abbosbek.codialtaskmusobaqa.databinding.FragmentAdminDiscountBinding
import uz.abbosbek.codialtaskmusobaqa.presenter.page.adminDiscountPage.adminAdapter.AdminDiscountAdapter
import uz.abbosbek.codialtaskmusobaqa.presenter.page.adminDiscountPage.viewModel.AdminViewModel


class AdminDiscountFragment : Fragment() {
    private val binding by lazy { FragmentAdminDiscountBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<AdminViewModel>()
    private val token: String = LocalStorage.getToken()
    private lateinit var adapterAdmin: AdminDiscountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adapterAdmin = AdminDiscountAdapter()
        viewModel.getDiscount(token)




        Toast.makeText(requireContext(), token, Toast.LENGTH_SHORT).show()

        return binding.root
    }

    private fun observer() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = true
        })
        viewModel.errormessage.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        })
        viewModel.discountsLiveData.observe(viewLifecycleOwner, Observer {
            it.warehouses.toString()
            adapterAdmin.list.addAll(it.warehouses)

            binding.rvAdminDiscount.adapter = adapterAdmin

            Toast.makeText(requireContext(), it.warehouses.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}