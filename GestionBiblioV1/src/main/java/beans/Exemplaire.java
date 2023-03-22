package beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="EXEMPLAIRE"
        ,schema="BIBLIO2"
)
public class Exemplaire  implements java.io.Serializable {



    private int idexp;
    private Livre livre;
    private Set<Emprunt> emprunts = new HashSet(0);

    public Exemplaire() {
    }


    public Exemplaire(int idexp) {
        this.idexp = idexp;
    }
    public Exemplaire(int idexp, Livre livre, Set emprunts) {
        this.idexp = idexp;
        this.livre = livre;
        this.emprunts = emprunts;
    }

    public Exemplaire(int idexp, Livre livre) {
        this.idexp = idexp;
        this.livre = livre;
    }




    @Id
    @Column(name="IDEXP", unique=true)
    public int getIdexp() {
        return this.idexp;
    }

    public void setIdexp(int idexp) {
        this.idexp = idexp;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ISBN")
    public Livre getLivre() {
        return this.livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="exemplaire")
    public  Set<Emprunt> getEmprunts() {
        return this.emprunts;
    }

    public void setEmprunts( Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public Exemplaire(Livre livre) {
        this.livre = livre;
    }
}
