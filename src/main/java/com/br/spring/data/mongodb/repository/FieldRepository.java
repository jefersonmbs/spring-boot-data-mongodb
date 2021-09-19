package com.br.spring.data.mongodb.repository;

import java.util.List;

import com.br.spring.data.mongodb.model.FieldTeste;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FieldRepository extends MongoRepository<FieldTeste, String> {
  List<FieldTeste> findByPublished(boolean published);
  List<FieldTeste> findByTitleContaining(String title);
}
