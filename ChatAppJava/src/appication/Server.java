package appication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements ActionListener {
    private JTextField textField;
    private JTextArea textArea;
    private JButton sendButton;

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Server() {
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

        setTitle("Server");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new Thread(this::startServer).start(); // Start server in a new thread
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(1200);
            textArea.append("Waiting for client...\n");
            socket = serverSocket.accept();
            textArea.append("Client connected.\n");

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String msg = dataInputStream.readUTF();
                SwingUtilities.invokeLater(() -> textArea.append("Client: " + msg + "\n"));
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
            textArea.append("Server: " + msg + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
