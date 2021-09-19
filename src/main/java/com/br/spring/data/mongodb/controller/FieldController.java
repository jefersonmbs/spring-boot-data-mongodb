package com.br.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.br.spring.data.mongodb.model.FieldTeste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.spring.data.mongodb.repository.FieldRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FieldController {

  @Autowired
  FieldRepository fieldRepository;

  @GetMapping("/fields")
  public ResponseEntity<List<FieldTeste>> getAllfields(@RequestParam(required = false) String title) {
    try {
      List<FieldTeste> fields = new ArrayList<FieldTeste>();

      if (title == null)
        fieldRepository.findAll().forEach(fields::add);
      else
        fieldRepository.findByTitleContaining(title).forEach(fields::add);

      if (fields.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(fields, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/fields/{id}")
  public ResponseEntity<FieldTeste> getTutorialById(@PathVariable("id") String id) {
    Optional<FieldTeste> tutorialData = fieldRepository.findById(id);

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/fields")
  public ResponseEntity<FieldTeste> createTutorial(@RequestBody FieldTeste tutorial) {
    try {
      FieldTeste _tutorial = fieldRepository.save(new FieldTeste(tutorial.getTitle(), tutorial.getDescription(), false));
      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/fields/{id}")
  public ResponseEntity<FieldTeste> updateTutorial(@PathVariable("id") String id, @RequestBody FieldTeste tutorial) {
    Optional<FieldTeste> tutorialData = fieldRepository.findById(id);

    if (tutorialData.isPresent()) {
      FieldTeste _tutorial = tutorialData.get();
      _tutorial.setTitle(tutorial.getTitle());
      _tutorial.setDescription(tutorial.getDescription());
      _tutorial.setPublished(tutorial.isPublished());
      return new ResponseEntity<>(fieldRepository.save(_tutorial), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/fields/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
    try {
      fieldRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/fields")
  public ResponseEntity<HttpStatus> deleteAllfields() {
    try {
      fieldRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/fields/published")
  public ResponseEntity<List<FieldTeste>> findByPublished() {
    try {
      List<FieldTeste> fields = fieldRepository.findByPublished(true);

      if (fields.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(fields, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
