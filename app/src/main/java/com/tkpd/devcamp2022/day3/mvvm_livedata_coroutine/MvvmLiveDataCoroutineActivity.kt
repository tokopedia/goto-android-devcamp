package com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.tkpd.devcamp2022.day3.mvvm_livedata_coroutine.menu.MenuFragment
import com.tkpd.devcamp2022.databinding.ActivityMvvmLivedataCoroutineBinding

class MvvmLiveDataCoroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmLivedataCoroutineBinding

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.findFragmentByTag(CHILD_FRAGMENT_TAG) is MenuFragment) {
                isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            } else {
                replaceChildFragment(MenuFragment())
            }
        }
    }

    private val menuFragmentListener = object : MenuFragment.Listener {
        override fun onMenuClicked(dest: Fragment) {
            val oldFragment = supportFragmentManager.findFragmentByTag(CHILD_FRAGMENT_TAG)

            replaceChildFragment(
                if (oldFragment != null && oldFragment::class.java == dest::class.java) {
                    oldFragment
                } else dest
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.addFragmentOnAttachListener { _, fragment ->
            when (fragment) {
                is MenuFragment -> fragment.setListener(menuFragmentListener)
            }
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        super.onCreate(savedInstanceState)

        binding = ActivityMvvmLivedataCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) replaceChildFragment(MenuFragment())
    }

    private fun replaceChildFragment(destFragment: Fragment) {
        supportFragmentManager.commit {
            replace(binding.containerPage.id, destFragment, CHILD_FRAGMENT_TAG)
        }
        onBackPressedCallback.isEnabled = true
    }

    companion object {
        private const val CHILD_FRAGMENT_TAG = "child"
    }

}