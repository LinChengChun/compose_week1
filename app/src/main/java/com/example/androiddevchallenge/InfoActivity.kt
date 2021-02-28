package com.example.androiddevchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import java.time.Year
import androidx.navigation.compose.rememberNavController as rememberNavController

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("url")
        val id = intent.getStringExtra("id")
        val year = intent.getIntExtra("year", 0)
        val name = intent.getStringExtra("name")
        setContent {
            MyTheme {
                if (url != null) {
                    if (name != null) {
                        if (id != null) {
                            ArtistCard(url = url, name = name, id = id, year = year)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    Surface(color = Color.Yellow) {
        content()
    }
}

@Composable
fun ArtistCard(url: String, name: String, id: String, year: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Compose_week1 detail page")
                },
                actions = {
                }
            )
        }
    ) { innerPadding ->
        val padding = 16.dp
        Column(
            Modifier
                .clickable(onClick = {  })
                .padding(padding)
                .fillMaxWidth()
        ) {
            Text("名称：$name")
            Text("ID号：$id")
            Text("岁龄: $year")
            Spacer(Modifier.height(padding))
            Card(elevation = 4.dp, modifier = Modifier
                .fillMaxWidth()
            ) {
                CoilImage(
                    data = url,
                    contentDescription = "Android Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        }
    }


}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {
//    val navController = rememberNavController()

    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected })
    )
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}