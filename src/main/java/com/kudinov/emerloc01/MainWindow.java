package com.kudinov.emerloc01;



import javax.swing.*;
import com.kudinov.emerloc01.GameField;
public class MainWindow extends JFrame {
    public MainWindow() {
        add(new GameField());
        setTitle("Emerloc.01");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
    }

    public static void main(String[] args) {
     MainWindow window = new MainWindow();
//        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
//        Player player1 = new Player();
//        player1.setName("Alex");
//        player1.setPassword("qwerty");
//        player1.setPlayed(0);
//        player1.setWon(0);
//        playerDAO.save(player1);
//        playerDAO.incrWon(player1);
//        playerDAO.getAll().forEach(it->System.out.println(it));
//        playerDAO.getAll().forEach(player -> playerDAO.delete(player));



    }
}