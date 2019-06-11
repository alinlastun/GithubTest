package com.example.githubtraining.utill

import com.example.githubtraining.database.modelDB.InfoRepoModelDB

class Sort {

    fun sortListBy(list : MutableList<InfoRepoModelDB>,listName:SortType){
       when(listName){
           SortType.CREATED ->list.sortBy {it.created_at  }
           SortType.UPDATED ->list.sortBy {it.updated_at  }
           SortType.PUSHED ->list.sortBy {it.pushed_at  }
           SortType.FULL_NAME ->list.sortBy {it.full_name  }
       }
    }
}