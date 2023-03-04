package com.ahr.reduce.ui.screen.market

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MarketFilter(
    onFilterChipClicked: () -> Unit,
    onSortClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = false,
            onClick = onFilterChipClicked,
            label = { Text(text = stringResource(id = R.string.filter)) },
            leadingIcon = { Icon(imageVector = Icons.Filled.Tune, contentDescription = null) }
        )
        FilterChip(
            selected = false,
            onClick = onSortClicked,
            label = { Text(text = stringResource(id = R.string.sort)) },
            trailingIcon = { Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null) }
        )
    }
}