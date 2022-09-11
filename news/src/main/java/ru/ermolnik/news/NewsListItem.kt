package ru.ermolnik.news

import android.widget.LinearLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.mts.data.news.repository.News

@Composable
fun NewsListItem(item: News) {
    Card(Modifier.wrapContentSize()) {
        Text(
            text = item.content,
            modifier = Modifier
                .wrapContentSize(),
            textAlign = TextAlign.Center
        )
    }
}