/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : AppCompatActivity() {

    val dogs: List<Dog> = List(100) { Dog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, InfoActivity::class.java)

        setContent {
            MyTheme {
                LayoutsCodeLab(dogs, onClick = { dog: Dog, i: Int ->
                    intent.putExtra("id", dog.id)
                    intent.putExtra("url", dog.url)
                    intent.putExtra("year", dog.year)
                    intent.putExtra("name", dog.name+i)
                    startActivity(intent)
                })
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        NewsStory()
//        PhotographerCard()
    }
}

@Composable
fun NewsStory() {
    Column(
        modifier = androidx.compose.ui.Modifier.padding(16.dp)
    ) {
        Text("A day in Shark Fin Cove")
        Text("Davenport, California")
        Text("December 2018")
    }
}

//@Preview
@Composable
fun DefaultPreview() {
    NewsStory()
}

//@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

@Composable
fun LayoutsCodeLab(dogs: List<Dog>,  onClick: (dog: Dog, i: Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Compose_week1")
                },
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(
            Modifier
                .padding(innerPadding)
                .padding(8.dp), dogs, onClick)
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier, dogs: List<Dog>, onClick: (dog: Dog, i: Int) -> Unit) {
    ScrollingList(modifier, dogs, onClick)
}

@Composable
fun LazyList() {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            Text("Item #$it")
        }
    }
}


@Composable
fun PhotographerCard(modifier: Modifier = Modifier, dog: Dog, i: Int, onClick: (dog: Dog, i: Int) -> Unit) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = {
                onClick(dog, i)
            })
            .padding(16.dp)) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
//            val painter = painterResource(id = R.drawable.ic_launcher_background)
//            Image(painter = painter, contentDescription = "hello")
            CoilImage(
                data = dog.url,
                contentDescription = "Android Logo",
                modifier = Modifier.size(50.dp)
            )
        }
        Column(modifier = Modifier
            .padding(start = 8.dp)
            .align(Alignment.CenterVertically)) {
            Text(dog.name+i, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}


@Composable
fun ImageListItem(dog: Dog, i: Int) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        CoilImage(
//            data = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fc%2F528ed16c3fc82.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1617095916&t=af170cb5349dce1c076c96ceeb756341",
//            contentDescription = "Android Logo",
//            modifier = Modifier.size(50.dp)
//        )
//        Spacer(Modifier.width(10.dp))
//        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
//    }
//    PhotographerCard(dog = dog, i = i)
}

@Composable
fun ScrollingList(modifier: Modifier, dogs: List<Dog>, onClick: (dog: Dog, i: Int) -> Unit) {
    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()


    Column {
        LazyColumn(state = scrollState) {
            items(listSize) {
                PhotographerCard(dog = dogs[it], i = it, onClick = onClick)
                Divider(color = Color.Black)
            }
        }
//        Row() {
//            Button(onClick = {
//                coroutineScope.launch {
//                    // 0 is the first item index
//                    scrollState.animateScrollToItem(0)
//                }
//            }) {
//                Text("滑动到顶部")
//            }
//
//            Button(onClick = {
//                coroutineScope.launch {
//                    // listSize - 1 is the last index of the list
//                    scrollState.animateScrollToItem(listSize - 1)
//                }
//            }) {
//                Text("滑动到底部")
//            }
//        }
    }
}
