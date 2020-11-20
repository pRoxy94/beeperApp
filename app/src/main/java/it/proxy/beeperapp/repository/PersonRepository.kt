package it.proxy.beeperapp.repository

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

    val retrofitInstance = PersonRetrofitInstance
        .getPersonRetrofitInstance()
        .create(PersonService::class.java)

    suspend fun getPersonById(id: String, lifeCycleOwner: LifecycleOwner): Person {
        lateinit var resultId: Person
        val personLiveData: LiveData<Response<Person>> = liveData {
            val response = retrofitInstance.getPersonById(id)
            emit(response)
        }
        personLiveData.observe(lifeCycleOwner, Observer {
            val result = it.body()!!
            val iterator = result.listIterator()
            while (iterator.hasNext()) {
                resultId.add(iterator.next())
            }
        })
        return resultId
    }

    suspend fun getPersonByName(name: String, lifeCycleOwner: LifecycleOwner): Person {
        lateinit var resultName: Person
        val personLiveData: LiveData<Response<Person>> = liveData {
            val response = retrofitInstance.getPersonById(name)
            emit(response)
        }
        personLiveData.observe(lifeCycleOwner, Observer {
            val result = it.body()!!
            val iterator = result.listIterator()
            while (iterator.hasNext()) {
                resultName.add(iterator.next())
            }
        })
        return resultName
    }

    suspend fun getPersonBySurname(surname: String, lifeCycleOwner: LifecycleOwner): Person {
        lateinit var resultSurname: Person
        val personLiveData: LiveData<Response<Person>> = liveData {
            val response = retrofitInstance.getPersonById(surname)
            emit(response)
        }
        personLiveData.observe(lifeCycleOwner, Observer {
            val result = it.body()!!
            val iterator = result.listIterator()
            while (iterator.hasNext()) {
                resultSurname.add(iterator.next())
            }
        })
        return resultSurname
    }

    suspend fun getPersonByPhoneNumber(phoneNumber: String, lifeCycleOwner: LifecycleOwner): Person {
        lateinit var resultPhoneNumber: Person
        val personLiveData: LiveData<Response<Person>> = liveData {
            val response = retrofitInstance.getPersonById(phoneNumber)
            emit(response)
        }
        personLiveData.observe(lifeCycleOwner, Observer {
            val result = it.body()!!
            val iterator = result.listIterator()
            while (iterator.hasNext()) {
                resultPhoneNumber.add(iterator.next())
            }
        })
        return resultPhoneNumber
    }
}