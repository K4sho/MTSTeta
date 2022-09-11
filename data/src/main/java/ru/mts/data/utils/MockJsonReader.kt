package ru.mts.data.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.BufferedReader
import java.io.InputStreamReader

object MockJsonReader {

    fun <T> read(
        context: Context,
        pathToAsset: String,
        typeToken: TypeToken<T>,
    ): T =
        JsonReader(
            BufferedReader(
                InputStreamReader(context.resources.assets.open(pathToAsset))
            )
        ).let { fileReader ->
            Gson().fromJson(fileReader, typeToken.type)
        }

}