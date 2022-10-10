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
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.HomeAdapter
import com.tkpd.devcamp2022.day2.recyclerview.presentation.uimodel.ProductUiModel
import com.tkpd.devcamp2022.day2.recyclerview.presentation.adapter.viewholder.ProductViewHolder

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    private val repository = HomeFactory.getRepository() // our source of data

    // listener: onWishListButtonClicked
    private val productClickListener = object : ProductViewHolder.Listener {
        override fun onWishlistButtonClicked(productUiModel: ProductUiModel, position: Int) {
            addProductToWishlist(productUiModel)
        }
    }

    // listener: onRefresh from SwipeRefreshLayout component
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        showInitialHomeData()
    }

    private var page: Int = 1 // page +1, everytime user scroll until last visible item
    private var isLoading: Boolean = false // indicating if still loading the last task

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            // because we are using LinearLayoutManager, we cast as LinearLayoutManager
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager

            // get position of the very last completely visible item
            val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()

            // if loading is not true
            // and lastVisibleItem position is same with itemCount-1 in adapter
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

    // initializing home adapter with product click listener
    // you can find implementation of productClickListener in ProductViewHolder
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

    private fun setupView(view: View) {
        swipeRefreshLayout = view.findViewById(R.id.home_swipe_refresh) // init SwipeRefreshLayout
        recyclerView = view.findViewById(R.id.home_recyclerview) // init RecyclerView

        recyclerView.adapter = homeAdapter // set home adapter to recyclerview

        // set swipe to refresh listener
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener)

        // set on scroll listener
        recyclerView.addOnScrollListener(onScrollListener)

        // showing first home data
        showInitialHomeData()
    }

    private fun showInitialHomeData() {
        swipeRefreshLayout.isRefreshing = true // show the loading of the SwipeRefreshLayout
        doSomethingWithDelay(800) {
            homeAdapter.setItems(homeData = repository.getInitialHomeData()) // set the items
            page = 1 // reset page to 1 every time user refresh the data
            swipeRefreshLayout.isRefreshing = false // hide the loading of the SwipeRefreshLayout
        }
    }

    private fun showLoadMoreHomeData() {
        // insert items is different with set items (see the difference in HomeAdapter)
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

        // just to be safe, we will setOnRefreshListener of SwipeRefreshLayout to null
        // and remove the OnScrollListener from RecyclerView
        // to avoid memory leaks (although with our implementation above, it won't leak)
        // read more: https://proandroiddev.com/everything-you-need-to-know-about-memory-leaks-in-android-d7a59faaf46a
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