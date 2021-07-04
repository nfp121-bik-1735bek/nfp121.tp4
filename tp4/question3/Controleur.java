package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(null /* null est à remplacer */);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    pile.empiler(operande());
                    actualiserInterface();
                    }catch(Exception ex){
                }
            }
        }); /* null est à remplacer */
        boutons.add(add);   add.addActionListener(new operation('+') /* null est à remplacer */);
        boutons.add(sub);   sub.addActionListener(new operation('-') /* null est à remplacer */);
        boutons.add(mul);   mul.addActionListener(new operation('*') /* null est à remplacer */);
        boutons.add(div);   div.addActionListener(new operation('/') /* null est à remplacer */);
        boutons.add(clear); clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    while(!pile.estVide()){
                        pile.depiler();
                    }
                    actualiserInterface();
                    }catch(Exception ex){
                }
            }
        } /* null est à remplacer */);
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        // à compléter
        donnee.setText("");
        
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }
    
    private class  operation implements ActionListener{
        char oper;
        operation(char oper){
            this.oper = oper;
        
        }
        public void actionPerformed(ActionEvent ae){
            Integer n1 = null;
            Integer n2 = null;
            try{

                n1 = pile.depiler();
                n2 = pile.depiler();
                switch(oper){
                
                    case '+' : pile.empiler(n1 + n2);break;
                    case '-' : pile.empiler(n2 - n1);break;
                    case '*' : pile.empiler(n1 * n2);break;
                    case '/' : if ( n1 != 0){
                        pile.empiler(n2/n1);
                    }
                    break;
            
                }
                actualiserInterface();
            }
            catch(Exception ex){
                if (n1 != null){
                    try{
                        pile.empiler(n1);
                    }
                    catch(Exception ex2){}
                }
            }
            
        }
    
    } 

    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)

}
