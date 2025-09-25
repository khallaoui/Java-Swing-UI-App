# 🎓 Java Swing UI App – Student Management System

Java Swing UI App is a **desktop application** for managing students, teachers, absences, grades, and administration in a school/lycée environment.  
It provides a user-friendly interface and integrates with **MySQL** to store and manage academic data.

---

## 📸 Screenshots

| Login Page      | Student Management              |
| --------------- | ------------------------------- |
| ![Login](3.png) | ![Student Report](Etudiant.pdf) |

---

## ⚡ Features

- 👨‍🎓 Manage internal & external students  
- 👩‍🏫 Manage teachers (internal staff)  
- 📚 Define subjects and courses  
- 📝 Track grades & evaluations  
- 📆 Manage absences for students  
- 🛡️ Admin module with user & role management  
- 🗄️ Database integration with MySQL  
- 📊 Generate reports in PDF format  

---

## 🛠️ Installation

1. Clone the repository:
   git clone https://github.com/khallaoui/Java-Swing-UI-App.git
   cd Java-Swing-UI-App

2. Import the project into **Eclipse** or **IntelliJ** (supports `.project` and `.classpath`).  

3. Set up the **MySQL database**:
   - Create a database (e.g., `gestion_etudiant`).  
   - Import the provided `.frm`, `.MYD`, `.MYI`, and `.ibd` files into MySQL `/data/`.  
   - Start MySQL server.  

4. Run the application:
   - Either:
     java -jar GestionEtudiant.jar
   - Or open in IDE and run `Main.java`.

---

## 🚀 Usage

- Log in as admin or teacher.  
- Add students, teachers, subjects, or absences.  
- Generate reports (`Etudiant.pdf`, `Etudiantinterne.pdf`).  

---

## 📂 Project Structure

Java-Swing-UI-App/
│
├── src/                  # Java source code
├── gestion_etudiant/     # Core package with app logic
├── bin/                  # Compiled classes
├── *.jar                 # Executable JAR files
├── *.pdf                 # Example generated reports
├── *.png                 # Screenshots
├── database/             # MySQL data files
└── README.md             # Documentation

---

## 🧩 Tech Stack

- **Java 8+**  
- **Swing** (UI)  
- **MySQL** (database)  
- **JCalendar** (date picker)  

---

## 🤝 Contributing

Pull requests are welcome!  
If you want to make major changes, please open an issue first to discuss what you’d like to change.  

---

## 📄 License

[MIT](https://choosealicense.com/licenses/mit/)

---

## 👤 Author

**Mohamed Khallaoui**  
📌 [GitHub Profile](https://github.com/khallaoui)
