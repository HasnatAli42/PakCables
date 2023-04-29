package com.hsa.pakcables.functions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun executeAfterDelay(delayMillis: Long, action: () -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        delay(delayMillis)
        action()
    }
}