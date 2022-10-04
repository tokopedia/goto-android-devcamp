package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.devcamp2022.R
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.coroutine.ProductCoroutine2Fragment
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.coroutine.ProductCoroutineFragment
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.mvvm.ProductMVVMFragment

/**
 * Created by kenny.hadisaputra on 26/09/22
 */
class MenuFragment : Fragment(R.layout.fragment_mvvm_menu) {

    private lateinit var rvMenu: RecyclerView

    private val menuAdapter = MenuAdapter()

    private var mListener: Listener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMenu = view.findViewById(R.id.rv_menu)
        rvMenu.adapter = menuAdapter

        menuAdapter.submitList(
            listOf(
                Menu("ViewModel + LiveData") {
                    onOpenFragment(ProductMVVMFragment())
                },
                Menu("Coroutine") {
                    onOpenFragment(ProductCoroutineFragment())
                },
                Menu("Coroutine 2") {
                    onOpenFragment(ProductCoroutine2Fragment())
                }
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mListener = null
    }

    fun setListener(listener: Listener?) {
        mListener = listener
    }

    private fun onOpenFragment(fragment: Fragment) {
        mListener?.onMenuClicked(fragment)
    }

    interface Listener {
        fun onMenuClicked(dest: Fragment)
    }
}

data class Menu(
    val title: String,
    val onClicked: () -> Unit,
)