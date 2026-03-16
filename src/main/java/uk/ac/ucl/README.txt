SUMMARY OF IMPLEMENTED FEATURES
This application implements a Model-View-Controller (MVC) architecture to process and display synthetic patient data. The following requirements have been completed:

Requirements 1-4 (Core Backend): Developed the foundational Column, DataFrame, and DataLoader classes to parse CSV files robustly. The Model wraps these via a Singleton pattern for access by Servlets.
Requirement 5 (Data Display): Created several Servlets and JSPs to display a list of all patients and view individual patient details.
Requirement 6 (Search): Implemented a keyword search feature in the Model that scans all columns and displays matching patients.
Requirement 7 (Data Operations): Created a dashboard that dynamically calculates and displays the oldest patient, the number of patients per city, and the number of people who share the same birthday.
Requirement 8 (Add/Edit/Delete & CSV Save): Added the ability to Add and Delete patients from the web interface. To protect the original dataset, updates are written to a new CSV file within the server's deployment directory.
Requirement 9 (JSON Export): Developed a JSONWriter class integrated with Jackson. Users can export the current state of the DataFrame to a well-formed JSON file via the web interface.

FEATURE HIGHLIGHTS
Instead of hardcoding 20 individual HTML input fields for the "Add Patient" feature, the JSP dynamically fetches the column names from the DataFrame and generates the form programmatically. This ensures the view automatically adapts to any dataset provided.
In compliance with standard web application practices, file exports (CSV/JSON) are saved to Tomcat's deployment directory using relative paths (getServletContext().getRealPath()) to prevent absolute-path crashes and protect the source CSV files from being unintentionally mutated.