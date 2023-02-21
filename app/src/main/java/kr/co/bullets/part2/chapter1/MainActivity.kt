package kr.co.bullets.part2.chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.bullets.part2.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                val textView = TextView(this@MainActivity)
                textView.text = "position $position"
                textView.gravity = Gravity.CENTER

                tab.customView = textView
//                tab.text = "position $position"
            }
        }.attach()

//        binding.button1.setOnClickListener {
//            // 엑티비티의 프래그먼트 관리자
//            // apply: 객체 초기화
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragmentContainer, WebViewFragment())
//                commit()
//            }
//        }

//        binding.button2.setOnClickListener {
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragmentContainer, BFragment())
//                commit()
//            }
//        }


//        val webView = findViewById<WebView>(R.id.webView)
//        In-app browser (v), External browser
//        webView.webViewClient = WebViewClient()
//        webView.settings.javaScriptEnabled = true
//        webView.loadUrl("https://google.com")
    }

    override fun onBackPressed() {
//        val currentFragment = supportFragmentManager.fragments[0]
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
//        super.onBackPressed()
    }
}