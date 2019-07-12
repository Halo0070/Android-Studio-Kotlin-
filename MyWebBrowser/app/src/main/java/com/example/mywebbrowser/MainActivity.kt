package com.example.mywebbrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu) // 메뉴 리소스를 액티빅티의 메뉴로 표시하려면 menuInflater 객채의 inflate() 메서드를 사용하여 메뉴 리소스를 지정함.
        return true     // true를 반환하면 액티비티에 메뉴가 있다고 인식함.
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {        // 메뉴 아이템으로 분기를 수행함.
            R.id.action_google, R.id.action_home -> {
                webView.loadUrl("http://www.google.come")
                return true
            }
            R.id.action_naver -> {
                webView.loadUrl("http://www.naver.com")
                return true
            }
            R.id.action_daum -> {
                webView.loadUrl("http://www.daum.net")
                return true
            }
            R.id.action_call -> {       // 연락처를 클릭하면 전화 앱을 엽니다. 이러한 방식을 암시적 인텐트라고 함.
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-123-4567")
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }
            R.id.action_send_text -> {
                // 문자보내기
                return true
            }
            R.id.action_email -> {
                // 이메일 보내기
                return true
            }
        }
        return super.onOptionsItemSelected(item)    // when문에서는 각 메뉴 처리를 끝내고 true를 반환했고 내가 처리하고자 하는 경우를 제외한 그 이외의 경우에는 super 메서드를 호출하는 것이 안드로이드 시스템에서의 보편적인 규칙임.
    }
}
