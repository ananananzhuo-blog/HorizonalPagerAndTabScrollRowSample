package com.broaddeep.composetestdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TabRowView() {
    val data = remember {
        mutableStateListOf("苹果","香蕉","菠萝","鸭梨")
    }
    var index by remember {
        mutableStateOf(0)
    }
    TabRow(selectedTabIndex = index, modifier = Modifier
        .height(50.dp)
        .width(200.dp)
        ,divider = {
                   Divider(Modifier.width(50.dp))
        }
        ,backgroundColor = Color.Green,indicator = {tabPositions->
            Box(//设置指示器
                Modifier
                    .tabIndicatorOffset(tabPositions[index])
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = Color.Red)
            )
        }) {
        data.forEachIndexed { i: Int, str: String ->
            Box(Modifier
                .width(100.dp)
                .clickable {
                    index = i
                },contentAlignment = Alignment.Center) {
                Text(text = str)
            }
        }
    }
}
