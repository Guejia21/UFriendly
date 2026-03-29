package edu.unicauca.aplimovil.ufriendly.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.repository.SubjectRepository
import edu.unicauca.aplimovil.ufriendly.data.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SubjectUiState(
    val subjectList: List<Subject> = emptyList()
)
class SubjectViewModel(private val repository: SubjectRepository): ViewModel(){
    val  uiState: StateFlow<SubjectUiState> = repository.allSubjects
        .map { SubjectUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SubjectUiState()
        )
    fun addSubject(subject:Subject){
        viewModelScope.launch {
            repository.insert(subject)
        }
    }
    fun updateSubject(subject: Subject){
        viewModelScope.launch {
            repository.update(subject)
        }
    }
    fun deleteSubject(subject: Subject){
        viewModelScope.launch {
            repository.delete(subject)
        }
    }
    companion object{
        fun provideFactory(repository: SubjectRepository): ViewModelProvider.Factory=
            object : ViewModelProvider.Factory{
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SubjectViewModel(repository) as T
                }
            }
    }
}
