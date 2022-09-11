package ru.ermolnik.news

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mts.data.news.repository.News

@Composable
fun NewsListItem(item: News) {
    Card(Modifier.wrapContentSize()) {
        ConstraintLayout {
            val (title, poster, content) = createRefs()

            Text(
                text = item.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                    },
                overflow = TextOverflow.Ellipsis
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://rabkor.ru/wp-content/uploads/2020/02/D_1_RDcXYAACiq_.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = "news poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier.constrainAs(poster) {
                    top.linkTo(title.bottom)
                }
            )

            Text(
                text = item.content,
                style = MaterialTheme.typography.body1,
                maxLines = 3,
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(poster.bottom)
                    }
                    .padding(bottom = 5.dp),
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}