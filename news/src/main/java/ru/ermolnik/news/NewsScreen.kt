package ru.ermolnik.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val state = viewModel.state.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (state.value) {
                is NewsState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                }
                is NewsState.Error -> {
                    Text(
                        text = (state.value as NewsState.Error).throwable.toString(),
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(onClick = { viewModel.refresh() }) {
                        Text(text = "Повторить")
                    }
                }
                is NewsState.Content -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items((state.value as NewsState.Content).news) { item ->
                            NewsListItem(
                                item
                            )
                            Divider(color = Color.Black, thickness = 2.dp)
                        }
                    }
                }
                is NewsState.Empty -> {
                    Text(
                        text = "Сервер вернул пустой список, попробуйте еще раз",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
