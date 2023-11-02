package Interfaces.Services;

import java.util.List;

public interface IMoodService {

    List<Integer> getAllHumeurs() throws Exception;
    void insertMood(int humeur) throws Exception ;
    void MoodSubmited(int idEmployee)throws Exception;
    boolean CheckMood(int idEmployee) throws Exception;
}
