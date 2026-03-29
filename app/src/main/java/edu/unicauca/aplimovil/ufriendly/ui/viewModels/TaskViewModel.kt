package edu.unicauca.aplimovil.ufriendly.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class TaskUiState(
    val taskList: List<Task> = emptyList()
)
class TaskViewModel(private val repository: TaskRepository): ViewModel(){
    val  uiState: StateFlow<TaskUiState> = repository.allTasks
        .map { TaskUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TaskUiState()
        )
    fun addTask(task:Task){
        viewModelScope.launch {
            repository.insert(task)
        }
    }
    fun changeStatus(task:Task, status:Boolean){
        viewModelScope.launch {
            val updatedTask = task.copy(isDone = status)
            repository.update(updatedTask)
        }
    }
    fun deleteTask(task:Task){
        viewModelScope.launch {
            repository.delete(task)
        }
    }
    companion object{
        fun provideFactory(repository: TaskRepository): ViewModelProvider.Factory=
            object : ViewModelProvider.Factory{
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskViewModel(repository) as T
                }
            }
    }
}
