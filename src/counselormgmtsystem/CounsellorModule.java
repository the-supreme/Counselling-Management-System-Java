import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class CounselorModule {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

abstract class User {
    private String id;
    private String username;
    private String password;
    private String fullName;

    public User(String id, String username, String password, String fullName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public abstract String getRole();

    public String getDashboardTitle() {
        return getRole() + " Dashboard";
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}

class Counselor extends User {
    private String specialization;

    public Counselor(String id, String username, String password, String fullName, String specialization) {
        super(id, username, password, fullName);
        this.specialization = specialization;
    }

    public Counselor(String id, String username, String password, String fullName) {
        this(id, username, password, fullName, "General Counseling");
    }

    @Override
    public String getRole() {
        return "Counselor";
    }

    @Override
    public String getDashboardTitle() {
        return "Counselor Dashboard - " + getFullName();
    }

    public String getSpecialization() {
        return specialization;
    }
}

class Appointment {
    private String appointmentId;
    private String studentId;
    private String studentName;
    private String counselorId;
    private String date;
    private String time;
    private String bookingType;
    private String status;
    private String queueNumber;

    public Appointment(String appointmentId, String studentId, String studentName, String counselorId,
                       String date, String time, String bookingType, String status, String queueNumber) {
        this.appointmentId = appointmentId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.counselorId = counselorId;
        this.date = date;
        this.time = time;
        this.bookingType = bookingType;
        this.status = status;
        this.queueNumber = queueNumber;
    }

    public static Appointment fromTextLine(String line) {
        String[] part = line.split("\\|", -1);
        if (part.length != 9) {
            return null;
        }
        return new Appointment(part[0], part[1], part[2], part[3], part[4], part[5], part[6], part[7], part[8]);
    }

    public String toTextLine() {
        return String.join("|", appointmentId, studentId, studentName, counselorId, date, time,
                bookingType, status, queueNumber);
    }

    public Object[] toTableRow() {
        return new Object[] { appointmentId, studentId, studentName, date, time, bookingType, status, queueNumber };
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCounselorId() {
        return counselorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class ConsultationRecord {
    private String recordId;
    private String appointmentId;
    private String studentId;
    private String studentName;
    private String counselorId;
    private String notes;
    private String recommendations;
    private String createdAt;

    public ConsultationRecord(String recordId, String appointmentId, String studentId, String studentName,
                              String counselorId, String notes, String recommendations, String createdAt) {
        this.recordId = recordId;
        this.appointmentId = appointmentId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.counselorId = counselorId;
        this.notes = notes;
        this.recommendations = recommendations;
        this.createdAt = createdAt;
    }

    public ConsultationRecord(String recordId, Appointment appointment, String counselorId,
                              String notes, String recommendations) {
        this(recordId, appointment.getAppointmentId(), appointment.getStudentId(), appointment.getStudentName(),
                counselorId, notes, recommendations, now());
    }

    private static String now() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

    public static ConsultationRecord fromTextLine(String line) {
        String[] part = line.split("\\|", -1);
        if (part.length != 8) {
            return null;
        }
        return new ConsultationRecord(part[0], part[1], part[2], part[3], part[4],
                decode(part[5]), decode(part[6]), part[7]);
    }

    public String toTextLine() {
        return String.join("|", recordId, appointmentId, studentId, studentName, counselorId,
                encode(notes), encode(recommendations), createdAt);
    }

    public Object[] toTableRow() {
        return new Object[] { recordId, appointmentId, studentId, studentName, createdAt };
    }

    private static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    private static String decode(String value) {
        return new String(Base64.getDecoder().decode(value));
    }

    public String getCounselorId() {
        return counselorId;
    }
}

class DataStore {
    private final Path dataDirectory = Paths.get("data");
    private final Path counselorsFile = dataDirectory.resolve("counselors.txt");
    private final Path appointmentsFile = dataDirectory.resolve("appointments.txt");
    private final Path recordsFile = dataDirectory.resolve("consultation_records.txt");

    public ArrayList<Counselor> loadCounselors() {
        ArrayList<Counselor> counselors = new ArrayList<>();
        ensureDataFiles();

        try (BufferedReader reader = Files.newBufferedReader(counselorsFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] part = line.split("\\|", -1);
                if (part.length == 5) {
                    counselors.add(new Counselor(part[0], part[1], part[2], part[3], part[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read counselors.txt: " + e.getMessage());
        }

        return counselors;
    }

    public ArrayList<Appointment> loadAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        ensureDataFiles();

        try (BufferedReader reader = Files.newBufferedReader(appointmentsFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                Appointment appointment = Appointment.fromTextLine(line);
                if (appointment != null) {
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read appointments.txt: " + e.getMessage());
        }

        return appointments;
    }

    public ArrayList<ConsultationRecord> loadConsultationRecords() {
        ArrayList<ConsultationRecord> records = new ArrayList<>();
        ensureDataFiles();

        try (BufferedReader reader = Files.newBufferedReader(recordsFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }

                ConsultationRecord record = ConsultationRecord.fromTextLine(line);
                if (record != null) {
                    records.add(record);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read consultation_records.txt: " + e.getMessage());
        }

        return records;
    }

    public Appointment[] getCounselorAppointmentsAsArray(String counselorId) {
        ArrayList<Appointment> allAppointments = loadAppointments();
        ArrayList<Appointment> counselorAppointments = new ArrayList<>();

        for (Appointment appointment : allAppointments) {
            if (appointment.getCounselorId().equals(counselorId)) {
                counselorAppointments.add(appointment);
            }
        }

        return counselorAppointments.toArray(new Appointment[0]);
    }

    public void saveAppointments(ArrayList<Appointment> appointments) {
        ensureDataFiles();

        try (BufferedWriter writer = Files.newBufferedWriter(appointmentsFile)) {
            writer.write("# appointmentId|studentId|studentName|counselorId|date|time|bookingType|status|queueNumber");
            writer.newLine();
            for (Appointment appointment : appointments) {
                writer.write(appointment.toTextLine());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Unable to save appointments.txt: " + e.getMessage());
        }
    }

    public void addConsultationRecord(ConsultationRecord record) {
        ensureDataFiles();

        try (BufferedWriter writer = Files.newBufferedWriter(recordsFile,
                java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND)) {
            writer.write(record.toTextLine());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Unable to save consultation record: " + e.getMessage());
        }
    }

    public String nextRecordId() {
        ArrayList<ConsultationRecord> records = loadConsultationRecords();
        return String.format("CR%03d", records.size() + 1);
    }

    private void ensureDataFiles() {
        try {
            Files.createDirectories(dataDirectory);

            if (!Files.exists(counselorsFile)) {
                try (BufferedWriter writer = Files.newBufferedWriter(counselorsFile)) {
                    writer.write("# counselorId|username|password|fullName|specialization");
                    writer.newLine();
                    writer.write("C001|alice|pass123|Alice Tan|Academic Stress");
                    writer.newLine();
                    writer.write("C002|daniel|pass123|Daniel Lim|Career Guidance");
                    writer.newLine();
                }
            }

            if (!Files.exists(appointmentsFile)) {
                try (BufferedWriter writer = Files.newBufferedWriter(appointmentsFile)) {
                    writer.write("# appointmentId|studentId|studentName|counselorId|date|time|bookingType|status|queueNumber");
                    writer.newLine();
                    writer.write("A001|S001|Nur Aina|C001|2026-07-01|09:00|Online|Pending|Q001");
                    writer.newLine();
                    writer.write("A002|S002|Jason Lee|C001|2026-07-01|10:30|Walk-in|Pending|Q002");
                    writer.newLine();
                    writer.write("A003|S003|Priya Kumar|C002|2026-07-02|14:00|Online|Pending|Q003");
                    writer.newLine();
                }
            }

            if (!Files.exists(recordsFile)) {
                try (BufferedWriter writer = Files.newBufferedWriter(recordsFile)) {
                    writer.write("# recordId|appointmentId|studentId|studentName|counselorId|notesBase64|recommendationsBase64|createdAt");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to prepare data files: " + e.getMessage());
        }
    }
}

class LoginFrame extends JFrame {
    private final DataStore dataStore = new DataStore();
    private final JTextField usernameField = new JTextField(18);
    private final JPasswordField passwordField = new JPasswordField(18);

    public LoginFrame() {
        setTitle("Counseling Management System - Counselor Login");
        setSize(430, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Counselor Login", JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(18, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(event -> login());

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(loginButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        ArrayList<Counselor> counselors = dataStore.loadCounselors();
        for (Counselor counselor : counselors) {
            if (counselor.login(username, password)) {
                new CounselorDashboardFrame(counselor).setVisible(true);
                dispose();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Invalid counselor username or password.");
    }
}

class CounselorDashboardFrame extends JFrame {
    private final Counselor counselor;
    private final DataStore dataStore = new DataStore();
    private JTable appointmentTable;
    private JTable recordTable;
    private JTextArea notesArea;
    private JTextArea recommendationArea;

    public CounselorDashboardFrame(Counselor counselor) {
        this.counselor = counselor;
        setTitle(counselor.getDashboardTitle());
        setSize(900, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createHeaderPanel(), BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("My Roster", createRosterPanel());
        tabs.addTab("Consultation Records", createConsultationPanel());
        add(tabs, BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String profile = counselor.getFullName() + " | " + counselor.getSpecialization();
        JLabel label = new JLabel(profile);
        label.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(event -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        panel.add(label, BorderLayout.WEST);
        panel.add(logoutButton, BorderLayout.EAST);
        return panel;
    }

    private JPanel createRosterPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        appointmentTable = new JTable();
        refreshAppointmentTable();
        panel.add(new JScrollPane(appointmentTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        JButton completeButton = new JButton("Mark Completed");
        JButton cancelButton = new JButton("Mark Cancelled");

        refreshButton.addActionListener(event -> refreshAppointmentTable());
        completeButton.addActionListener(event -> updateSelectedAppointmentStatus("Completed"));
        cancelButton.addActionListener(event -> updateSelectedAppointmentStatus("Cancelled"));

        buttonPanel.add(refreshButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(cancelButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createConsultationPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        recordTable = new JTable();
        refreshRecordTable();
        JScrollPane recordScrollPane = new JScrollPane(recordTable);
        recordScrollPane.setPreferredSize(new Dimension(850, 150));
        panel.add(recordScrollPane, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(1, 2, 8, 8));
        notesArea = new JTextArea();
        recommendationArea = new JTextArea();
        notesArea.setLineWrap(true);
        notesArea.setWrapStyleWord(true);
        recommendationArea.setLineWrap(true);
        recommendationArea.setWrapStyleWord(true);

        JScrollPane notesScrollPane = new JScrollPane(notesArea);
        notesScrollPane.setBorder(BorderFactory.createTitledBorder("Consultation Notes"));

        JScrollPane recommendationScrollPane = new JScrollPane(recommendationArea);
        recommendationScrollPane.setBorder(BorderFactory.createTitledBorder("Recommendations"));

        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.add(notesScrollPane);
        formPanel.add(recommendationScrollPane);
        panel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JComboBox<String> appointmentBox = new JComboBox<>(getAppointmentIdOptions());
        JButton addButton = new JButton("Add Notes and Recommendations");

        addButton.addActionListener(event -> addConsultationRecord((String) appointmentBox.getSelectedItem()));

        buttonPanel.add(new JLabel("Appointment:"));
        buttonPanel.add(appointmentBox);
        buttonPanel.add(addButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshAppointmentTable() {
        String[] column = { "Appointment ID", "Student ID", "Student Name", "Date", "Time", "Type", "Status", "Queue" };
        DefaultTableModel model = new DefaultTableModel(column, 0);

        Appointment[] appointments = dataStore.getCounselorAppointmentsAsArray(counselor.getId());
        for (Appointment appointment : appointments) {
            model.addRow(appointment.toTableRow());
        }

        appointmentTable.setModel(model);
    }

    private void refreshRecordTable() {
        String[] column = { "Record ID", "Appointment ID", "Student ID", "Student Name", "Created At" };
        DefaultTableModel model = new DefaultTableModel(column, 0);

        ArrayList<ConsultationRecord> records = dataStore.loadConsultationRecords();
        for (ConsultationRecord record : records) {
            if (record.getCounselorId().equals(counselor.getId())) {
                model.addRow(record.toTableRow());
            }
        }

        recordTable.setModel(model);
    }

    private String[] getAppointmentIdOptions() {
        Appointment[] appointments = dataStore.getCounselorAppointmentsAsArray(counselor.getId());
        String[] options = new String[appointments.length];

        for (int i = 0; i < appointments.length; i++) {
            options[i] = appointments[i].getAppointmentId();
        }

        return options;
    }

    private void updateSelectedAppointmentStatus(String newStatus) {
        int row = appointmentTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select an appointment first.");
            return;
        }

        String selectedId = appointmentTable.getValueAt(row, 0).toString();
        ArrayList<Appointment> allAppointments = dataStore.loadAppointments();

        for (Appointment appointment : allAppointments) {
            if (appointment.getAppointmentId().equals(selectedId)) {
                appointment.setStatus(newStatus);
                break;
            }
        }

        dataStore.saveAppointments(allAppointments);
        refreshAppointmentTable();
        JOptionPane.showMessageDialog(this, "Appointment status updated to " + newStatus + ".");
    }

    private void addConsultationRecord(String appointmentId) {
        if (appointmentId == null) {
            JOptionPane.showMessageDialog(this, "No appointment is available for this counselor.");
            return;
        }

        String notes = notesArea.getText().trim();
        String recommendations = recommendationArea.getText().trim();

        if (notes.isEmpty() || recommendations.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both consultation notes and recommendations.");
            return;
        }

        Appointment selectedAppointment = findAppointmentById(appointmentId);
        if (selectedAppointment == null) {
            JOptionPane.showMessageDialog(this, "Selected appointment was not found.");
            return;
        }

        ConsultationRecord record = new ConsultationRecord(dataStore.nextRecordId(),
                selectedAppointment, counselor.getId(), notes, recommendations);
        dataStore.addConsultationRecord(record);

        notesArea.setText("");
        recommendationArea.setText("");
        refreshRecordTable();
        JOptionPane.showMessageDialog(this, "Consultation record saved.");
    }

    private Appointment findAppointmentById(String appointmentId) {
        Appointment[] appointments = dataStore.getCounselorAppointmentsAsArray(counselor.getId());
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }
}
