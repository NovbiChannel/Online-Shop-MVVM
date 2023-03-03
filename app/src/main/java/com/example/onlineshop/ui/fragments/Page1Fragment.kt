package com.example.onlineshop.ui.fragments

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.onlineshop.R
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.databinding.FragmentPage1Binding
import com.example.onlineshop.other.Resource
import com.example.onlineshop.ui.adapters.BrandsAdapter
import com.example.onlineshop.ui.adapters.CategoryAdapter
import com.example.onlineshop.ui.adapters.FlashSaleAdapter
import com.example.onlineshop.ui.adapters.LatestAdapter
import com.example.onlineshop.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Page1Fragment: Fragment() {

    private var _binding: FragmentPage1Binding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()
    lateinit var latestAdapter: LatestAdapter
    lateinit var flashSaleAdapter: FlashSaleAdapter
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var brandsAdapter: BrandsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPage1Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

//        latestAdapter.setOnItemClickListener {
//            activity?.supportFragmentManager?.beginTransaction()?.apply {
//                val page2Fragment = Page2Fragment()
//                replace(R.id.flFragment, page2Fragment)
//                commit()
//            }
//        }

        viewModel.latestLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        latestAdapter.submitList(it.latest)
                        brandsAdapter.submitList(it.latest)
                    }
                }
                is Resource.Error -> {
                    response.data?.let {
                        Log.d("checkData", "Page1Fragment error: ${it}")
                    }
                }
                is Resource.Loading -> {}
            }
        }

        viewModel.flashSaleLiveData.observe(viewLifecycleOwner) { response ->
            Log.d("Response", "Response for flash sale: ${response.data?.flash_sale}")
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        flashSaleAdapter.submitList(it.flash_sale)
                    }
                }
                is Resource.Error -> {
                    response.data?.let {
                        Log.d("checkData", "Page1Fragment error: ${it}")
                    }
                }
                is Resource.Loading -> {}
            }
        }

        viewModel.categoryLiveData.observe(viewLifecycleOwner) { response ->
            categoryAdapter.submitList(response)
        }
    }

    private fun initAdapter() {
        latestAdapter = LatestAdapter()
        flashSaleAdapter = FlashSaleAdapter()
        categoryAdapter = CategoryAdapter()
        brandsAdapter = BrandsAdapter()

        binding.rvLatest.apply {
            adapter = latestAdapter
        }
        binding.rvFlashSale.apply {
            adapter = flashSaleAdapter
        }
        binding.rvBrands.apply {
            adapter = brandsAdapter
        }
        binding.rvCategories.apply {
            adapter = categoryAdapter
        }
    }
}