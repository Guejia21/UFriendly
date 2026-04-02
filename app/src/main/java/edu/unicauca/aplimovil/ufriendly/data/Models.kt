package edu.unicauca.aplimovil.ufriendly.data

data class DashboardState(
    val userName: String,
    val pendingCount: Int,
    val doneCount: Int,
    val expiredCount: Int
)
interface SaveableItem{
    fun isValid(): Boolean
}