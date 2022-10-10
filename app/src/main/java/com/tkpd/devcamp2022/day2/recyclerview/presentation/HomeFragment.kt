package com.tkpd.devcamp2022.day2.recyclerview.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.factory.HomeFactory
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.viewholder.ProductViewHolder

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var page: Int = 1
    private var isLoading: Boolean = false

    private val repository = HomeFactory.getRepository() // our source of data

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        showInitialHomeData()
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()

            if (!isLoading && lastVisibleItem == homeAdapter.itemCount - 1) {
                isLoading = true
                doSomethingWithDelay(500) {
                    showLoadMoreHomeData()
                    isLoading = false
                    page += 1
                }
            }
        }
    }

    private val productClickListener = object : ProductViewHolder.Listener {

        override fun onWishlistButtonClicked(productUiModel: ProductUiModel, position: Int) {
            addProductToWishlist(productUiModel)
        }
    }

    private val homeAdapter = HomeAdapter(productClickListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    private fun setupView(view: View) {
        swipeRefreshLayout = view.findViewById(R.id.home_swipe_refresh)
        recyclerView = view.findViewById(R.id.home_recyclerview)

        recyclerView.adapter = homeAdapter

        swipeRefreshLayout.setOnRefreshListener(onRefreshListener)
        recyclerView.addOnScrollListener(onScrollListener)

        showInitialHomeData()
    }

    private fun showInitialHomeData() {
        swipeRefreshLayout.isRefreshing = true
        doSomethingWithDelay(800) {
            homeAdapter.setItems(homeData = repository.getInitialHomeData())
            page = 1
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun showLoadMoreHomeData() {
        homeAdapter.insertItems(
            repository.getProducts(5)
        )
    }

    private fun addProductToWishlist(product: ProductUiModel) {
        Toast.makeText(
            requireContext(),
            "${getString(R.string.wishlist_success_message)} \n${product.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        swipeRefreshLayout.setOnRefreshListener(null)
        recyclerView.removeOnScrollListener(onScrollListener)
    }

    // like its name, it just add delay with everything we wanna do
    private fun doSomethingWithDelay(delayInMillis: Long, something: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            something.invoke()
        }, delayInMillis)
    }
}