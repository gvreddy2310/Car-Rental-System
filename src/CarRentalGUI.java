import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Car {
    int id;
    String name;
    boolean rented;

    Car(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + " - " + name + " (" + (rented ? "Rented" : "Available") + ")";
    }
}

public class CarRentalGUI extends JFrame implements ActionListener {

    JTextField idField = new JTextField(),
               nameField = new JTextField();

    JTextArea area = new JTextArea();

    ArrayList<Car> cars = new ArrayList<>();

    JButton add = new JButton("Add"),
            view = new JButton("View"),
            rent = new JButton("Rent"),
            ret = new JButton("Return");

    CarRentalGUI() {

        setTitle("Car Rental System");
        setSize(400, 400);
        setLayout(null);

        // for background color
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // for labels
        addLabel("ID:", 20, 20);
        addLabel("Name:", 20, 60);

        // for text fields
        idField.setBounds(100, 20, 150, 25);
        add(idField);

        nameField.setBounds(100, 60, 150, 25);
        add(nameField);

        // for buttons
        addButton(add, 20);
        addButton(view, 110);
        addButton(rent, 200);
        addButton(ret, 290);

        // for text area
        area.setEditable(false);

        JScrollPane sp = new JScrollPane(area);
        sp.setBounds(20, 150, 350, 180);
        add(sp);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // a method to add labels
    void addLabel(String text, int x, int y) {
        JLabel l = new JLabel(text);
        l.setBounds(x, y, 80, 25);
        add(l);
    }

    // a method to add buttons
    void addButton(JButton b, int x) {
        b.setBounds(x, 100, 80, 30);
        add(b);
        b.addActionListener(this);
    }

    // a button actions
    public void actionPerformed(ActionEvent e) {

        int id = idField.getText().isEmpty()
                ? -1
                : Integer.parseInt(idField.getText());

        // for adding a car
        if (e.getSource() == add) {

            cars.add(new Car(id, nameField.getText()));

            area.setText("Car Added Successfully\n");

            // clear fields
            idField.setText("");
            nameField.setText("");
        }

        // viewing Cars
        else if (e.getSource() == view) {

            area.setText("");

            for (Car c : cars) {
                area.append(c + "\n");
            }
        }

        // Rent / Return
        else {

            for (Car c : cars) {

                if (c.id == id) {

                    // rent Car
                    if (e.getSource() == rent && !c.rented) {

                        c.rented = true;
                        area.setText("Car Rented Successfully\n");
                        return;
                    }

                    // return Car
                    if (e.getSource() == ret && c.rented) {

                        c.rented = false;
                        area.setText("Car Returned Successfully\n");
                        return;
                    }
                }
            }

            area.setText("Invalid Action\n");
        }
    }

    public static void main(String[] args) {
        new CarRentalGUI();
    }
}
