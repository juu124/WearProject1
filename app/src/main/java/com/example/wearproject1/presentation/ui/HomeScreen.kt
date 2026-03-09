package com.example.wearproject1.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition

@Composable
fun HomeScreen(navController: NavHostController) {
    // 스크롤 정보를 유지하기 위한 상태 선언..
    // ScalingLazyColumn 을 이용해 목록을 제공하고 유저 스크로로 항목을 보게 하고자 한다.
    // ScalingLazyColumn 내부에 스크롤 정보를 유지하는 상태가 이지 선언 되어 있고. 그 상태를 그대로 이용해도 되지만
    // ScalingLazyColumn 의 스크롤 상태와 PositionIndicator의 상태를 동일하게 지정할려고 rememberScalingLazyListState를 사용한 것이다.

    val listState = rememberScalingLazyListState()

    Scaffold(
        timeText = { TimeText() },
        // Vignette : 위 아래 흐리게 처리
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    ) {
        ScalingLazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = 32.dp,
                bottom = 32.dp
            )
        ) {
            item {
                ListHeader {
                    Text(
                        text = "Wear OS UI",
                        textAlign = TextAlign.Center
                    )
                }
            }
            item {
                Chip(
                    onClick = { navController.navigate("circular") },
                    label = { Text("Circular") }
                )
            }
        }
    }
}