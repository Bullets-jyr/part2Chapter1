package kr.co.bullets.part2.chapter1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return WebViewFragment(position)
            }
            1 -> {
                return WebViewFragment(position)
            }
            else -> {
                return WebViewFragment(position)
            }
        }
    }
}