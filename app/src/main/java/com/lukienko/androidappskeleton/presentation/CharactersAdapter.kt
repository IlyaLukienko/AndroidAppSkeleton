package com.lukienko.androidappskeleton.presentation

import android.content.Context
import android.widget.ArrayAdapter

class CharactersAdapter(
    context: Context,
    resource: Int,
    objects: Array<String?>
) : ArrayAdapter<String?>(context, resource, objects)