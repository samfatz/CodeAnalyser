package Abusers.Switch;
// From https://rosettacode.org/wiki/Morpion_solitaire/Java
// Nothing in here

import java.awt.*;
        import java.awt.event.*;
        import java.util.*;
        import java.util.List;
        import javax.swing.*;

public class MorpionSolitaire extends JFrame {

    MorpionSolitairePanel panel;

    public static void main(String[] args) {
        JFrame f = new MorpionSolitaire();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public MorpionSolitaire() {
        Container content = getContentPane();
        content.setLayout(new BorderLayout());
        panel = new MorpionSolitairePanel();
        content.add(panel, BorderLayout.CENTER);
        setTitle("MorpionSolitaire");
        pack();
        setLocationRelativeTo(null);
    }
}