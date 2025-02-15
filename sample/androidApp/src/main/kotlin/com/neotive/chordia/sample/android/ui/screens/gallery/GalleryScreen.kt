package com.neotive.chordia.sample.android.ui.screens.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.neotive.chordia.sample.android.ui.di.Component

object GalleryScreen : Screen {

    private fun readResolve(): Any = GalleryScreen

    @Composable
    override fun Content() {
        Component.images.let { images ->
            Surface {
                if (images.isEmpty()) EmptyBadge()
                LazyRow(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(top = 40.dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(images) { image ->
                        Image(
                            painter = rememberDrawablePainter(image.drawable),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun EmptyBadge() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 40.dp)
        ) {
            Text(
                text = "Empty",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}
