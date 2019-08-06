package com.aungthuya.codetest.ui.wonders

import android.os.Bundle
import android.os.Handler
import android.view.View
import com.aungthuya.codetest.R
import com.aungthuya.codetest.model.Wonder
import com.aungthuya.codetest.ui.base.AbsActivity
import com.aungthuya.codetest.ui.wonder.WonderActivity
import kotlinx.android.synthetic.main.activity_wonders.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * This activity show the all wonder places.
 *
 * @author Aung Thuya
 * @since 28 July 2019
 */
class WondersActivity: AbsActivity<WondersContract.WondersPresenter, WondersContract.WondersView>(),
        WondersContract.WondersView {

    private val adapter by lazy { WondersAdapter(::onItemSelected) }

    private var wonders = listOf<Wonder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup toolbar
        toolbar.setNavigationOnClickListener { onBackPressed() }
        tvToolbar.setText(R.string.wonders)

        rvWonders.adapter = adapter

        // Load offline data
        wonders = app.getStorage().getWonders()
        refreshView()

        Handler().postDelayed({
            // show loading progress while loading wonder places
            // if there is offline data, don't need to show progress
            if(wonders.isEmpty()) {
                showProgress()
            }

            // load wonders
            presenter.loadWonders()
        }, 100)
    }

    /**
     * Refresh the UI to show
     */
    private fun refreshView() {
        if(this.wonders.isNotEmpty()) {
            rvWonders.visibility = View.VISIBLE
            tvEmptyWonders.visibility = View.GONE

            adapter.setItems(wonders)
        } else {
            rvWonders.visibility = View.GONE
            tvEmptyWonders.visibility = View.VISIBLE
        }
    }

    private fun onItemSelected(wonder: Wonder) {
        startActivity(WonderActivity.getLauncherIntent(this, wonder))
    }

    override fun loadedWonders(wonders: List<Wonder>) {
        hideProgress()
        this.wonders = wonders

        refreshView()
    }

    override fun loadingFailed() {
        hideProgress()
    }

    override fun getLayoutResId() = R.layout.activity_wonders

    override fun providePresenter() = WondersPresenterImpl(app)

    override fun provideView() = this


}