package edu.unicauca.aplimovil.ufriendly.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.ufriendly.network.BookDoc
import edu.unicauca.aplimovil.ufriendly.network.LibraryApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface LibraryUiState {
    data class Success(val books: List<BookDoc>) : LibraryUiState
    object Error : LibraryUiState
    object Loading : LibraryUiState
}

class LibraryViewModel(subjecName:String) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var libraryUiState: LibraryUiState by mutableStateOf(LibraryUiState.Loading)
        private set

    /**
     * Call searchBooks(subjectName) on init so we can display status immediately.
     */
    init {
        searchBooks(subjecName)
    }

    /**
     * Gets Books related to the subject name fromm OpenLibrary API Retrofit service
     * and updates the [LibraryUiState] with the data of the response (success, error, loading)
     */
    fun searchBooks(subjecName: String) {
        viewModelScope.launch {
            try {
                val listResult = LibraryApi.retrofitService.searchBooks(subjecName)
                libraryUiState = LibraryUiState.Success(
                    listResult.books
                )
            } catch (e: IOException) {
                libraryUiState = LibraryUiState.Error
            }
        }
    }
    companion object{
        fun provideFactory(subjectName: String): ViewModelProvider.Factory=
            object : ViewModelProvider.Factory{
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LibraryViewModel(subjectName) as T
                }
            }
    }
}