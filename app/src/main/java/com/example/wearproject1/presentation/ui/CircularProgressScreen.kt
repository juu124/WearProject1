package com.example.wearproject1.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import kotlinx.coroutines.delay

@Composable
fun CircularProgressScreen() {
    var progress by remember { mutableStateOf(0f) }
    var isLoading by remember { mutableStateOf(true) }

    // progress 값을 장시간 변경해야 한다..
    // 이 composable 과 생명주기를 같이 했으면 좋겠다..
    // 그 때 유용한 것이 LaunchedEffect이다.
    // LaunchedEffect : composable이 초기화 될때 { } 부분 실행된다.
    // 실행 된 이후에 key 부분이 변경되지 않는한 다시 실행되지는 않는다.
    // key 부분은 Unit이 아니라 개발자 데이터를 지정할 수도 있고, { } 부분이 다시 실행되기를 원할 때 key 값을 변경하면 된다.
    // 지금 변수처럼 이 아니라 Unit으로 단일 값으로 주면 변경되지 않는다. 꼭 Unit이 아니어도 상관없다.
    // 하지만, 아래 코드처럼 Unit으로 단일 값으로 지정하면 최초 한번만 수행된다.
    LaunchedEffect(Unit) {
        while(progress < 1f) {
            delay(50)
            progress += 0.01f
        }
        isLoading = false

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 시계 3시 방향이 0도이다. 시계 방향
        if (isLoading) {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxSize(),
                startAngle = 290f,
                endAngle = 240f,
                strokeWidth = 5.dp
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${(progress * 100).toInt()}%"
                )
                Text(
                    text = "Loading..."
                )
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Green
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("완료!!")
            }
        }
    }
}