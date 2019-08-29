package com.example.githubtraining
import android.content.Context
import androidx.room.Room
import com.example.githubtraining.database.AppDataBase
import com.example.githubtraining.database.dao.DaoInfoUser
import com.example.githubtraining.database.modelDB.UserInformationDB
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class InfoUserTest  {

   private lateinit var appDataBase:AppDataBase
    private lateinit var infoUserDao:DaoInfoUser

    @Before
    fun createDb(){
        val context = mock(Context::class.java)
        appDataBase = Room.inMemoryDatabaseBuilder(context,AppDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        infoUserDao = appDataBase.daoInfoUser()
    }

    @After
    fun closeDb(){
        appDataBase.close()
    }

    @Test
    fun insertAndGetUserInfo(){
        val userInfo = UserInformationDB(id = 0,created_at = "",login = "",updated_at = "",avatar_url = "test",bio = "",email = "",encodedUserPass = "",isLogin = false,location = "",public_repos = 0,total_private_repos = 0)
        //infoUserDao.insertInfoUser(userInfo)
       /* val infoUser = infoUserDao.getListOfInfoUser()
            MatcherAssert.assertThat(infoUser[0].avatar_url, `is`("test") )*/


    }
}