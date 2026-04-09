package edu.unicauca.aplimovil.ufriendly.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.relation.GradeWithSubject
import edu.unicauca.aplimovil.ufriendly.data.repository.GradeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class GradeUiState(
    val gradeList: List<GradeWithSubject> = emptyList()
)

class GradeViewModel(private val repository: GradeRepository): ViewModel(){
    val  uiState: StateFlow<GradeUiState> = repository.allGradesWithSubject
        .map { GradeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GradeUiState()
        )

    fun addGrade(grade: Grade){
        viewModelScope.launch {
            repository.insert(grade)
        }
    }

    fun updateGrade(grade: Grade) {
        viewModelScope.launch {
            repository.update(grade)
        }
    }

    fun deleteGrade(grade: Grade) {
        viewModelScope.launch {
            repository.delete(grade)
        }
    }

    companion object{
        fun provideFactory(repository: GradeRepository): ViewModelProvider.Factory=
            object : ViewModelProvider.Factory{
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return GradeViewModel(repository) as T
                }
            }
    }
}