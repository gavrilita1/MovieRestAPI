package com.example.movie.service;

import com.example.movie.model.Movie;
import com.example.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    MovieRepository repository;

   public MovieService(MovieRepository repository){
       this.repository = repository;
   }

    public Movie save(Movie movie){
        return repository.save(movie);
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }
}
