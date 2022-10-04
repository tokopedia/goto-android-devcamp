package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.devcamp2022.databinding.FragmentProductMvvmBinding
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.api.MockProductApi
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.view.setProduct

/**
 * Created by kenny.hadisaputra on 26/09/22
 */
class ProductMVVMFragment : Fragment() {

    private var _binding: FragmentProductMvvmBinding? = null
    private val binding get() = _binding!!

    //TODO(1,1) - Initialize ViewModel with custom ViewModelProvider.Factory
    private val viewModel by viewModels<ProductMVVMViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ProductMVVMViewModel(MockProductApi()) as T
                }
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductMvvmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearch.setOnClickListener {
            //TODO(1,5) - Call viewModel.getProduct and pass query from edit text as parameter
            viewModel.getProduct(binding.etSearchProduct.text.toString())
        }

        //TODO(1,6) - Observe from viewModel.product livedata and set product data to product card
        viewModel.product.observe(viewLifecycleOwner) {
            binding.productCard.setProduct(it)
            binding.productCard.root.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}