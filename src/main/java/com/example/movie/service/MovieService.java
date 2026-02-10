package com.example.movie.service;

import com.example.movie.model.Movie;
import com.example.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Movie> findByTitle(String title){
       return repository.findByTitle(title);
    }

    public void delete(String title){
       repository.delete(title);
    }

    public Optional<Movie> markWatched(String title){
       Optional<Movie> maybeMovie = repository.findByTitle(title);
       if(maybeMovie.isPresent()){
           Movie movie = maybeMovie.get();
           movie.setWatched(true);
           repository.save(movie);
           return Optional.of(movie);
       }
       return Optional.empty();
    }

    public Optional<Movie> markUnwatched(String title){
       Optional<Movie> maybeMovie = repository.findByTitle(title);
       if (maybeMovie.isPresent()){
           Movie movie = maybeMovie.get();
           if (movie.isWatched()){
               movie.setWatched(false);
               repository.save(movie);
           }
           return Optional.of(movie);
       }
       return Optional.empty();
    }

    public Optional<Movie> updateRating(String title, double rating){
       Optional<Movie> maybeMovie = repository.findByTitle(title);
       if(maybeMovie.isPresent()){
           Movie movie = maybeMovie.get();
           movie.setRating(rating);
           repository.save(movie);
           return Optional.of(movie);
       }
       return Optional.empty();
    }


}
