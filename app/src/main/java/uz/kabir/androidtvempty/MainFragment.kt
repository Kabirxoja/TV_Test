package uz.kabir.androidtvempty

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.VerticalGridPresenter
import androidx.core.content.edit


class MainFragment : VerticalGridSupportFragment() {

    companion object {
        const val PREF_SAVE_LAST_APP = "test_prefs"
        const val PREF_LAST_APP_NAME = "last_app"
    }

    private lateinit var arrayObjectAdapter: ArrayObjectAdapter
    private lateinit var pm: PackageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        gridPresenter = VerticalGridPresenter(FocusHighlight.ZOOM_FACTOR_SMALL, false).apply {
            numberOfColumns = 3
            shadowEnabled = false
        }

        val cardPresenter = CardPresenter()
        arrayObjectAdapter = ArrayObjectAdapter(cardPresenter)
        adapter = arrayObjectAdapter

        pm = requireActivity().packageManager
        loadApps()
    }

    private fun loadApps() {

        val apps = pm.queryIntentActivities(Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER)
            addCategory(Intent.CATEGORY_LAUNCHER)
        }, 0)

        // get last app
        val prefs = requireActivity().getSharedPreferences(PREF_SAVE_LAST_APP, Context.MODE_PRIVATE)
        val lastAppLaunched = prefs.getString(PREF_LAST_APP_NAME, null)

        val sortedApps = if (lastAppLaunched != null) {
            val lastApp = apps.find { it.activityInfo.packageName == lastAppLaunched }
            if (lastApp != null) listOf(lastApp) + apps.filter { it != lastApp } else apps
        } else {
            apps
        }

        arrayObjectAdapter.clear()
        sortedApps.forEach { arrayObjectAdapter.add(it) }

        setOnItemViewClickedListener { _, item, _, _ ->
            val resolveInfo = item as ResolveInfo
            val packageName = resolveInfo.activityInfo.packageName
            val launchIntent = pm.getLaunchIntentForPackage(packageName)

            if (launchIntent != null) {
                startActivity(launchIntent)
            } else {
                Toast.makeText(requireContext(), "Cannot open this app", Toast.LENGTH_SHORT).show()
            }

            // save last app
            val prefs =
                requireActivity().getSharedPreferences(PREF_SAVE_LAST_APP, Context.MODE_PRIVATE)
            prefs.edit {
                putString(PREF_LAST_APP_NAME, packageName)
                commit()
            }

        }
    }

    // reload app arrangement
    override fun onStart() {
        super.onStart()
        loadApps()
    }
}

