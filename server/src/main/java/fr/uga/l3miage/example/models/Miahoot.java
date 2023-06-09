package fr.uga.l3miage.example.models;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "miahoot")
public class Miahoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Question> questions;

    public void addQuestion(Question question){
        this.questions.add(question);
    }

    //contains by id

    public boolean containsQuestion(Long idQuestion){
        for(Question q : this.questions){
            if(q.getId() == idQuestion){
                return true;
            }
        }
        return false;
    }

    //get question by id

   public Question getQuestion(Long idQuestion) {
        for(Question q : this.questions){
            if(q.getId() == idQuestion){
                return q;
            }
        }
        return null;
    }

    public void deleteQuestion(Long idQuestion) {
        for(Question q : this.questions){
            if(q.getId() == idQuestion){
                this.questions.remove(q);
            }
        }
    }

    public void addQuestions(List<Question> questions){
        this.questions.addAll(questions);
    }
}
