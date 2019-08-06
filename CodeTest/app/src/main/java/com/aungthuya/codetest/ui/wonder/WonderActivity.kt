package com.aungthuya.codetest.ui.wonder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.aungthuya.codetest.R
import com.aungthuya.codetest.model.Wonder
import com.aungthuya.codetest.ui.base.AbsActivity
import com.aungthuya.codetest.util.load
import kotlinx.android.synthetic.main.activity_wonder.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * This activity show the detail information of wonder place.
 *
 * @author Aung Thuya
 * @since 28 July 2019
 */
class WonderActivity: AbsActivity<WonderContract.WonderPresenter, WonderContract.WonderView>(),
    WonderContract.WonderView {

    override fun getLayoutResId() = R.layout.activity_wonder

    override fun providePresenter() = WonderPresenterImpl(app)

    override fun provideView() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup toolbar
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)
        tvToolbar.setText(R.string.wonders)

        // Get wonder object from intent
        val wonder = intent.getSerializableExtra(KEY_WONDER) as Wonder

        // show information
        ivImage.load(wonder.imageUrl)
        tvLocation.text = wonder.location
        tvDescription.text = wonder.description
    }

    companion object {

        private const val KEY_WONDER = "wonder_intent_key"

        /**
         * Get the launcher instance of WonderActivity with parameter object.
         *
         * @param context Activity Context.
         * @param wonder The object to shown in this activity.
         * @return The launcher intent of WonderActivity
         */
        fun getLauncherIntent(context: Context, wonder: Wonder): Intent {
            // create intent
            val intent = Intent(context, WonderActivity::class.java)
            intent.putExtra(KEY_WONDER, wonder)

            return intent
        }
    }
}