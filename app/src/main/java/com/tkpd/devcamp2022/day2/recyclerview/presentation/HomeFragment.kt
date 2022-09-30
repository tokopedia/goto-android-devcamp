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
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day2.recyclerview.factory.HomeFactory
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.HomeAdapter
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.ProductViewHolder
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductShimmeringUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    private val onScrollListener = object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()

            if (!isLoading && lastVisibleItem == homeAdapter.itemCount - 1) {
                showLoadMore()
                isLoading = true
                doSomethingWithDelay(1500) { // delay 1.5s
                    hideLoadMore()
                    showLoadMoreItems()
                    isLoading = false
                    page += 1
                }
            }
        }
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        showInitialItems(onRefresh = true)
    }

    private val productClickListener = object : ProductViewHolder.Listener {
        override fun onWishlistButtonClicked(product: ProductUiModel, position: Int) {
            Toast.makeText(
                requireContext(),
                R.string.wishlist_success_message,
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onAddToCartButtonClicked(product: ProductUiModel, position: Int) {
            Toast.makeText(
                requireContext(),
                R.string.add_to_cart_success_message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val homeRepository = HomeFactory.getRepository()
    private val homeAdapter = HomeAdapter(productClickListener)

    private var page: Int = 1
    private var isLoading: Boolean = false

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

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.removeOnScrollListener(onScrollListener)
        swipeRefreshLayout.setOnRefreshListener(null)
    }

    private fun setupView(view: View) {
        swipeRefreshLayout = view.findViewById(R.id.home_swipe_refresh)
        recyclerView = view.findViewById(R.id.home_recyclerview)
        recyclerView.adapter = homeAdapter

        recyclerView.addOnScrollListener(onScrollListener)
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener)

        showInitialItems()
    }

    private fun showInitialItems(onRefresh: Boolean = false) {
        if (onRefresh) swipeRefreshLayout.isRefreshing = true
        showInitialState()
        doSomethingWithDelay(1000) {
            if (onRefresh) swipeRefreshLayout.isRefreshing = false
            setInitialItems()
        }
    }

    private fun showLoadMoreItems() {
        homeAdapter.insertItems(
            homeRepository.getProducts(5, page)
        )
    }

    private fun showInitialState() {
        homeAdapter.setItems(
            List(5) { ProductShimmeringUiModel }
        )
    }

    private fun setInitialItems() {
        homeAdapter.setItems(
            homeRepository.getFirstInitialData()
        )
    }

    private fun showLoadMore() {
        homeAdapter.insertItemAtLast(
            ProductShimmeringUiModel
        )
    }

    private fun hideLoadMore() {
        homeAdapter.removeItemAtLast()
    }

    private fun doSomethingWithDelay(delayInMillis: Long, something: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            something.invoke()
        }, delayInMillis)
    }
}