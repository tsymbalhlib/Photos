package com.example.android.photos.repository

import com.example.android.photos.R
import com.example.android.photos.database.PhotoDatabaseDao
import com.example.android.photos.mappers.DatabaseMapper
import com.example.android.photos.network.PhotoApiService
import com.example.android.photos.network.PhotoNetwork
import com.example.android.photos.mappers.NetworkMapper
import com.example.android.photos.network.networkhelper.NetworkHelper
import com.example.android.photos.photos.Photo
import com.example.android.photos.util.PhotoApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val photoDatabaseDao: PhotoDatabaseDao,
    private val photoApiService: PhotoApiService,
    private val databaseMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper,
    private val networkHelper: NetworkHelper
) : Repository {

    override fun getPhotosFromDb(): Flow<List<Photo>> {
        return photoDatabaseDao.getPhotos().map { list ->
            databaseMapper.asPhoto(list)
        }
    }

    override suspend fun getPhotosFromNetwork(): Flow<PhotoApiStatus> = flow {
        emit(PhotoApiStatus.Loading)
        try {
            if (networkHelper.isNetworkConnected()) {
                val response = loadPhotos()
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        if (list.isNotEmpty()) {
                            updatePhotos(list)
                            emit(PhotoApiStatus.Success)
                        } else {
                            emit(PhotoApiStatus.Error(R.string.error_data_loading))
                        }
                    }
                } else {
                    emit(PhotoApiStatus.Error(R.string.error_data_loading))
                }
            } else {
                emit(PhotoApiStatus.Error(R.string.check_your_internet_connection))
            }
        } catch (e: Exception) {
            emit(PhotoApiStatus.Error(R.string.error_data_loading))
        }
    }

    private suspend fun loadPhotos(): Response<List<PhotoNetwork>> = withContext(Dispatchers.IO) {
        photoApiService.loadPhotos()
    }

    private suspend fun updatePhotos(
        list: List<PhotoNetwork>
    ) = withContext(Dispatchers.IO) {
        photoDatabaseDao.insertPhotos(networkMapper.asPhotoEntity(list))
    }
}