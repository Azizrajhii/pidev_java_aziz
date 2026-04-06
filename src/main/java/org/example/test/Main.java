package org.example.test;

import org.example.entities.test;
import org.example.services.ServiceTest;

import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ServiceTest st = new ServiceTest();
        test t = new test("ghaith");

        try {
            // ajouter
            st.ajouter(t);
            System.out.println("Ajout termine");

            // get all
            List<test> listeAvant = st.getAll();
            System.out.println("Liste: " + listeAvant);

            if (!listeAvant.isEmpty()) {
                test premier = listeAvant.get(0);

                // modifier
                premier.setName("rajhi");
                st.modifier(premier);
                System.out.println("Modification terminee");
                System.out.println("Liste modifie: " + st.getAll());

                // supprimer
                st.supprimer(premier.getId());
                System.out.println("Suppression terminee");
                

                // get all apres suppression
                System.out.println("Liste finale: " + st.getAll());
            } else {
                System.out.println("Aucune donnee dans la table test.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}