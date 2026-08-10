package com.rodrigocarreon.spideytracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodrigocarreon.spideytracker.core.RetrofitHelper
import com.rodrigocarreon.spideytracker.data.SightingRepository
import com.rodrigocarreon.spideytracker.data.network.SightingClient
import com.rodrigocarreon.spideytracker.data.network.SightingService
import com.rodrigocarreon.spideytracker.domain.GetSightingsUseCase

val ViewModelFactory = object : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val retrofit = RetrofitHelper.getRetrofit()
        val client = retrofit.create(SightingClient::class.java)

        val service = SightingService(client)
        val repository = SightingRepository(service)
        val useCase = GetSightingsUseCase(repository)

        @Suppress("UNCHECKED_CAST")
        return SightingViewModel(useCase) as T
    }
}