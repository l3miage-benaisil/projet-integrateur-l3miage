package fr.uga.l3miage.example.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String label;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Reponse> reponses;


    // Getters
    public String getLabel() {
        return label;
    }
    public long getId() {
        return id;
    }
    public List<Reponse> getReponses() {
        return reponses;
    }

    // Setters
    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setId(long id) {
        this.id = id;
    }
}
