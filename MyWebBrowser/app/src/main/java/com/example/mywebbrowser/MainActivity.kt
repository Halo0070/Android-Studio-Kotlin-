package com.example.mywebbrowser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 웹뷰 기본 설정
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        webView.loadUrl("http://www.google.com")    // loadUrl() 메서드를 사용하여 "http://'가 포함된 Url을 전달하면 웹뷰에서 페이지가 로딩됨.

        // 키보드의 검색 버튼 동작 정의하기
        urlEditText.setOnEditorActionListener {_, actionId, _ ->    // 글자가 입력될 때마다 호출 인자로는 {반응한 뷰, 액션ID, 이벤트} 세가지, 뷰와 이벤트를 사용하지 않기 때문에 _로 대치 가능
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {      // actionId값은 EditorInfo 클래스에 상수로 정의된 값 중에서 검색 버튼에 해당하는 상수와 비교하여 검색 버튼이 눌렸는지 확인함.
                webView.loadUrl(urlEditText.text.toString())    // 검색 창에 입력한 주소를 웹뷰에 전달하여 로딩함. 마지막으로 true를 반환하여 이벤트를 종료함.
                true
            } else {
                false
            }
        }
    }

    // 뒤로가기 동작 재정의
    override fun onBackPressed() {
        if (webView.canGoBack()) {      // 웹뷰가 이전 페이지로 갈 수 있다면
            webView.goBack()            // 이전 페이지로 이동하고 그렇지 않다면
        } else {
            super.onBackPressed()       // 원래 동작을 수행.(즉 액티비티를 종료)
        }
    }
}
