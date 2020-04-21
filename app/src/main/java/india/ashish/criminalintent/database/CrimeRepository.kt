package india.ashish.criminalintent.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import india.ashish.criminalintent.model.Crime
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"


class CrimeRepository private constructor(context: Context){

    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2)
        .build()

    //).build()

    private val crimeDAO = database.crimeDAO()

    private val executor = Executors.newSingleThreadExecutor()

    fun getCrimes(): LiveData<List<Crime>> = crimeDAO.getCrimes()
    fun getCrime(id: UUID): LiveData<Crime?> = crimeDAO.getCrime(id)

    fun updateCrime(crime: Crime){
        executor.execute {
            crimeDAO.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime){
        executor.execute {
            crimeDAO.addCrime(crime)
        }
    }

/*fun getCrimes(): List<Crime> = crimeDAO.getCrimes()
fun getCrime(id: UUID): Crime? = crimeDAO.getCrime(id)*/

companion object{
    private var INSTANCE: CrimeRepository? = null

    fun intialize(context: Context){
        if(INSTANCE == null){
            INSTANCE = CrimeRepository(context)
        }
    }

    fun get(): CrimeRepository{
        return INSTANCE ?:
                throw IllegalStateException("CrimeRepository must be intialized ")
    }
}
}