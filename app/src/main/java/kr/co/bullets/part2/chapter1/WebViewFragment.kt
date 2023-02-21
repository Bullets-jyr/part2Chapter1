package kr.co.bullets.part2.chapter1

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import kr.co.bullets.part2.chapter1.databinding.FragmentWebViewBinding

class WebViewFragment(private val position: Int, private val webViewUrl: String) : Fragment() {
    var listener: OnTabLayoutNameChanged? = null

    private lateinit var binding: FragmentWebViewBinding

    companion object {
        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.webView.webViewClient = WebViewClient()
        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar) { url ->

//            if (commit) {
                  // 동기 처리
//                editor.commit()
//            } else {
                  // 비동기 처리
//                editor.apply()
//            }
            activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
            }
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(webViewUrl)

        binding.backToLastButton.setOnClickListener {
            val sharedPreference = activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position", "")
            if (url.isNullOrEmpty()) {
                Toast.makeText(context, "마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }
        }

        binding.changeTabNameButton.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)

            dialog.setView(editText)
            dialog.setPositiveButton("저장") { _, _ ->
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit {
                    putString("tab${position}_name", editText.text.toString())
                    listener?.nameChanged(position, editText.text.toString())
                }
            }
            dialog.setNegativeButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            dialog.show()
        }
    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}

interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}