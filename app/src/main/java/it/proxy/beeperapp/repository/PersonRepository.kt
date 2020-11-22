package it.proxy.beeperapp.repository

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import it.proxy.beeperapp.rest.Person
import it.proxy.beeperapp.rest.PersonItem
import it.proxy.beeperapp.rest.PersonRetrofitInstance
import it.proxy.beeperapp.rest.PersonService
import retrofit2.Response

class PersonRepository {

    var resultList = ArrayList<PersonItem>()
    var haveResult = true

    private val retrofitInstance = PersonRetrofitInstance
        .getPersonRetrofitInstance()
        .create(PersonService::class.java)

    suspend fun getPersonById(id: String, lifeCycleOwner: LifecycleOwner) {
        val personLiveData: LiveData<Response<Person>> = liveData {
            val response = retrofitInstance.getPersonById(id)
            emit(response)
        }
        personLiveData.observe(lifeCycleOwner, Observer {
            val iterator = it.body()!!.listIterator()
            if (!iterator.hasNext()) haveResult = false
            else {
                while (iterator.hasNext()) {
                    haveResult = true
                    resultList.add(iterator.next())
                }
            }
        })
    }

    suspend fun getPersonByName(name: String, lifeCycleOwner: LifecycleOwner) {
        if (name.isNotEmpty() || name != "") {
            val personLiveData: LiveData<Response<Person>> = liveData {
                val response = retrofitInstance.getPersonByName(name)
                emit(response)
            }
            personLiveData.observe(lifeCycleOwner, Observer {
                val iterator = it.body()!!.listIterator()
                if (!iterator.hasNext()) haveResult = false
                else {
                    while (iterator.hasNext()) {
                        haveResult = true
                        resultList.add(iterator.next())
                    }
                }
                Log.i(PersonRepository::class.simpleName, resultList.toString())
            })
        } else haveResult = false
    }

    suspend fun getPersonBySurname(surname: String, lifeCycleOwner: LifecycleOwner) {
        if (surname.isNotEmpty() || surname != "") {
            val personLiveData: LiveData<Response<Person>> = liveData {
                val response = retrofitInstance.getPersonBySurname(surname)
                emit(response)
            }
            personLiveData.observe(lifeCycleOwner, Observer {
                val iterator = it.body()!!.listIterator()
                if (!iterator.hasNext()) haveResult = false
                else {
                    while (iterator.hasNext()) {
                        haveResult = true
                        resultList.add(iterator.next())
                    }
                }
            })
        } else haveResult = false
    }

    suspend fun getPersonByPhoneNumber(phoneNumber: String, lifeCycleOwner: LifecycleOwner) {
        if (phoneNumber.isNotEmpty() || phoneNumber != "") {
            val personLiveData: LiveData<Response<Person>> = liveData {
                val response = retrofitInstance.getPersonByPhoneNumber(phoneNumber)
                emit(response)
            }
            personLiveData.observe(lifeCycleOwner, Observer {
                val iterator = it.body()!!.listIterator()
                if (!iterator.hasNext()) haveResult = false
                else {
                    while (iterator.hasNext()) {
                        haveResult = true
                        resultList.add(iterator.next())
                    }
                }
            })
        } else haveResult = false
    }
}