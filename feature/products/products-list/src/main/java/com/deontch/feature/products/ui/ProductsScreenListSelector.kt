package com.deontch.feature.products.ui

import androidx.compose.runtime.Composable
import com.aliumujib.all.breeds.R

@Composable
fun selectSwitchViewButtonTextSelector(isListView: Boolean): Int {
    return if (isListView) {
        R.string.switch_to_grid_view
    } else {
        R.string.switch_to_list_view
    }
}