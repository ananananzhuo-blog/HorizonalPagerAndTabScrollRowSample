package com.broaddeep.composetestdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ananananzhuo.composelib.ListView
import com.ananananzhuo.composelib.bean.ItemData
import com.broaddeep.composetestdemo.ui.theme.ComposeTestDemoTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}
const val home = "home"
const val HorizonalPagerVew = "HorizonalPager"
const val TabRow = "TabRow"
@ExperimentalPagerApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Greeting() {
    val controller = rememberNavController()
    NavHost(navController = controller,startDestination = home ){
        composable(home){
            Home(controller)
        }
        composable(TabRow){
            TabRowView()
        }
    }
}
@Composable
fun Home(controller: NavHostController) {
    ListView(datas = mutableListOf(
        ItemData(title = "HorizonalPager、TabScrollView、Dialog使用",tag = HorizonalPagerVew),
        ItemData(title = "TabRow使用",tag = TabRow),
    ), click = { itemData: ItemData, i: Int, i1: Int ->
        controller.navigate(itemData.tag)
    })
}