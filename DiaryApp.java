import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class DiaryApp extends JFrame {
    private JTextArea diaryArea;
    private JButton saveBtn, loadBtn, clearBtn, imageBtn, removeImageBtn, deleteBtn, deleteEntryBtn, bgColorBtn;
    private JLabel imageLabel;
    private static final String PASSWORD = "1234";
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 150;

    public DiaryApp() {
        setTitle("Secure Diary");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Diary area
        diaryArea = new JTextArea();
        diaryArea.setLineWrap(true);
        diaryArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(diaryArea);

        // Image label
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));

        // Buttons
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        clearBtn = new JButton("Clear");
        imageBtn = new JButton("Add Image");
        removeImageBtn = new JButton("Remove Image");
        deleteBtn = new JButton("Delete Diary");
        deleteEntryBtn = new JButton("Delete Entry");
        bgColorBtn = new JButton("Change Background");

        JPanel panel = new JPanel();
        panel.add(saveBtn);
        panel.add(loadBtn);
        panel.add(clearBtn);
        panel.add(imageBtn);
        panel.add(removeImageBtn);
        panel.add(deleteBtn);
        panel.add(deleteEntryBtn);
        panel.add(bgColorBtn);

        add(imageLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Save diary
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("diary.txt")); // no 'true' flag
                    writer.write(diaryArea.getText());
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Diary saved!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving diary.");
                }
            }
        });

        // Load diary
        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("diary.txt"));
                    diaryArea.read(reader, null);
                    reader.close();
                    JOptionPane.showMessageDialog(null, "Diary loaded!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error loading diary.");
                }
            }
        });

        // Clear diary
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Clear diary text?", "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    diaryArea.setText("");
                }
            }
        });

        // Add image (static size)
        imageBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    Image img = icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(img));
                }
            }
        });

        // Remove image
        removeImageBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imageLabel.setIcon(null);
            }
        });

        // Delete entire diary
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Delete entire diary?", "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    File file = new File("diary.txt");
                    if (file.exists()) {
                        if (file.delete()) {
                            diaryArea.setText("");
                            JOptionPane.showMessageDialog(null, "Diary deleted!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to delete diary.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Diary file does not exist.");
                    }
                }
            }
        });

        // Delete single entry
        deleteEntryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File file = new File("diary.txt");
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(null, "Diary file does not exist.");
                    return;
                }
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    ArrayList<String> entries = new ArrayList<String>();
                    StringBuilder currentEntry = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        if (line.equals("------------------------------")) {
                            if (currentEntry.length() > 0) {
                                entries.add(currentEntry.toString());
                                currentEntry = new StringBuilder();
                            }
                        } else {
                            currentEntry.append(line).append("\n");
                        }
                    }
                    if (currentEntry.length() > 0)
                        entries.add(currentEntry.toString());
                    reader.close();

                    if (entries.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No entries found.");
                        return;
                    }

                    String[] options = new String[entries.size()];
                    for (int i = 0; i < entries.size(); i++) {
                        options[i] = "Entry " + (i + 1);
                    }

                    String selected = (String) JOptionPane.showInputDialog(null, "Select entry to delete:",
                            "Delete Entry", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    if (selected != null) {
                        int index = Integer.parseInt(selected.split(" ")[1]) - 1;
                        entries.remove(index);
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        for (String entry : entries) {
                            writer.write(entry + "\n------------------------------\n");
                        }
                        writer.close();
                        diaryArea.setText("");
                        JOptionPane.showMessageDialog(null, "Entry deleted!");
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting entry.");
                }
            }
        });

        // Change background color
        bgColorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose Background Color", diaryArea.getBackground());
                if (color != null) {
                    diaryArea.setBackground(color);
                }
            }
        });
    }

    private static boolean loginDialog() {
        JPasswordField pf = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String input = new String(pf.getPassword());
            return input.equals(PASSWORD);
        }
        return false;
    }

    public static void main(String[] args) {
        if (loginDialog()) {
            DiaryApp app = new DiaryApp();
            app.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect password. Exiting.");
            System.exit(0);
        }
    }
}