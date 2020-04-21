package india.ashish.criminalintent

import android.app.Application
import india.ashish.criminalintent.database.CrimeRepository

class CriminalIntentApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.intialize(this)
    }
}