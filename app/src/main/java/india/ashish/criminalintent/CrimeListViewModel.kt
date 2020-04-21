package india.ashish.criminalintent

import androidx.lifecycle.ViewModel
import india.ashish.criminalintent.database.CrimeRepository
import india.ashish.criminalintent.model.Crime

class CrimeListViewModel : ViewModel(){

    /*val crimes = mutableListOf<Crime>()
    init {
        for (i in 0 until 100){
             val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            crimes += crime

        }
    }*/

    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()

    fun addcrime(crime: Crime){
        crimeRepository.addCrime(crime)
    }
}