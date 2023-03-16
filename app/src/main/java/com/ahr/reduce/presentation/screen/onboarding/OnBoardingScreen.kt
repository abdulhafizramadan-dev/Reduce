package com.ahr.reduce.presentation.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.OnBoarding
import com.ahr.reduce.navigation.IndependentScreen
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.button.ReduceFilledButton
import com.ahr.reduce.ui.theme.ReduceTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val onBoardings = listOf(
        OnBoarding(
            image = R.drawable.on_boarding_1,
            title = R.string.on_boarding_title_1,
            description = R.string.on_boarding_description_1
        ),
        OnBoarding(
            image = R.drawable.on_boarding_2,
            title = R.string.on_boarding_title_2,
            description = R.string.on_boarding_description_2
        ),
        OnBoarding(
            image = R.drawable.on_boarding_3,
            title = R.string.on_boarding_title_3,
            description = R.string.on_boarding_description_3
        ),
    )

    val endOfOnBoarding by remember {
        derivedStateOf { pagerState.currentPage == onBoardings.size - 1 }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Skip(
            onSkipClicked = { navigator.navigateToAuthGraph(IndependentScreen.OnBoarding.route) },
            modifier = Modifier
                .align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(80.dp))
        HorizontalPager(
            state = pagerState,
            pageCount = onBoardings.size,
            modifier = Modifier.weight(7f),
        ) { index ->
            OnBoardingContent(
                onBoarding = onBoardings[index],
                modifier = Modifier.fillMaxSize()
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = onBoardings.size,
            modifier = Modifier.weight(2f),
            indicatorHeight = 11.dp,
            indicatorWidth = 11.dp,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
        )
        val titleButton = if (endOfOnBoarding) {
            R.string.start
        } else {
            R.string.furthermore
        }
        val onButtonClicked: () -> Unit = {
            if (endOfOnBoarding) {
                onBoardingViewModel.saveOnBoardingState(true)
                navigator.navigateToAuthGraph(IndependentScreen.OnBoarding.route)
            } else {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            ReduceFilledButton(
                title = titleButton,
                onButtonClicked = onButtonClicked,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
fun Skip(
    onSkipClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onSkipClicked,
        modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Skip",
            color = Color(0xff252525),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoardingScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        OnBoardingScreen(navigator = Navigator(navController))
    }
}