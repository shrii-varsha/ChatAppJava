package appication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {
    private JTextField textField;
    private JTextArea textArea;
    private JButton sendButton;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Client() {
        textField = new JTextField(30);
        textArea = new JTextArea(30, 40);
        textArea.setEditable(false);
        sendButton = new JButton("Send");

        sendButton.addActionListener(this);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textField, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.SOUTH);

        add(panel);

        setTitle("Client");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new Thread(this::startClient).start(); // Start client in a new thread
    }

    private void startClient() {
        try {
            socket = new Socket("localhost", 1200);
            textArea.append("Connected to server.\n");

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String msg = dataInputStream.readUTF();
                SwingUtilities.invokeLater(() -> textArea.append("Server: " + msg + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = textField.getText();
        textField.setText("");

        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
            textArea.append("Client: " + msg + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
