> 关注公众号学习更多知识
>
>![](https://img-blog.csdnimg.cn/img_convert/6dd2df09156ca4cbfc44ad68c9baa2e4.png)


## 前言
首先一个问题`ScrollableTabRow`和`HorizontalPager`是什么？

答：`ScrollableTabRow`相当于View体系中的`TabView`，`HorizontalPager`相当于View体系中的`ViewPager`。这么说很明了了吧，哈哈。

## 导入依赖
Pager需要导入依赖如下：

```groovy
implementation "com.google.accompanist:accompanist-pager:0.18.0"
```


## 用法介绍

```kt
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
```
## 扩展
Pager库中还有VerticalPager用来实现垂直的Banner。

ScrollableTabRow还有配套的不可滑动的TabRow实现固定的Tab效果。

## 效果

![](https://files.mdnice.com/user/15648/fd3095ff-5c00-483b-ba7b-23c03429895e.gif)



> git：https://github.com/ananananzhuo-blog/HorizonalPagerAndTabScrollRowSample.git