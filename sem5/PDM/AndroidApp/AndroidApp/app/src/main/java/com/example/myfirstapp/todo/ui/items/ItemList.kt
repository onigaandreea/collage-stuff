package com.example.myfirstapp.todo.ui.items

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myfirstapp.todo.data.Series
import com.example.myfirstapp.todo.ui.toBitmap

typealias OnItemFn = (id: String?) -> Unit

@Composable
fun ItemList(seriesList: List<Series>, onItemClick: OnItemFn, modifier: Modifier) {
    Log.d("ItemList", "recompose")
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        items(seriesList) { item ->
            ItemDetail(item, onItemClick)
        }
    }
}

@Composable
fun ItemDetail(series: Series, onItemClick: OnItemFn) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (isPressed) 2f else 1f, label = "")

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.White,
        targetValue = Color(0xFFD8BFD8),
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )


    Log.d("ItemDetail", "recompose id = ${series._id}, title: ${series.title}")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(series._id) }
            .padding(5.dp)
            .background(MaterialTheme.colorScheme.background) // Set background color
            .clip(MaterialTheme.shapes.medium) // Apply rounded corners
            .border(
                border = BorderStroke(1.2.dp, MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.medium
            )
            .fillMaxSize().background(color)
    ) {
        Column(
            modifier = Modifier	
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = series.title,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                AvailabilityDot(series = series)
            }
            Text(
                text = "Episodes: ${series.episodes}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = "Date: ${series.date}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 250.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
                .clickable {
                    isPressed = !isPressed
                }
                .graphicsLayer(scaleX = scale, scaleY = scale)
        ) {

            CustomImage(url = series.imageUrl)

        }
    }
}

@Composable
fun CustomImage(url: String, modifier: Modifier = Modifier) {
    Log.d("CustomImage", "Image is visible")
    AsyncImage(
        modifier = modifier.size(size = 100.dp),
        model = toBitmap(url),
        contentDescription = null
    )

}

@Composable
fun AvailabilityDot(series: Series) {
    val dotColor = if (series.available) Color(0x814BCEC1) else Color(0xFFEFA5FF)
    Box(
        modifier = Modifier
            .size(15.dp)
            .clip(MaterialTheme.shapes.small)
            .background(dotColor),
    )
}
