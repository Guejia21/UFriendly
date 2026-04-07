package edu.unicauca.aplimovil.ufriendly.ui.screens

import androidx.compose.foundation.Image
import edu.unicauca.aplimovil.ufriendly.R
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.network.BookDoc
import edu.unicauca.aplimovil.ufriendly.ui.components.TopBar
import edu.unicauca.aplimovil.ufriendly.ui.viewModels.LibraryUiState



@Composable
fun SubjectDetailScreen(
    subject: Subject,
    libraryUiState: LibraryUiState,
    navController: NavHostController
) {
    GenericScreen(
        navController = navController,
        topBar = {TopBar("Suggested Reading for ${subject.name}")}
    ) {
        when (libraryUiState) {
            is LibraryUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is LibraryUiState.Success -> if(libraryUiState.books.isEmpty())
                ErrorScreen(stringResource(R.string.no_books_found),modifier = Modifier.fillMaxSize())
                else{
                    LazyColumn {
                    items(libraryUiState.books) { book ->
                        BookSuggestionCard(book)
                    }
                }
            }
            is LibraryUiState.Error -> ErrorScreen( message= stringResource(R.string.loading_failed),modifier = Modifier.fillMaxSize())
        }
    }

}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(message:String,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = message , modifier = Modifier.padding(16.dp))
    }
}
@Composable
fun BookSuggestionCard(book: BookDoc) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            // Cover image via Coil
            AsyncImage(
                model = book.coverUrl("M") ?: "https://instagram.fclo7-1.fna.fbcdn.net/v/t51.82787-19/610643973_18050922026692761_2042621266485195796_n.jpg?stp=dst-jpg_s150x150_tt6&efg=eyJ2ZW5jb2RlX3RhZyI6InByb2ZpbGVfcGljLmRqYW5nby4xMDgwLmMyIn0&_nc_ht=instagram.fclo7-1.fna.fbcdn.net&_nc_cat=109&_nc_oc=Q6cZ2gE9soXW90l8liI6ue1Y0Zkn3v4islrJi0lCWwOkhBFb4SmYBh5aBUrpTuWEU9yHtplwEfKSqn5dRFEZPgRPkGgU&_nc_ohc=qYTFEs9XEVoQ7kNvwGJRzx_&_nc_gid=HlczH9LsZFeJhBhHhVR_Vw&edm=ALGbJPMBAAAA&ccb=7-5&oh=00_Af0ZcrT-7LfE8wcUuoxRlP2PW3heFIpw-dDih6zzdp2ocw&oe=69DA5AD1&_nc_sid=7d3ac5",
                contentDescription = book.title,
                modifier = Modifier
                    .size(64.dp, 90.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(book.title ?: "Unknown title", style = MaterialTheme.typography.titleMedium)
                Text(
                    book.authors?.firstOrNull() ?: "Unknown author",
                    style = MaterialTheme.typography.bodyMedium
                )
                book.year?.let {
                    Text("$it", style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline)
                }
            }
        }
    }
}

