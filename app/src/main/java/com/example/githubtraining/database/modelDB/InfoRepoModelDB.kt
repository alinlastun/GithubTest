package com.example.githubtraining.database.modelDB

import android.arch.persistence.room.*

@Entity(tableName = "repoInfo_table",
    foreignKeys = arrayOf(ForeignKey(entity = OwnerModelDB::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("ownerId"),
    onDelete = ForeignKey.CASCADE)))

data class InfoRepoModelDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "ownerId")
    var ownerId: Int?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "full_name")
    var full_name: String?,

    @ColumnInfo(name = "created_at")
    var created_at: String?,

    @ColumnInfo(name = "updated_at")
    var updated_at: String?,

    @ColumnInfo(name = "pushed_at")
    var pushed_at: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "private")
    var private: Boolean?

)
{
    constructor():this(0,0,"","","","","","",false)
}