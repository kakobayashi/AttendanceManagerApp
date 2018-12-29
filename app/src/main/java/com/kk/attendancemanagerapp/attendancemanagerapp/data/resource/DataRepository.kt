package com.kk.attendancemanagerapp.attendancemanagerapp.data.resource

class DataRepository: DataSource {

    companion object {
        // singleTon
        private var sInstance: DataRepository? = null

        fun getInstance(): DataRepository? {
            if (sInstance == null) {
                sInstance = DataRepository()
            }
            return sInstance
        }
    }
}