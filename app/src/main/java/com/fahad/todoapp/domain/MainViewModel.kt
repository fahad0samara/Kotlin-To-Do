package com.fahad.todoapp.domain


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahad.todoapp.data.local.dto.LocalNote
import com.fahad.todoapp.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val stateUi= mutableStateOf(StateUtil())


    init {
        getNotes()
    }


    fun getNotes() {
        when (stateUi.value.displayType) {
            DISPLAY_TYPE.ALL -> {
                viewModelScope.launch {
                    repository.getNotes().collect{
                            notesList->
                        stateUi.value=stateUi.value.copy(
                            currentNote = notesList
                        )
                    }

                }


            }
            DISPLAY_TYPE.COMPLETED -> {
                viewModelScope.launch {
                    repository.getFinishedNotes(true).collect{
                            notesList->
                        stateUi.value=stateUi.value.copy(
                            currentNote = notesList
                        )
                    }

                }


            }
            DISPLAY_TYPE.UNCOMPLETED -> {
                viewModelScope.launch {
                    repository.getFinishedNotes(false).collect{
                            notesList->
                        stateUi.value=stateUi.value.copy(
                            currentNote = notesList
                        )
                    }

                }


            }
            DISPLAY_TYPE.FINISHED -> {
                viewModelScope.launch {
                    repository.getPinnedNotes(true).collect{
                            notesList->
                        stateUi.value=stateUi.value.copy(
                            currentNote = notesList
                        )
                    }

                }


            }
            DISPLAY_TYPE.UNFINISHED -> {
                viewModelScope.launch {
                    repository.getPinnedNotes(false).collect{
                            notesList->
                        stateUi.value=stateUi.value.copy(
                            currentNote = notesList
                        )
                    }

                }


            }



        }



    }

    fun  insert(note: LocalNote) {
        viewModelScope.launch {
            repository.insert(note)
        }



    }

    fun delete(note: LocalNote) {
        viewModelScope.launch {
            repository.delete(note)
        }

    }

    fun update(note: LocalNote) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    fun onDisplayTypeChange(displayType: DISPLAY_TYPE) {
        stateUi.value=stateUi.value.copy(
            displayType = displayType
        )
        getNotes()
    }



}