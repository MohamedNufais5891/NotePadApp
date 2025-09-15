import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ExtendedNotepad extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public ExtendedNotepad() {
        // Frame settings
        setTitle(" Notepad App");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Text area with scroll
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // File chooser
        fileChooser = new JFileChooser();

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        helpMenu.add(aboutItem);

        // Optional: Format menu (Font/Color chooser)
        JMenu formatMenu = new JMenu("Format");
        JMenuItem fontItem = new JMenuItem("Choose Font");
        JMenuItem colorItem = new JMenuItem("Choose Color");

        formatMenu.add(fontItem);
        formatMenu.add(colorItem);

        // Add menus to bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        menuBar.add(formatMenu);

        setJMenuBar(menuBar);

        // Action Listeners
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        exitItem.addActionListener(e -> System.exit(0));

        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Extended Notepad\n Created by [M.H.M Nufais & 200102102400]",
                "About",
                JOptionPane.INFORMATION_MESSAGE
        ));

        fontItem.addActionListener(e -> chooseFont());
        colorItem.addActionListener(e -> chooseColor());
    }

    // Open file
    private void openFile() {
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file!");
            }
        }
    }

    // Save file
    private void saveFile() {
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file!");
            }
        }
    }

    // Choose Font
    private void chooseFont() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        String chosen = (String) JOptionPane.showInputDialog(
                this,
                "Select Font:",
                "Font Chooser",
                JOptionPane.PLAIN_MESSAGE,
                null,
                fonts,
                fonts[0]
        );
        if (chosen != null) {
            textArea.setFont(new Font(chosen, Font.PLAIN, 16));
        }
    }

    // Choose Text Color
    private void chooseColor() {
        Color color = JColorChooser.showDialog(this, "Choose Text Color", Color.BLACK);
        if (color != null) {
            textArea.setForeground(color);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ExtendedNotepad().setVisible(true);
        });
    }
}
