package ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Entity.SuperHeroEntity;
import Repository.SuperHeroRepository;

public class SuperHeroViewModel extends AndroidViewModel {
        private SuperHeroRepository repository;

        public SuperHeroViewModel(@NonNull Application application) {
        super(application);
        repository =new SuperHeroRepository(application);
        }
    public LiveData<List<SuperHeroEntity>> fetchList(){
        return repository.fetchData();
    }

    public void deleteRC(){

        //repository.delete();
    }
}
