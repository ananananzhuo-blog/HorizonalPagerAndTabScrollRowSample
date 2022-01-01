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
        initialPage = 0,//初始页码
        infiniteLoop = true,//是否循环效果
        initialOffscreenLimit = 1//预加载页数
    )
    val scope = rememberCoroutineScope()
    Column {
        ScrollableTabRow(selectedTabIndex = state.currentPage,//展示的页码，和Pager的保持一致
            backgroundColor = Color.Green) {
            datas.forEachIndexed { index, data ->
                Box(
                    Modifier
                        .height(40.dp)
                        .width(100.dp)
                        .clickable {
                            scope.launch {
                                state.scrollToPage(index, 0f)//Tab被点击后让Pager中内容动画形式滑动到目标页
                            }
                        }, contentAlignment = Alignment.Center) {
                    Text(text = data)
                }
            }
        }
        HorizontalPager(state = state,//Pager当前所在页数
            modifier = Modifier.height(300.dp)) { pagePosition ->
            Log.e("tag", "加载页码$pagePosition")
            val color = (0..255)
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(color.random(), color.random(), color.random())),
                contentAlignment = Alignment.Center) {
                Text(text = datas[pagePosition])
            }
        }
    }

}