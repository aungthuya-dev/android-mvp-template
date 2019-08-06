package com.aungthuya.codetest.ui.base

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.aungthuya.codetest.core.CodeTestApplication
import com.aungthuya.codetest.model.response.ErrorThrowable
import com.aungthuya.codetest.ui.view.BlockProgressBar
import com.aungthuya.codetest.util.showToast
import java.util.*

@Suppress("MemberVisibilityCanPrivate")
abstract class AbsActivity<P : BasePresenter<V>, V : BaseView> : AppCompatActivity(), BaseView,
    PresenterFactory<P> {

    protected lateinit var presenter: P
    private lateinit var view: V
    private var identifier: String? = null
    protected val app by lazy { CodeTestApplication.getApp(this) }

    private val progress by lazy {
        BlockProgressBar.with(this)
            .style(android.R.attr.progressBarStyleLarge)
            .centerIn(findViewById(android.R.id.content))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        identifier = getIdentifier(savedInstanceState)
        presenter = PresenterManager.instance.getPresenter(identifier!!, this)
        view = provideView()
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(view)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbindView()
    }

    private fun getIdentifier(savedInstanceState: Bundle?): String? =
        if (savedInstanceState != null) {
            savedInstanceState.getString(BUNDLE_PRESENTER_IDENTIFIER)
        } else {
            createIdentifier()
        }

    abstract fun getLayoutResId(): Int

    abstract fun provideView(): V

    override fun showProgress() {
        progress.visibility = View.VISIBLE
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

//    protected fun tryPermission(permissions: Array<String>, code: Int, isGranted: () -> Unit) {
//        val notGranted = mutableListOf<String>()
//
//        permissions.forEach {
//            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
//                notGranted.add(it)
//            }
//        }
//
//        if (notGranted.isNotEmpty()) {
//            ActivityCompat.requestPermissions(this, notGranted.toTypedArray(), code)
//        } else {
//            isGranted()
//        }
//    }

    override fun hideProgress() {
        progress.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

//    fun isProgressShow() = progress.visibility == View.VISIBLE

    override fun showError(error: String) {
        showToast(error)
    }

    override fun showError(throwable: ErrorThrowable) {
        showToast(throwable.localizedMessage)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            PresenterManager.instance.remove(identifier)
        }
    }

    override fun provideContext() = this

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(BUNDLE_PRESENTER_IDENTIFIER, identifier)
        super.onSaveInstanceState(outState)
    }

    private fun createIdentifier() =
        if (TextUtils.isEmpty(identifier)) {
            UUID.randomUUID().toString()
        } else {
            identifier
        }

    companion object {
        const val BUNDLE_PRESENTER_IDENTIFIER = "bundle.PresenterIdentifier"
    }
}