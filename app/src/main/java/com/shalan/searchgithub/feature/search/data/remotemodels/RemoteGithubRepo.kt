package com.shalan.searchgithub.feature.search.data.remotemodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass


@Entity
@JsonClass(generateAdapter = true)
data class RemoteGithubRepo(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "owner_info") val owner: Owner,
    @ColumnInfo(name = "description") val description: String
)
