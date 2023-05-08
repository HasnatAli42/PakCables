package com.hsa.pakcables.classes

data class DragPosition(
    val x: Float,
    val y: Float
) {
    operator fun plus(other: DragPosition): DragPosition {
        return DragPosition(x + other.x, y + other.y)
    }

    companion object {
        val Zero = DragPosition(0f, 0f)
    }
}