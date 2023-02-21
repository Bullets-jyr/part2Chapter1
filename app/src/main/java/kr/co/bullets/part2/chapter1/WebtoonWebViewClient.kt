package kr.co.bullets.part2.chapter1

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.view.isVisible

class WebtoonWebViewClient(private val progressBar: ProgressBar, private val saveData: (String) -> Unit) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

        // https://comic.naver.com/webtoon/detail?titleId=795297&no=38&weekday=mon
//        if (request != null && request.url.toString().contains("comic.naver.com")) {
//            return false
//        } else {
//            return true
//        }

        if (request != null && request.url.toString().contains("comic.naver.com/webtoon/detail")) {
//            saveData.invoke(request.url.toString())
            saveData(request.url.toString())
        }

        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressBar.visibility = View.GONE
//        progressBar.isVisible = false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)

        // 에러페이지를 띄워준다.
    }
}