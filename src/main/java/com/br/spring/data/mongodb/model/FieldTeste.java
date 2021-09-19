package com.br.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "testeField")
public class FieldTeste {
  @Id
  private String id;

  @Field("Titulo")
  private String title;

  @Field("Descricao")
  private String description;


  private boolean published;

  public FieldTeste() {

  }

  public FieldTeste(String title, String description, boolean published) {
    this.title = title;
    this.description = description;
    this.published = published;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  @Override
  public String toString() {
    return "Tutorial{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", published=" + published +
            '}';
  }
}
