# 📔 Secure Diary App
A simple password-protected personal diary desktop app built with **Java Swing**. Write your thoughts, attach an image, and keep everything secured behind a password — all running locally on your machine with no internet required.

---

## ✨ Features
- 🔐 Password-protected login — app won't open without the correct password
- 💾 Save diary text to a local file (`diary.txt`)
- 📂 Load previously saved diary text from `diary.txt`
- 🗑️ Clear the text area (with confirmation prompt)
- 🖼️ Add an image displayed at the top of the app
- ❌ Remove the displayed image
- 🗂️ Delete a specific entry from the diary file
- 📛 Delete the entire `diary.txt` file from disk
- 🎨 Change the background colour of the writing area

---

## 📚 Use Cases
🔹 Maintain a private daily journal stored on your own computer

🔹 Learn and practice Java Swing GUI programming

🔹 Explore file I/O, event listeners, and dialog boxes in Java

🔹 Use as a beginner-friendly Java desktop project reference

---

## 🛠 Technologies
- **Language:** Java
- **GUI Framework:** Java Swing *(part of the standard JDK — no external libraries needed)*
- **Environment:** Desktop Application
- **Compiler:** `javac` (JDK 8 or above)

---

## 📌 Requirements
To compile and run this program, you'll need:

- **Java JDK 8** or above
  - Windows: Download from [oracle.com](https://www.oracle.com/java/technologies/downloads/)
  - Linux: `sudo apt install default-jdk`
  - macOS: `brew install openjdk`
- *(Optional)* Any Java IDE — IntelliJ IDEA, Eclipse, or VS Code with the Java Extension Pack

---

## 📁 Folder Structure

```
secure-diary-app/
│
├── DiaryApp.java      ← Single source file (entire app lives here)
├── diary.txt          ← Auto-created on first save
│
├── README.md
└── LICENSE
```

---

## 🚀 How to Run

### 🖥️ Option 1: Terminal / Command Prompt

```bash
# Compile
javac DiaryApp.java

# Run
java DiaryApp
```

Enter the password when prompted:
```
Password: 1234
```

### 🧩 Option 2: IDE (IntelliJ / Eclipse / VS Code)
1. Open the project folder in your IDE
2. Open `DiaryApp.java`
3. Click **Run** or press the run shortcut
4. Enter the password in the dialog that pops up

---

## ⚙️ Customisation

### 🔑 Change the Password
Find this line near the top of `DiaryApp.java`:

```java
private static final String PASSWORD = "1234";
```

Replace `"1234"` with your own password, recompile, and you're good to go.

### 🖼️ Change the Image Display Size
The image panel size is defined here:

```java
private static final int IMAGE_WIDTH = 200;
private static final int IMAGE_HEIGHT = 150;
```

Adjust these values and recompile to resize the image panel.

---

## 📁 How Diary Entries Are Stored

All entries are saved as plain text in `diary.txt` in the same folder where you run the app. Individual entries are separated by:

```
------------------------------
```

Example:
```
Had a great day today!
------------------------------
Went for a long walk in the evening.
------------------------------
```

---

## 🔗 Contributing / Feedback
Found a bug? Have a suggestion? Feel free to open an issue or submit a pull request!

---

## ❓ Frequently Asked Questions (FAQ)

**📌 Q1: Do I need an IDE to run this?**  
A: No. A terminal with `javac` and `java` installed is all you need.

**📌 Q2: Where is my diary saved?**  
A: In a file called `diary.txt`, created automatically in the same directory where you run the app.

**📌 Q3: Can I run this on Linux or macOS?**  
A: Yes! The compile and run commands are the same on all platforms as long as JDK is installed.

**📌 Q4: Are images saved with my diary entries?**  
A: No. Images are only displayed during the current session. They are not saved to `diary.txt`.

**📌 Q5: What if I forget my password?**  
A: The password is hardcoded in the source file. Open `DiaryApp.java`, change the `PASSWORD` value, and recompile.

---

## 📌 Known Limitations
- Password is stored as plain text in the source code
- Only one diary file (`diary.txt`) is supported at a time
- Images are not saved — they are lost when the app closes
- No automatic timestamps on entries
- No search functionality

---

## 🧑‍💻 Author
Kinjal Sethiya - titipo08

---

## 📄 LicenseThis project is licensed under the [MIT License](LICENSE).
