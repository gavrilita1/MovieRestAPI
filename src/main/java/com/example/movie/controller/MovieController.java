package com.example.movie.controller;

import com.example.movie.model.Movie;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        return ResponseEntity.ok(service.save(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title){
        return service.findByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String title){
        service.delete(title);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{title}/watch")
    public ResponseEntity<Movie> watchMovie(@PathVariable String title){
        return service.markWatched(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{title}/unWatch")
    public ResponseEntity<Movie> unWatchMovie(@PathVariable String title){
        return service.markUnwatched(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{title}/{rating}")
    public ResponseEntity<Movie> changeRatingPath(@PathVariable String title, @PathVariable double rating){
        return service.updateRating(title, rating)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{title}/rating")
    public ResponseEntity<Movie> changeRating(@PathVariable String title, @RequestParam double rating){
        return service.updateRating(title, rating)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
