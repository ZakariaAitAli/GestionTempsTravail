package Interfaces.Services;

import DAO.Humeur;

import java.util.List;

public interface IMoodService {

    List<Integer> getAllHumeurs() throws Exception;
}
