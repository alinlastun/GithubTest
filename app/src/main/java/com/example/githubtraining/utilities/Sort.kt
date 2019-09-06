package com.example.githubtraining.utilities

import com.example.githubtraining.db.model.InfoRepoModelDB
import com.example.githubtraining.utilities.enums.SortType

class Sort {

    fun sortListBy(list: MutableList<InfoRepoModelDB>,listName: SortType){
       when (listName){
           SortType.CREATED -> list.sortBy { it.created_at?.toLowerCase()  }
           SortType.UPDATED -> list.sortBy { it.updated_at?.toLowerCase()  }
           SortType.PUSHED ->list.sortBy { it.pushed_at?.toLowerCase()  }
           SortType.FULL_NAME ->list.sortBy { it.full_name?.toLowerCase() }
       }
    }
}