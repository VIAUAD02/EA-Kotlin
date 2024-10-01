package hu.bme.aut.aitstudentgradedemo.di


import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.aitstudentgradedemo.data.AppDatabase
import hu.bme.aut.aitstudentgradedemo.data.GradeDAO
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class GradeDbModule {
    @Provides
    fun provideGradeDao(appDatabase: AppDatabase): GradeDAO {
        return appDatabase.gradeDao()
    }

    @Provides
    @Singleton
    fun provideGradeAppDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }
}