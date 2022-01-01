package com.broaddeep.composetestdemo

import android.os.Trace
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.os.trace
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizonalPagerVew() {
    val datas = remember {
        mutableStateListOf("香蕉", "苹果", "芒果", "萝卜", "咖啡")
    }

    val state = rememberPagerState(pageCount = datas.size,
        initialPage = 2,
        infiniteLoop = true,
        initialOffscreenLimit = 1)
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = it){data->
            Snackbar(
                snackbarData = data,
                backgroundColor = Color.Green,
                contentColor = Color.White,
                shape = CutCornerShape(10.dp)
            )
        }

    },
    scaffoldState = scaffoldState,
    bottomBar = {
        BottomAppBar {
            Text(text = "底部导航栏")
        }
    }
    ) {
        Column {
            ScrollableTabRow(selectedTabIndex = state.currentPage, backgroundColor = Color.Green) {
                datas.forEachIndexed { index, data ->
                    Box(Modifier
                        .height(40.dp)
                        .width(100.dp)
                        .clickable {
                            scope.launch {
                                state.scrollToPage(index, 0f)
                            }
                        }, contentAlignment = Alignment.Center) {
                        Text(text = data)
                    }
                }
            }
            HorizontalPager(state = state, modifier = Modifier.height(300.dp)) { pagePosition ->
                Log.e("tag", "加载页码$pagePosition")
                val color = (0..255)
                Box(Modifier
                    .fillMaxSize()
                    .background(Color(color.random(), color.random(), color.random())),
                    contentAlignment = Alignment.Center) {
                    Text(text = datas[pagePosition])
                }
            }
            Button(onClick = {
                Trace.beginSection("")
                trace(""){
                    (0..30).forEach {
                        println("index:$it")
                    }
                }
                scope.launch {
                    val showSnackbar = scaffoldState.snackbarHostState.showSnackbar("哈哈哈哈",
                        duration = SnackbarDuration.Short)
                }
            }) {
                Text(text = "showToast")
            }

        }
    }
}