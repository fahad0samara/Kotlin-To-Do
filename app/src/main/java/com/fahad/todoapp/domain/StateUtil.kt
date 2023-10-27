package com.fahad.todoapp.domain

import com.fahad.todoapp.data.local.dto.LocalNote

data class StateUtil (
    val displayType: DISPLAY_TYPE = DISPLAY_TYPE.ALL,
    val currentNote:List<LocalNote> = emptyList(),



    )

